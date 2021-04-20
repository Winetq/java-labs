package lab4;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class JPAManagerBrowar extends JPAManager<Browar, String> {
    public JPAManagerBrowar(EntityManagerFactory emf, Class<Browar> clazz) {
        super(emf, clazz);
    }

    public List<Browar> findAllBrowaryThatHavePiwaCheaperThen(int k) {
        List<Browar> answer = new ArrayList<>();
        List<Browar> browary = findAll();
        for(Browar x : browary) {
            int n = x.getSize();
            for(int i = 0; i < n; i++) {
                Piwo piwo = x.getPiwo(i);
                if (piwo != null) {
                    if (piwo.getCena() < k) {
                        answer.add(x);
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
