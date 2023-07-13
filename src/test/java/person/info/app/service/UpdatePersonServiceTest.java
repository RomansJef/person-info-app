package person.info.app.service;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.requests.UpdatePersonEntityRequest;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.serviceImpl.UpdatePersonServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdatePersonServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);
    private final UpdatePersonServiceImpl updatePersonService =
            new UpdatePersonServiceImpl(repositoryService);
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
    public void whenUpdatePersonSuccess() {
        // entity
        PersonEntity updatedPersonEntity = PersonEntity.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();

        // request
        UpdatePersonEntityRequest updatePersonEntityRequest =
                UpdatePersonEntityRequest.builder()
                        .personalId("1234567-1122")
                        .name("Pro Secco")
                        .gender("Female")
                        .birthDate("1980-02-02")
                        .phoneNumber("+6124080143")
                        .email("test@test.com")
                        .build();

        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person with Person ID " + updatedPersonEntity.getPersonalId() + " updated successfully")
                .build();

        when(repositoryService.getPersonByPersonalId("1234567-1122")).thenReturn(updatedPersonEntity);
        PersonServiceResponse actual = updatePersonService.executeRequest(updatePersonEntityRequest);
        assertThat(actual).isEqualTo(personServiceResponse);
    }

    @Test
    public void whenUpdatePersonMissingPersonalId() {
        // request
        UpdatePersonEntityRequest updatePersonEntityRequest =
                UpdatePersonEntityRequest.builder()
                        .build();

        doNothing().when(repositoryService).deletePerson(personEntity.getPersonalId());

        when(repositoryService.getPersonByPersonalId("1234567-1122")).thenReturn(personEntity);

        RuntimeException updateFailed = assertThrows(
                RuntimeException.class,
                () -> updatePersonService.executeRequest(updatePersonEntityRequest));
        assertThat(updateFailed.getMessage()).isEqualTo("a78c5472-f62f-4d36-b193-3481fd999ed4 - Error: Update person request Personal ID should not be null");
    }

    @Test
    public void whenUpdatePersonCannotGetByPersonalId() {
        // request
        UpdatePersonEntityRequest updatePersonEntityRequest =
                UpdatePersonEntityRequest.builder()
                        .personalId("1234567-1122")
                        .build();

        when(repositoryService.getPersonByPersonalId("1234567-1123")).thenReturn(null);

        RuntimeException updateFailed = assertThrows(
                RuntimeException.class,
                () -> updatePersonService.executeRequest(updatePersonEntityRequest));
        assertThat(updateFailed.getMessage()).isEqualTo("sfdf25d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Personal ID");
    }
}
