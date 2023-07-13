package person.info.app.system.service;

import person.info.app.system.response.GetFullPersonListResponse;

/**
 * Interface for service getting all Items stored in repository service.
 */
public interface GetFullPersonListService {

    GetFullPersonListResponse executeRequest();
}
