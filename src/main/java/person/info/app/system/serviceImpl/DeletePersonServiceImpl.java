package person.info.app.system.serviceImpl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.requests.DeletePersonByIdRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.service.DeletePersonService;

import java.util.Optional;

/**
 * Delete Person By ID Service.
 */
@Log4j2
@Service
public class DeletePersonServiceImpl implements DeletePersonService {
    @Autowired
    private RepositoryService repositoryService;

    public DeletePersonServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public PersonServiceResponse executeRequest(DeletePersonByIdRequest deletePersonByIdRequest) {
        Validate.notNull(deletePersonByIdRequest.getPersonalId(), "fbd60118-1612-4c09-abcd-45305dbd7e73 - Error: Personal ID is missing");
        String personalId = deletePersonByIdRequest.getPersonalId();

        Optional.ofNullable(repositoryService.getPersonByPersonalId(deletePersonByIdRequest.getPersonalId()))
                .orElseThrow(() ->
                        new RuntimeException("580425d2-wuyt-4b56-b244-5fec900a8741 - Error: Cannot find Person with such Personal ID"));

        repositoryService.deletePerson(personalId);

        return PersonServiceResponse.builder().status("Person " + personalId + " deleted").build();
    }
}
