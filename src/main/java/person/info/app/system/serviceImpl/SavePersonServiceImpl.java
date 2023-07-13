package person.info.app.system.serviceImpl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.SavePersonRequest;
import person.info.app.system.response.PersonServiceResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import person.info.app.system.service.SavePersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service to save new person.
 */
@Log4j2
@Service
public class SavePersonServiceImpl implements SavePersonService {
    private static final String MALE = "Male";
    private static final String FEMALE = "Female";

    @Autowired
    private RepositoryService repositoryService;

    public SavePersonServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public PersonServiceResponse executeRequest(SavePersonRequest savePersonRequest) {
        Validate.notNull(savePersonRequest, "a78c5503-f62f-4d36-b193-3481fd999ed4 - Missing request list");

        Optional.ofNullable(repositoryService.retrieveAll())
                .ifPresent(personEntities -> validateRequest(personEntities, savePersonRequest));

        PersonEntity new_person = PersonEntity.builder()
                .id(UUID.randomUUID().toString())
                .personalId(savePersonRequest.getPersonalId())
                .name(savePersonRequest.getName())
                .gender(savePersonRequest.getGender())
                .birthDate(savePersonRequest.getBirthDate())
                .phoneNumber(savePersonRequest.getPhoneNumber())
                .email(savePersonRequest.getEmail())
                .build();

        repositoryService.savePerson(new_person);

        if (repositoryService.getPersonByPersonalId(savePersonRequest.getPersonalId()) != null) {
            return PersonServiceResponse.builder().status("Person " + new_person.getName() + " saved successfully").build();
        } else throw new RuntimeException("37593rd2-wuyt-4b56-b244-5fec900a8741 - Error: Person save failed");
    }

    public void validateRequest(
            List<PersonEntity> personEntities, SavePersonRequest savePersonRequest) {
        for (PersonEntity personEntity : personEntities) {
            validatePersonalId(personEntity, savePersonRequest);
            validateGender(savePersonRequest);
        }
    }

    private void validatePersonalId(PersonEntity personEntity, SavePersonRequest savePersonRequest) {
        Validate.notNull(savePersonRequest.getPersonalId(), "6087e32e-c548-47e2-a93b-85b9a0b56743 - Personal ID cannot be null");
        if (personEntity.getPersonalId().equals(savePersonRequest.getPersonalId())) {
            throw new RuntimeException("588ceeaf-99a4-429c-8ebc-65f429b39833 - Person with such Personal ID already exist in repository");
        }
    }

    private void validateGender(SavePersonRequest savePersonRequest) {
        Validate.notNull(savePersonRequest.getGender(), "311bc5c2-d00f-4f9a-853d-a32ab0a01543 - Person gender cannot be null");
        if (!MALE.equals(savePersonRequest.getGender()) && !FEMALE.equals(savePersonRequest.getGender())) {
            throw new RuntimeException("30be8d2b-d6e6-40f6-9af6-cc0526a6e083 - Person gender is out of value list [Male, Female]");
        }
    }
}