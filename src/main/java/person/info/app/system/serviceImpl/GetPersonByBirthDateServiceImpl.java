package person.info.app.system.serviceImpl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.GetPersonByBirthDateRequest;
import person.info.app.system.response.GetPersonResponse;
import person.info.app.system.service.GetPersonByBirthDateService;

import java.util.Optional;

/**
 * Service to find Person by Borth Date.
 */
@Log4j2
@Service
public class GetPersonByBirthDateServiceImpl implements GetPersonByBirthDateService {
    @Autowired
    private RepositoryService repositoryService;

    public GetPersonByBirthDateServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public GetPersonResponse executeRequest(GetPersonByBirthDateRequest getPersonByBirthDateRequest) {

        PersonEntity personEntity = Optional.ofNullable(getPersonByBirthDateRequest.getBirthDate())
                .map(birthDate -> repositoryService.getPersonByBirthDate(birthDate))
                .orElseThrow(
                        () -> new RuntimeException("647tj4d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Birth Date"));

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
