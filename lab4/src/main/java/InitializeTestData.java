public class InitializeTestData {
    private JPAManager<Mage, String> managerMage;
    private JPAManager<Tower, String> managerTower;

    public InitializeTestData(JPAManager<Mage, String> managerMage, JPAManager<Tower, String> managerTower) {
        this.managerMage = managerMage;
        this.managerTower = managerTower;
    }

    public void init() {
        Tower tower1 = new Tower("tower1");
        managerTower.add(tower1);

        Mage mage1 = new Mage("mage1", 15, tower1);
        managerMage.add(mage1);
        Mage mage2 = new Mage("mage2", 20, tower1);
        managerMage.add(mage2);
        Mage mage3 = new Mage("mage3", 10, tower1);
        managerMage.add(mage3);
        Mage mage4 = new Mage("mage4", 5, tower1);
        managerMage.add(mage4);
        Mage mage5 = new Mage("mage5", 0, tower1);
        managerMage.add(mage5);

        tower1.addMage(mage1);
        tower1.addMage(mage2);
        tower1.addMage(mage3);
        tower1.addMage(mage4);
        tower1.addMage(mage5);

        managerTower.update(tower1);
    }
}
