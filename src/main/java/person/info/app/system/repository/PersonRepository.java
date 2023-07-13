package person.info.app.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import person.info.app.system.repositoryImpl.PersonEntity;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface PersonRepository extends PagingAndSortingRepository <PersonEntity, String> {

    List<PersonEntity> findAll();

    Optional<PersonEntity> findByPersonalId(@Param("personalId") String personalId);

    Optional<PersonEntity> findByBirthDate(@Param("birthDate") String birthDate);

    void deleteByPersonalId(@Param("birthDate") String personalId);
}