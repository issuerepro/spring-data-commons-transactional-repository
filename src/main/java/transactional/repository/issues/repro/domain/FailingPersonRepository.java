package transactional.repository.issues.repro.domain;

import transactional.repository.issues.repro.factory.LongCrudRepository;

public interface FailingPersonRepository extends LongCrudRepository<Person> {

	Person findByFirstName(String firstName);

}
