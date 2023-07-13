package person.info.app.system.service;

import person.info.app.system.request.DeletePersonByIdRequest;
import person.info.app.system.response.PersonServiceResponse;

/**
 * Delete Person By ID Service Interface.
 */
public interface DeletePersonService {

    PersonServiceResponse executeRequest(DeletePersonByIdRequest deletePersonByIdRequest);
}
