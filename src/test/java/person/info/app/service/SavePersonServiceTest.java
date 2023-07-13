package person.info.app.service;

import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.SavePersonRequest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.serviceImpl.SavePersonServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SavePersonServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);
    private final SavePersonServiceImpl savePersonService =
            new SavePersonServiceImpl(repositoryService);
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
    public void whenSaveNewPersonSuccess() {
        // request
        SavePersonRequest savePersonRequest =
                SavePersonRequest.builder()
                        .personalId("1234567-1122")
                        .name("Xi Xo")
                        .gender("Male")
                        .birthDate("1990-01-01")
                        .phoneNumber("+6824080143")
                        .email("testx@test.com")
                        .build();

        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person " + savePersonRequest.getName() + " saved successfully")
                .build();

        when(repositoryService.getPersonByPersonalId("1234567-1122")).thenReturn(personEntity);
        PersonServiceResponse actual = savePersonService.executeRequest(savePersonRequest);
        assertThat(actual).isEqualTo(personServiceResponse);
    }

    @Test
    public void whenSaveNewPersonValidationPersonalIDNull() {
        // request
        SavePersonRequest savePersonRequest =
                SavePersonRequest.builder()
                        .name("Xi Xo")
                        .gender("Male")
                        .birthDate("1990-01-01")
                        .phoneNumber("+6824080143")
                        .email("testx@test.com")
                        .build();

        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        when(repositoryService.retrieveAll()).thenReturn(personEntityList);
        RuntimeException saveFailed = assertThrows(
                RuntimeException.class,
                () -> savePersonService.executeRequest(savePersonRequest));
        assertThat(saveFailed.getMessage()).isEqualTo("6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null");
    }

    @Test
    public void whenSaveNewPersonValidationPersonalIDExist() {
        // request
        SavePersonRequest savePersonRequest =
                SavePersonRequest.builder()
                        .personalId("1234567-1122")
                        .name("Xi Xo")
                        .gender("Male")
                        .birthDate("1990-01-01")
                        .phoneNumber("+6824080143")
                        .email("testx@test.com")
                        .build();

        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        when(repositoryService.retrieveAll()).thenReturn(personEntityList);
        RuntimeException saveFailed = assertThrows(
                RuntimeException.class,
                () -> savePersonService.executeRequest(savePersonRequest));
        assertThat(saveFailed.getMessage()).isEqualTo("588ceeaf-99a4-429c-8ebc-65f429b39833 - Person with such Personal ID already exist in repository");
    }

    @Test
    public void whenSaveNewPersonGenderValidationFailed() {
        // request
        SavePersonRequest savePersonRequest =
                SavePersonRequest.builder()
                        .personalId("1234567-1123")
                        .name("Xi Xo")
                        .gender("Maxx")
                        .birthDate("1990-01-01")
                        .phoneNumber("+6824080143")
                        .email("testx@test.com")
                        .build();

        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        when(repositoryService.retrieveAll()).thenReturn(personEntityList);
        RuntimeException saveFailed = assertThrows(
                RuntimeException.class,
                () -> savePersonService.executeRequest(savePersonRequest));
        assertThat(saveFailed.getMessage()).isEqualTo("30be8d2b-d6e6-40f6-9af6-cc0526a6e083 - Person gender is out of value list [Male, Female]");
    }
}
