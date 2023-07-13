package person.info.app.system.service;

import person.info.app.system.request.SavePersonRequest;
import person.info.app.system.response.PersonServiceResponse;

/**
 * Innterface for Service to save new person.
 */
public interface SavePersonService {

    PersonServiceResponse executeRequest(SavePersonRequest savePersonRequest);
}
