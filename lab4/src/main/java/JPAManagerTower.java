import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAManagerTower extends JPAManager<Tower, String> {

    public JPAManagerTower(EntityManagerFactory emf, Class<Tower> clazz) {
        super(emf, clazz);
    }

    public Tower findByHeight(int h) {
        List<Tower> list = findAll();
        for (Tower x : list) {
            if (x.getHeight() == h) {
                return x;
            }
        }

        return null;
    }
}
