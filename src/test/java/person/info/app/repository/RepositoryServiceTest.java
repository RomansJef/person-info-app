package person.info.app.repository;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.PersonRepository;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.repositoryImpl.RepositoryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RepositoryServiceTest {
    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final RepositoryServiceImpl repositoryService = new RepositoryServiceImpl(personRepository);

    private PersonEntity personEntity = new PersonEntity();

    @Before
    public void setUp() {
        // entity
        personEntity = PersonEntity.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();
    }

    @Test
    public void saveMethodTest() {
        doThrow(new RuntimeException("6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null")).when(personRepository).save(personEntity);

        RuntimeException saveFailed = assertThrows(
                RuntimeException.class,
                () -> repositoryService.savePerson(personEntity));
        assertThat(saveFailed.getMessage()).isEqualTo("6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null");
    }

    @Test
    public void deleteMethodTest() {
        String personalId = "";

        doThrow(new RuntimeException("6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null")).when(personRepository).deleteByPersonalId(personalId);

        RuntimeException deleteFailed = assertThrows(
                RuntimeException.class,
                () -> repositoryService.deletePerson(personalId));
        assertThat(deleteFailed.getMessage()).isEqualTo("6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null");
    }

    @Test
    public void getFullPersonListMethodTest() {
        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        when(personRepository.findAll()).thenReturn(personEntityList);

        List<PersonEntity> actual = repositoryService.retrieveAll();
        assertThat(actual).isEqualTo(personEntityList);
    }

    @Test
    public void getPersonByPersonalIdMethodTest() {
        when(personRepository.findByPersonalId(personEntity.getPersonalId())).thenReturn(Optional.of(personEntity));

        PersonEntity actual = repositoryService.getPersonByPersonalId(personEntity.getPersonalId());
        assertThat(actual).isEqualTo(personEntity);
    }

    @Test
    public void getPersonByPersonalIdFailedMethodTest() {
        assertThrows(RuntimeException.class,
                () -> repositoryService.getPersonByPersonalId(personEntity.getPersonalId()));
    }

    @Test
    public void getPersonByBirthDateMethodTest() {
        when(personRepository.findByBirthDate(personEntity.getBirthDate())).thenReturn(Optional.of(personEntity));

        PersonEntity actual = repositoryService.getPersonByBirthDate(personEntity.getBirthDate());
        assertThat(actual).isEqualTo(personEntity);
    }

    @Test
    public void getPersonByBirthDateFailedMethodTest() {
        assertThrows(RuntimeException.class,
                () -> repositoryService.getPersonByBirthDate(personEntity.getBirthDate()));
    }
}
