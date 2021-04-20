import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

// @param <T> type of the entity
// @param <K> type of the entity primary key
public abstract class JPAManager<T, K> {
    private final EntityManagerFactory emf;
    private final Class<T> clazz;

    public JPAManager(EntityManagerFactory emf, Class<T> clazz) {
        this.emf = emf;
        this.clazz = clazz;
    }

    public void add(T entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
    }

     public void show() {
        List<T> list = findAll();
        for (T x : list) {
            System.out.println(x.toString());
        }
    }

    protected List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> list = em.createQuery("select t from " + clazz.getSimpleName() + " t", clazz).getResultList();
        em.close();

        return list;
    }

    public void update(T entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(entity);
        transaction.commit();
        em.close();
    }

    public T find(K id) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(clazz, id);
        em.close();

        return entity;
    }

    public void delete(T entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(entity));
        transaction.commit();
        em.close();
    }

    protected EntityManagerFactory getEmf() {
        return emf;
    }
}
