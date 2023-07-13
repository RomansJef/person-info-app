package person.info.app.system.service;

import person.info.app.system.request.GetPersonByBirthDateRequest;
import person.info.app.system.response.GetPersonResponse;

/**
 * Service Interface to Get Person By Birth Date.
 */
public interface GetPersonByBirthDateService {

    GetPersonResponse executeRequest(GetPersonByBirthDateRequest getPersonByBirthDateRequest);
}
