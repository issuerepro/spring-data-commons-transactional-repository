package transactional.repository.issues.repro.domain;

import transactional.repository.issues.repro.factory.CrudRepository;

public interface SucceedingPersonRepository extends CrudRepository<Person, Long> {

	Person findByFirstName(String firstName);

}
