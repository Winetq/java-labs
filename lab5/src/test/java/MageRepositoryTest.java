import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MageRepositoryTest {
    @Test
    public void delete_deletingNonExistingMage_IllegalArgumentException() {
        // given
        MageRepository mageRepository = new MageRepository();
        Mage mage = new Mage("mage", 99);
        // when
        Throwable thrown = catchThrowable(() -> {
            mageRepository.delete(mage.getName());
        });
        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void find_findingNonExistingMage_EmptyOptional() {
        // given
        MageRepository mageRepository = new MageRepository();
        Mage mage = new Mage("mage", 99);
        // when
        Optional<Mage> optional = mageRepository.find(mage.getName());
        // then
        assertThat(optional).isEmpty();
    }

    @Test
    public void find_findingExistingMage_OptionalWithMage() {
        // given
        MageRepository mageRepository = new MageRepository();
        Mage mage = new Mage("mage", 99);
        mageRepository.save(mage);
        // when
        Optional<Mage> optional = mageRepository.find(mage.getName());
        // then
        assertThat(optional).isPresent();
    }

    @Test
    public void save_savingMageWhosePrimaryKeyAlreadyExists_IllegalArgumentException() {
        // given
        MageRepository mageRepository = new MageRepository();
        Mage mage1 = new Mage("mage", 99);
        Mage mage2 = new Mage("mage", 25);
        mageRepository.save(mage1);
        // when
        Throwable thrown = catchThrowable(() -> {
            mageRepository.save(mage2);
        });
        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
