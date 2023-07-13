package person.info.app.system.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.info.app.system.repository.PersonRepository;
import person.info.app.system.repository.RepositoryService;

import java.util.List;
import java.util.Optional;

/**
 * Repository Service.
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {

	/**
	 * Person Repository.
	 */
	 @Autowired
	 private PersonRepository personRepository;
	 
	 public RepositoryServiceImpl(PersonRepository personRepository) {
	  this.personRepository = personRepository;
	 }

	/**
	 * Returns All Persons.
	 */
	 public List<PersonEntity> retrieveAll() {
	 List<PersonEntity> personList = personRepository.findAll();
	  return personList;
	 }

	public PersonEntity getPersonByPersonalId(String personId) {
		Optional<PersonEntity> personEntity = personRepository.findByPersonalId(personId);
		return personEntity.orElseThrow(
				() -> new RuntimeException("tuyr25d2-b7db-4b56-b244-5fec900a8741 - Find by Personal ID error"));
	}

	 public PersonEntity getPersonByBirthDate(String birthDate) {
		  Optional<PersonEntity> personEntity = personRepository.findByBirthDate(birthDate);
		  return personEntity.orElseThrow(
				  () -> new RuntimeException("pokj25d2-b7db-4b56-b244-5fec900a8741 - Find by Date of Birth error"));
		 }
	 
	 public void savePerson(PersonEntity personEntity){
		 personRepository.save(personEntity);
	 }
	  
	 public void deletePerson(String personalId){
		 personRepository.deleteByPersonalId(personalId);
	 }
}
