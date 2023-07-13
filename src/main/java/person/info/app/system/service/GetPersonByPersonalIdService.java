package person.info.app.system.service;

import person.info.app.system.request.GetPersonByPersonalIdRequest;
import person.info.app.system.response.GetPersonResponse;

/**
 * Get Person By PersonalId Service Interface.
 */
public interface GetPersonByPersonalIdService {

    GetPersonResponse executeRequest(GetPersonByPersonalIdRequest getPersonByPersonalIdRequest);
}
