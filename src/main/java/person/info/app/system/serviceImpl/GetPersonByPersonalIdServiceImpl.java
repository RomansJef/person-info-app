package person.info.app.system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.GetPersonByPersonalIdRequest;
import person.info.app.system.response.GetPersonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import person.info.app.system.service.GetPersonByPersonalIdService;

import java.util.Optional;

/**
 * Service to find Person by PersonId.
 */
@Log4j2
@Service
public class GetPersonByPersonalIdServiceImpl implements GetPersonByPersonalIdService {
    @Autowired
    private RepositoryService repositoryService;

    public GetPersonByPersonalIdServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public GetPersonResponse executeRequest(GetPersonByPersonalIdRequest getPersonByPersonalIdRequest) {

        PersonEntity personEntity = Optional.ofNullable(getPersonByPersonalIdRequest.getPersonalId())
                .map(id -> repositoryService.getPersonByPersonalId(id))
                .orElseThrow(
                        () -> new RuntimeException("gcbhj4d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Personal ID"));

        return GetPersonResponse.builder()
                .id(personEntity.getId())
                .personalId(personEntity.getPersonalId())
                .name(personEntity.getName())
                .gender(personEntity.getGender())
                .birthDate(personEntity.getBirthDate())
                .phoneNumber(personEntity.getPhoneNumber())
                .email(personEntity.getEmail())
                .build();
    }
}
