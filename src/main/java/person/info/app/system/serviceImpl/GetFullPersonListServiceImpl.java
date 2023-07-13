package person.info.app.system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import person.info.app.system.repository.RepositoryService;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.response.GetFullPersonListResponse;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import person.info.app.system.service.GetFullPersonListService;

/**
 * Get all Items stored in repository service.
 */
@Log4j2
@Service
public class GetFullPersonListServiceImpl implements GetFullPersonListService {

    @Autowired
    private RepositoryService repositoryService;

    public GetFullPersonListServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public GetFullPersonListResponse executeRequest() {
            List<PersonEntity> items = repositoryService.retrieveAll();
        return GetFullPersonListResponse.builder().personEntityList(items).build();
    }
}

