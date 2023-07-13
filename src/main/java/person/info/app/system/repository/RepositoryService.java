package person.info.app.system.repository;

import person.info.app.system.repositoryImpl.PersonEntity;

import java.util.List;

/**
 * Repository Service Interface.
 */
public interface RepositoryService {

    List<PersonEntity> retrieveAll();

    PersonEntity getPersonByPersonalId(String personId);

    PersonEntity getPersonByBirthDate(String birthDate);

    void savePerson(PersonEntity item);

    void deletePerson(String personalId);
}
