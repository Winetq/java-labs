import javax.persistence.*;

@Entity
public class Mage {
    @Id
    private String name;

    private int level;

    @ManyToOne
    private Tower tower; // relacja dwukierunkowa

    public Mage() {} // default constructor

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }

    @Override
    public String toString() {
        return "Mage{name = " + name + ", level = " + level + ", tower = " + tower.getHeight() + "}";
    }
}
