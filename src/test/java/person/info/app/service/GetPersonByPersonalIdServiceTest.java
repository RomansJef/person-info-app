package person.info.app.service;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.GetPersonByPersonalIdRequest;
import person.info.app.system.response.GetPersonResponse;
import person.info.app.system.serviceImpl.GetPersonByPersonalIdServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPersonByPersonalIdServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);

    private final GetPersonByPersonalIdServiceImpl getPersonByPersonalId =
            new GetPersonByPersonalIdServiceImpl(repositoryService);

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
    public void getPersonByPersonIdSuccess() {
        // request
        GetPersonByPersonalIdRequest getPersonByPersonalIdRequest =
                GetPersonByPersonalIdRequest.builder()
                        .personalId("1234567-1122")
                        .build();

        // response
        GetPersonResponse getPersonResponse = GetPersonResponse.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();

        when(repositoryService.getPersonByPersonalId("1234567-1122")).thenReturn(personEntity);
        GetPersonResponse actual = getPersonByPersonalId.executeRequest(getPersonByPersonalIdRequest);
        assertThat(actual).isEqualTo(getPersonResponse);
    }

    @Test
    public void whenGetPersonByPersonalIdFailed() {
        //request
        GetPersonByPersonalIdRequest getPersonByPersonalIdRequest =
                GetPersonByPersonalIdRequest.builder()
                        .personalId("1234567-1122")
                        .build();

        when(repositoryService.getPersonByPersonalId("1234567-1123")).thenReturn(null);

        RuntimeException retrieveFailed = assertThrows(
                RuntimeException.class,
                () -> getPersonByPersonalId.executeRequest(getPersonByPersonalIdRequest));
        assertThat(retrieveFailed.getMessage()).isEqualTo("gcbhj4d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Personal ID");
    }
}
