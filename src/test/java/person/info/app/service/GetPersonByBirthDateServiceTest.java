package person.info.app.service;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.requests.GetPersonByBirthDateRequest;
import person.info.app.system.response.GetPersonResponse;
import person.info.app.system.service.GetPersonByBirthDateService;
import person.info.app.system.serviceImpl.GetPersonByBirthDateServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPersonByBirthDateServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);
    private final GetPersonByBirthDateService getPersonByBirthDateService =
            new GetPersonByBirthDateServiceImpl(repositoryService);
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
    public void getPersonByBirthDateSuccess() {
        // request
        GetPersonByBirthDateRequest getPersonByBirthDateRequest =
                GetPersonByBirthDateRequest.builder()
                        .birthDate("1990-01-01")
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

        when(repositoryService.getPersonByBirthDate("1990-01-01")).thenReturn(personEntity);
        GetPersonResponse actual = getPersonByBirthDateService.executeRequest(getPersonByBirthDateRequest);
        assertThat(actual).isEqualTo(getPersonResponse);
    }

    @Test
    public void whenGetPersonByBirthDateFailed() {
        //request
        GetPersonByBirthDateRequest getPersonByBirthDateRequest =
                GetPersonByBirthDateRequest.builder()
                        .birthDate("1990-01-01")
                        .build();

        when(repositoryService.getPersonByBirthDate("1990-01-01")).thenReturn(null);

        RuntimeException retrieveFailed = assertThrows(
                RuntimeException.class,
                () -> getPersonByBirthDateService.executeRequest(getPersonByBirthDateRequest));
        assertThat(retrieveFailed.getMessage()).isEqualTo("647tj4d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Birth Date");
    }
}
