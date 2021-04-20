package lab4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Piwo {
    @Id
    private String name;

    private long cena;

    @ManyToOne
    private Browar browar; // relacja dwukierunkowa

    public Piwo() {} // default constructor

    public Piwo(String name, long cena, Browar browar) {
        this.name = name;
        this.cena = cena;
        this.browar = browar;
    }

    public long getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "Piwo{name = " + name + ", cena = " + cena + ", browar = " + browar.getName() + "}";
    }
}
