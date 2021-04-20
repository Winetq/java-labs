import java.util.Comparator;

public class LevelCompare implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        if (mage1.getLevel() < mage2.getLevel())
            return -1;
        else if (mage1.getLevel() > mage2.getLevel())
            return 1;
        else
            return 0;
        // return mage1.getLevel() - mage2.getLevel();
    }
}
