package person.info.app.system.service;

import person.info.app.system.request.UpdatePersonEntityRequest;
import person.info.app.system.response.PersonServiceResponse;

/**
 * Interface for Update Person entity service.
 */
public interface UpdatePersonService {

    PersonServiceResponse executeRequest(UpdatePersonEntityRequest updatePersonEntityRequest);
}
