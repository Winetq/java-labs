import java.util.*;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;
    private int set_type; // HashSet/TreeSet or HashMap/TreeMap

    public Mage(String name, int level, double power, int set_type) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.set_type = set_type;
        if (set_type == 1)
            apprentices = new HashSet<>();
        else if (set_type == 2)
            apprentices = new TreeSet<>(); // sort by natural order so just comparable
        else if (set_type == 4)
            apprentices = new TreeSet<>(new LevelCompare()); // sort by extra parameter (LevelCompare)
        else // set_type == 5
            apprentices = new TreeSet<>(new PowerCompare()); // sort by extra parameter (PowerCompare)
    }

    @Override
    public int compareTo(Mage mage) { // natural order: name, level, power
        int ret = name.compareTo(mage.getName()); // comparing strings

        if (ret == 0)
            ret = level - mage.getLevel(); // comparing level in case of names equality

        if (ret == 0)
            ret = (int)power - (int)mage.getPower(); // comparing power in case of levels equality

        return ret;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) // comparison of references
            return true; // the same objects

        if (other == null || getClass() != other.getClass())
            return false; // other is null or classes are diferent

        Mage mage = (Mage) other;

        if (name != null ? !name.equals(mage.getName()) : mage.getName() != null)
            return false; // check name

        if (level != mage.getLevel())
            return false; // check level

        if (power != mage.getPower())
            return false; // check power

        return true; // return level == mage.getLevel() && power == mage.getPower && Objects.equals(name, mage.getName()); // equals is static methods for Objects class
    }

    @Override
    public int hashCode() { // we have to use all primitive types and hashCode() for classes like String, also we have to multiply result by prime number that is >= 31
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + level + (int)power;
        return result; // return Objects.hash(name, level, power); // like above hash is static methods for Objects class
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + "'" +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    public void addMageToSet(Mage mage) {
        apprentices.add(mage);
    }

    public void showSet(int step) {
        if (step == 0) {
            System.out.println("-" + this);
            this.showSet(step + 1);
        }
        if (apprentices.size() != 0 && step >= 1) {
            for (Mage x : apprentices) {
                String dash = "";
                for (int i = 0; i <= step; i++)
                    dash += "-";
                System.out.println(dash + x);
                x.showSet(step + 1);
            }
        }
    }

    public Map<String, Integer> generateStatistics() {
        if (set_type == 1) { // HashMap
            Map<String, Integer> map = new HashMap<>();
            this.generate(map, 0);

            return map;
        } else { // TreeMap
            Map<String, Integer> map = new TreeMap<>();
            this.generate(map, 0);

            return map;
        }
    }

    private void generate(Map<String, Integer> map, int step) {
        if (step == 0) {
            int number_of_descendants = countNumberOfDescendants(this);
            map.put(this.toString(), number_of_descendants);
            generate(map, step + 1);
        } else {
            // visit all descendants of this object to generate complete statistics
            for (Mage x : this.getSet()) {
                int number_of_descendants = countNumberOfDescendants(x);
                map.put(x.toString(), number_of_descendants);
                x.generate(map, step + 1);
            }
        }
    }

    private int countNumberOfDescendants(Mage mage) { // sum of all nodes in n-ary tree
        int sum = 0;

        if (mage.getSizeOfTheSet() == 0)
            return 0;

        Queue<Mage> q = new LinkedList<>();
        q.add(mage);

        while (!q.isEmpty())
        {
            int n = q.size();

            while (n > 0)
            {
                // dequeue a mage from queue and add his descendants to variable sum
                Mage p = q.peek();
                q.remove();
                sum += p.getSizeOfTheSet();

                q.addAll(p.getSet()); // enqueue all mages of the dequeued item

                n--;
            }
        }

        return sum;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public int getSizeOfTheSet() {
        return apprentices.size();
    }

    public Set<Mage> getSet() {
        return apprentices;
    }
}
