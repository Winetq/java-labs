import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> optional = repository.find(name);
        if (optional.isPresent()) {
            return optional.toString();
        } else {
            return "not found";
        }
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException iae) {
            return "not found";
        }
    }

    public String save(String name, int level) {
        try {
            Mage mage = new Mage(name, level);
            repository.save(mage);
            return "done";
        } catch (IllegalArgumentException iae) {
            return "bad request";
        }
    }

    public void showMageRepository() {
        repository.show();
    }
}
