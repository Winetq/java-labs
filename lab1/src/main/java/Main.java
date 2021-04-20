import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. brak sortowania");
        System.out.println("2. sortowanie zgodnie z naturalnym porzadkiem");
        System.out.println("3. sortowanie zgodnie z alternatywnym kryterium");
        System.out.println("Wybierz numer: ");
        int odp = scanner.nextInt();

        if (odp == 1) { // HashSet
            Mage mage1 = new Mage("Michael", 99, 25.75, odp);
            Mage temp = new Mage("Michael", 99, 25.75, odp); // to testing equals() i hashCode()
            Mage mage2 = new Mage("Zibi", 25, 1.25, odp);
            Mage mage21 = new Mage("Gabriel", 36, 1.75, odp);
            Mage mage3 = new Mage("Kajtek", 64, 50.25, odp);
            Mage mage31 = new Mage("Majtek", 1, 3.25, odp);

            System.out.println(mage1.equals(temp)); // true
            System.out.println(mage1.hashCode() == temp.hashCode()); // true

            System.out.println(temp.equals(temp)); // true
            System.out.println(temp.hashCode() == temp.hashCode()); // true

            System.out.println(mage1.equals(mage2)); // false
            System.out.println(mage1.hashCode() == mage2.hashCode()); // false

            System.out.println("Potomkowie i ich hierarchia dla mage1:");
            mage1.addMageToSet(mage2);
            mage2.addMageToSet(mage21);
            mage1.addMageToSet(mage3);
            mage3.addMageToSet(mage31);

            mage1.showSet(0);

            System.out.println("Potomkowie i ich hierarchia dla mage2:");
            mage2.showSet(0);

            System.out.println("Statystyka dla mage1:");
            Map<String, Integer> map = mage1.generateStatistics();
            for (Object k : map.keySet()) {
                System.out.println(k + ", liczba potomkow = " + map.get(k));
            }
        } else { // TreeSet
            if (odp == 2) { // natural order
                Mage mage1 = new Mage("Michael", 99, 25.75, odp);
                Mage mage2 = new Mage("Zibiel", 25, 1.25, odp);
                Mage mage21 = new Mage("Gabriel", 36, 1.75, odp);
                Mage mage22 = new Mage("Gabriella", 20, 0.75, odp);
                Mage mage3 = new Mage("Zibi", 64, 50.25, odp);
                Mage mage31 = new Mage("Majtek", 1, 3.25, odp);
                Mage mage4 = new Mage("Zibi", 25, 10.25, odp);
                Mage mage41 = new Mage("Mateo", 10, 15.25, odp);
                Mage mage42 = new Mage("Mateo", 10, 10.25, odp);
                Mage mage43 = new Mage("Mateo", 5, 50.75, odp);

                System.out.println("Potomkowie i ich hierarchia zgodnie z naturalnym porzadkiem dla mage1:");
                mage1.addMageToSet(mage2);
                mage2.addMageToSet(mage21);
                mage2.addMageToSet(mage22);
                mage1.addMageToSet(mage3);
                mage3.addMageToSet(mage31);
                mage1.addMageToSet(mage4);
                mage4.addMageToSet(mage41);
                mage4.addMageToSet(mage42);
                mage4.addMageToSet(mage43);

                mage1.showSet(0);

                System.out.println("Statystyka dla mage1:");
                Map<String, Integer> map1 = mage1.generateStatistics();
                for (Object k : map1.keySet()) {
                    System.out.println(k + ", liczba potomkow = " + map1.get(k));
                }

                System.out.println("Statystyka dla mage4:");
                Map<String, Integer> map2 = mage4.generateStatistics();
                for (Object k : map2.keySet()) {
                    System.out.println(k + ", liczba potomkow = " + map2.get(k));
                }
            } else if (odp == 3) { // extra parameter
                System.out.println("4. LevelCompare");
                System.out.println("5. PowerCompare");
                System.out.println("Wybierz numer: ");
                odp = scanner.nextInt();

                if (odp == 4 || odp == 5) {
                    Mage mage1 = new Mage("Michael", 99, 25.75, odp);
                    Mage mage2 = new Mage("Zibiel", 25, 1.25, odp);
                    Mage mage21 = new Mage("Gabriel", 36, 1.75, odp);
                    Mage mage22 = new Mage("Gabriella", 20, 3.75, odp);
                    Mage mage3 = new Mage("Zibi", 64, 50.25, odp);
                    Mage mage31 = new Mage("Majtek", 1, 3.25, odp);
                    Mage mage4 = new Mage("Zibi", 72, 10.25, odp);
                    Mage mage41 = new Mage("Mateo", 8, 15.25, odp);
                    Mage mage42 = new Mage("Mateo", 10, 10.25, odp);
                    Mage mage43 = new Mage("Mateo", 5, 50.75, odp);

                    System.out.println("Potomkowie i ich hierarchia zgodnie z naturalnym porzadkiem dla mage1:");
                    mage1.addMageToSet(mage2);
                    mage2.addMageToSet(mage21);
                    mage2.addMageToSet(mage22);
                    mage1.addMageToSet(mage3);
                    mage3.addMageToSet(mage31);

                    mage1.addMageToSet(mage4);
                    mage4.addMageToSet(mage41);
                    mage4.addMageToSet(mage42);
                    mage4.addMageToSet(mage43);

                    mage1.showSet(0);

                    System.out.println("Statystyka dla mage1:");
                    Map<String, Integer> map = mage1.generateStatistics();
                    for (Object k : map.keySet()) {
                        System.out.println(k + ", liczba potomkow = " + map.get(k));
                    }
                }
                else {
                    System.out.println("Nieznany numer!");
                }
            } else {
                System.out.println("Nieznany numer!");
            }
        }
    }

}
