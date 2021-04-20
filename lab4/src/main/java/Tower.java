import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;

    private int height = 0;

    @Transient
    private static int current_height = 0;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tower")
    private List<Mage> mages;

    public Tower() {} // default constructor

    public Tower(String name) {
        this.name = name;
        current_height += 1;
        height = current_height;
        mages = new ArrayList<>();
    }

    public void addMage(Mage mage) {
        mages.add(mage);
    }

    public void decrementHeight() {
        current_height -= 1;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return mages.size();
    }

    @Override
    public String toString() {
        return "Tower{name = " + name + ", height = " + height + ", mages = " + mages.size() + "}";
    }
}
