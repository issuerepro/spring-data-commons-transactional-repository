package transactional.repository.issues.repro;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import transactional.repository.issues.repro.domain.Person;
import transactional.repository.issues.repro.domain.FailingPersonRepository;
import transactional.repository.issues.repro.domain.SucceedingPersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoApplicationTests {

	@Autowired
	private FailingPersonRepository failingRepository;
	@Autowired
	private SucceedingPersonRepository succeedingRepository;

	@Test
	public void case1_a_createEntity() {
		createEntity(true);
	}

	@Test
	public void case1_b_createEntity() {
		checkEntityExits(true);
	}

	@Test
	public void case1_c_deleteEntity() {
		// should fail with
		// org.springframework.dao.InvalidDataAccessApiUsageException: No
		// transactional EntityManager available; nested exception is
		// javax.persistence.TransactionRequiredException: No transactional
		// EntityManager available
		deleteEntity(true);
	}

	@Test
	public void case1_d_checkEntityMissing() {
		// should fail with something similar to
		// java.lang.AssertionError: expected null, but was:<Person [id=1,
		// firstName=Max]>
		checkEntityMissing(true);
	}

	@Test
	public void case2_a_createEntity() {
		createEntity(false);
	}

	@Test
	public void case2_b_createEntity() {
		checkEntityExits(false);
	}

	@Test
	public void case2_c_deleteEntity() {
		deleteEntity(false);
	}

	@Test
	public void case2_d_checkEntityMissing() {
		checkEntityMissing(false);
	}

	private void createEntity(boolean mode) {
		Person person = findByFirstName(mode, name(mode));
		assertNull(person);

		person = save(mode, new Person(name(mode)));
		assertNotNull(person);
	}

	private void checkEntityExits(boolean mode) {
		Person person = findByFirstName(mode, name(mode));
		assertNotNull(person);
	}

	private void deleteEntity(boolean mode) {
		Person person = findByFirstName(mode, name(mode));
		assertNotNull(person);

		delete(mode, person);

		person = findByFirstName(mode, name(mode));
		assertNull(person);
	}

	public void checkEntityMissing(boolean mode) {
		Person person = findByFirstName(mode, name(mode));
		assertNull(person);
	}

	private Person save(boolean repo, Person person) {
		if (repo) {
			return failingRepository.save(person);
		} else {
			return succeedingRepository.save(person);
		}
	}

	private void delete(boolean repo, Person person) {
		if (repo) {
			failingRepository.delete(person.getId());
		} else {
			succeedingRepository.delete(person.getId());
		}
	}

	private Person findByFirstName(boolean repo, String firstName) {
		if (repo) {
			return failingRepository.findByFirstName(firstName);
		} else {
			return succeedingRepository.findByFirstName(firstName);
		}
	}

	private String name(boolean mode) {
		return mode ? "Max" : "Carl";
	}
}
