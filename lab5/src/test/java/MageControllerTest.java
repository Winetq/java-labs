import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MageControllerTest {
    @Test
    public void delete_deletingExistingMage_StringEqualsDone() {
        // given
        MageRepository mageRepository = mock(MageRepository.class);
        doNothing().when(mageRepository).delete(anyString()); // doNothing is a default behavior
        MageController mageController = new MageController(mageRepository);
        // when
        String answer = mageController.delete(anyString());
        // then
        assertThat(answer).isEqualTo("done");
    }

    @Test
    public void delete_deletingNonExistingMage_StringEqualsNotFound() {
        // given
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository).delete(anyString());
        MageController mageController = new MageController(mageRepository);
        // when
        String answer = mageController.delete(anyString());
        // then
        assertThat(answer).isEqualTo("not found");
    }

    @Test
    public void find_findingExistingMage_OptionalWithMage() {
        // given
        Mage mage = new Mage("mage", 99);
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find(anyString())).thenReturn(Optional.of(mage)); // of because of ofNullable
        MageController mageController = new MageController(mageRepository);
        // when
        String answer = mageController.find(mage.getName());
        // then
        assertThat(answer).isEqualTo("Optional[" + mage.toString() + "]");
    }

    @Test
    public void find_findingNonExistingMage_StringEqualsNotFound() {
        // given
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find(anyString())).thenReturn(Optional.empty());
        MageController mageController = new MageController(mageRepository);
        // when
        String answer = mageController.find(anyString());
        // then
        assertThat(answer).isEqualTo("not found");
    }

    @Test
    public void save_savingNewMage_CallingMethodWithCorrectParameterAndStringEqualsDone() {
        // given
        Mage mage = new Mage("mage", 99);
        MageRepository mageRepository = mock(MageRepository.class);
        doNothing().when(mageRepository).save(any(Mage.class));
        MageController mageController = new MageController(mageRepository);
        ArgumentCaptor<Mage> argument = ArgumentCaptor.forClass(Mage.class);
        // when
        String answer = mageController.save("mage", 99);
        verify(mageRepository, times(1)).save(argument.capture());
        // then
        assertThat(answer).isEqualTo("done");
        assertThat(argument.getAllValues()).containsExactly(mage);
    }

    @Test
    public void save_savingMageWhosePrimaryKeyAlreadyExists_StringEqualsBadRequest() {
        // given
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository).save(any(Mage.class));
        MageController mageController = new MageController(mageRepository);
        // when
        String answer = mageController.save("mage", 99);
        // then
        assertThat(answer).isEqualTo("bad request");
    }
}
