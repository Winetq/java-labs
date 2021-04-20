package lab4;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Browar {
    @Id
    private String name;

    private long wartosc;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "browar")
    private List<Piwo> piwa;

    public Browar() {} // default constructor

    public Browar(String name, long wartosc) {
        this.name = name;
        this.wartosc = wartosc;
        this.piwa = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPiwo(Piwo piwo) {
        piwa.add(piwo);
    }

    public Piwo getPiwo(int i) {
        if (i < piwa.size()) {
            return piwa.get(i);
        } else {
            return null;
        }
    }

    public int getSize() {
        return piwa.size();
    }

    @Override
    public String toString() {
        return "Browar{name = " + name + ", wartosc = " + wartosc + ", liczba piw = " + piwa.size() + '}';
    }
}
