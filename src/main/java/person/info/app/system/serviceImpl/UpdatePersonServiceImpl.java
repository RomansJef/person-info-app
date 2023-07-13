package person.info.app.system.serviceImpl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.requests.UpdatePersonEntityRequest;
import person.info.app.system.response.PersonServiceResponse;

import java.util.Optional;

import lombok.extern.log4j.Log4j2;

import static org.apache.logging.log4j.util.Strings.isEmpty;

import org.springframework.stereotype.Service;
import person.info.app.system.service.UpdatePersonService;

/**
 * Update Person entity service implementation.
 */
@Log4j2
@Service
public class UpdatePersonServiceImpl implements UpdatePersonService {

    @Autowired
    private RepositoryService repositoryService;

    public UpdatePersonServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    private PersonServiceResponse response = new PersonServiceResponse();

    /**
     * Method to execute update.
     */
    public PersonServiceResponse executeRequest(UpdatePersonEntityRequest updatePersonEntityRequest) {
        Validate.notNull(updatePersonEntityRequest.getPersonalId(), "a78c5472-f62f-4d36-b193-3481fd999ed4 - Error: Update person request Personal ID should not be null");

        PersonEntity personEntity = Optional.ofNullable(repositoryService.getPersonByPersonalId(updatePersonEntityRequest.getPersonalId()))
                .orElseThrow(
                        () -> new RuntimeException("sfdf25d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot get Person entity by Personal ID"));

        if (!isEmpty(updatePersonEntityRequest.getName())) {
            personEntity.setName(updatePersonEntityRequest.getName());
        }

        if (!isEmpty(updatePersonEntityRequest.getGender())) {
            personEntity.setGender(updatePersonEntityRequest.getGender());
        }

        if (!isEmpty(updatePersonEntityRequest.getBirthDate())) {
            personEntity.setBirthDate(updatePersonEntityRequest.getBirthDate());
        }

        if (!isEmpty(updatePersonEntityRequest.getPhoneNumber())) {
            personEntity.setPhoneNumber(updatePersonEntityRequest.getPhoneNumber());
        }

        if (!isEmpty(updatePersonEntityRequest.getEmail())) {
            personEntity.setEmail(updatePersonEntityRequest.getEmail());
        }

        repositoryService.savePerson(personEntity);

        return PersonServiceResponse.builder().status("Person with Person ID " + updatePersonEntityRequest.getPersonalId() + " updated successfully").build();
    }
}
