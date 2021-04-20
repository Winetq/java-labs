import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        // 1 argument startowy przechowuje sciezke do katalogu zawierajacego obrazki do przetworzenia (args[0] = images)
        // 2 argument startowy przechowuje sciezke do katalogu, w ktorym zostana zapisane przetworzone obrazki (args[1] = converted_images)
        if (args.length != 0) {
            String pathToImages = args[0];
            String pathToConvertedImages = args[1];
            ForkJoinPool pool = new ForkJoinPool(1); // stworzenie wlasnej puli watkow
            long time = System.currentTimeMillis();
            new Potok(pathToImages, pathToConvertedImages, pool).convert();
            System.out.println(System.currentTimeMillis() - time); // wypisanie czasu konwersji obrazkow
            pool.shutdown(); // zamkniecie wlasnej puli watkow
        } else {
            System.out.println("Enter program arguments!");
        }
    }

}
