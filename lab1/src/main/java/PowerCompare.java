import java.util.Comparator;

public class PowerCompare implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        if (mage1.getPower() < mage2.getPower())
            return -1;
        else if (mage1.getPower() > mage2.getPower())
            return 1;
        else
            return 0;
    }
}
