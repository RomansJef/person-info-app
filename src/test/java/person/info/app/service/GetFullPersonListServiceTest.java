package person.info.app.service;

import org.junit.Before;
import org.junit.Test;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.response.GetFullPersonListResponse;
import person.info.app.system.serviceImpl.GetFullPersonListServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GetFullPersonListServiceTest {
    private final RepositoryService repositoryService = mock(RepositoryService.class);
    private final GetFullPersonListServiceImpl getAllItemsService =
            new GetFullPersonListServiceImpl(repositoryService);
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
    public void getAllPersonEntitiesSuccess() {
        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        // response
        GetFullPersonListResponse getFullPersonListResponse = GetFullPersonListResponse.builder()
                .personEntityList(personEntityList)
                .build();

        when(repositoryService.retrieveAll()).thenReturn(personEntityList);
        GetFullPersonListResponse actual = getAllItemsService.executeRequest();
        verify(repositoryService, times(1)).retrieveAll();
        assertThat(actual).isEqualTo(getFullPersonListResponse);
    }
}
