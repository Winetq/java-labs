import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Potok {
    private String pathToImages;
    private String pathToConvertedImages;
    private ForkJoinPool pool;

    public Potok(String pathToImages, String pathToConvertedImages, ForkJoinPool pool) {
        this.pathToImages = pathToImages;
        this.pathToConvertedImages = pathToConvertedImages;
        this.pool = pool;
    }

    public void convert() {
        List<Path> files;
        Path source = Path.of(pathToImages);
        try (Stream<Path> stream = Files.list(source)) {
            files = stream.collect(Collectors.toList());
            pool.submit(() -> {
                files.stream().parallel()   // uzyskanie rownoleglego strumienia
                        .map(path -> {      // pierwsze odwzorowanie
                            try {
                                BufferedImage image = ImageIO.read(path.toFile());
                                String name = path.getFileName().toString();
                                return Pair.of(name, image);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .map(pair -> {      // drugie odwzorowanie
                            BufferedImage original = pair.getRight();
                            BufferedImage image = new BufferedImage (original.getWidth(), original.getHeight(), original.getType());
                            transformImage(original, image);
                            return Pair.of(pair.getLeft(), image);
                        })
                        .forEach(pair -> {  // zakonczenie strumienia
                            Path path = Path.of(pathToConvertedImages, pair.getLeft()); // path + file name
                            try {
                                ImageIO.write(pair.getRight(), "jpg", path.toFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }).get();
        } catch (IOException exception) {
            System.out.println("Error during loading list of paths!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void transformImage(BufferedImage original, BufferedImage image) {
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int rgb = original.getRGB(i, j);
                image.setRGB(i, j, transformRGB(rgb));
            }
        }
    }

    private int transformRGB(int rgb) {
        Color color = new Color(rgb);
        int red = (int) Math.round(0.299 * (double)color.getRed()); // 0.299 * red
        int green = (int) Math.round(0.587 * (double)color.getGreen()); // 0.587 * green
        int blue = (int) Math.round(0.114 * (double)color.getBlue()); // 0.114 * blue
        int grey = red + green + blue;
        return new Color(grey, grey, grey).getRGB();
    }
}
