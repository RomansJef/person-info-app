package person.info.app.service;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.requests.DeletePersonByIdRequest;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.serviceImpl.DeletePersonServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeletePersonServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);
    private final DeletePersonServiceImpl deletePersonService = new DeletePersonServiceImpl(repositoryService);
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
    public void whenDeletePersonMissingPersonSuccess() {
        // request
        DeletePersonByIdRequest deletePersonRequest =
                DeletePersonByIdRequest.builder()
                        .personalId("1234567-1122")
                        .build();
        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person " + deletePersonRequest.getPersonalId() + " deleted")
                .build();

        when(repositoryService.getPersonByPersonalId(deletePersonRequest.getPersonalId())).thenReturn(personEntity);
        PersonServiceResponse actual = deletePersonService.executeRequest(deletePersonRequest);
        assertThat(actual).isEqualTo(personServiceResponse);
    }

    @Test
    public void whenDeletePersonMissingPersonalId() {
        // request
        DeletePersonByIdRequest deletePersonRequest =
                DeletePersonByIdRequest.builder()
                        .build();

        RuntimeException deleteFailed = assertThrows(
                RuntimeException.class,
                () -> deletePersonService.executeRequest(deletePersonRequest));
        assertThat(deleteFailed.getMessage()).isEqualTo("fbd60118-1612-4c09-abcd-45305dbd7e73 - Error: Personal ID is missing");
    }

    @Test
    public void whenDeletePersonMissingPerson() {
        // request
        DeletePersonByIdRequest deletePersonRequest =
                DeletePersonByIdRequest.builder()
                        .personalId("xxxxxx")
                        .build();

        when(repositoryService.getPersonByPersonalId(deletePersonRequest.getPersonalId())).thenReturn(null);

        RuntimeException deleteFailed = assertThrows(
                RuntimeException.class,
                () -> deletePersonService.executeRequest(deletePersonRequest));
        assertThat(deleteFailed.getMessage()).isEqualTo("580425d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot find Person with such Personal ID");
    }
}
