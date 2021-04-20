import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAManagerMage extends JPAManager<Mage, String> {
    public JPAManagerMage(EntityManagerFactory emf, Class<Mage> clazz) {
        super(emf, clazz);
    }

    public List<Mage> findAllMagesWithLevelAboveOrEqualAndWithSpecificTower(int lvl, Tower t) {
        EntityManager em = getEmf().createEntityManager();
        List<Mage> mages = em.createQuery("SELECT m FROM Mage m WHERE m.level >= :lvl AND m.tower = :t ORDER BY m.level", Mage.class)
                .setParameter("lvl", lvl)
                .setParameter("t", t)
                .getResultList();
        em.close();

        return mages;

    }
}
