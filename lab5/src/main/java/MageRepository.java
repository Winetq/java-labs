import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository() {
        this.collection = new ArrayList<>();
    }

    public Optional<Mage> find(String name) {
        return Optional.ofNullable(getMageByID(name));
    }

    public void delete(String name) {
        Mage temp = getMageByID(name);
        if (temp == null) {
            throw new IllegalArgumentException();
        } else {
            collection.remove(temp);
        }
    }

    public void save(Mage mage) {
        Mage temp = getMageByID(mage.getName());
        if (temp != null) {
            throw new IllegalArgumentException();
        } else {
            collection.add(mage);
        }
    }

    private Mage getMageByID(String id) {
        for (Mage x : collection) {
            if (x.getName().equals(id)) {
                return x;
            }
        }

        return null;
    }

    public void show() {
        for (Mage x : collection) {
            System.out.println(x);
        }
    }
}
