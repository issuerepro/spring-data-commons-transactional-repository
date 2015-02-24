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

import transactional.repository.issues.repro.domain.FailingPersonRepository;
import transactional.repository.issues.repro.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoApplicationTests {

	@Autowired
	private FailingPersonRepository failingRepository;

	@Test
	public void failingRepositoryTest() {
		// ensure entity is absent
		assertNull(failingRepository.findByFirstName("Max"));

		// create entity
		assertNotNull(failingRepository.save(new Person("Max")));

		// reload
		Person person = failingRepository.findByFirstName("Max");
		assertNotNull(person);

		// delete entiy
		failingRepository.delete(person.getId());

		// ensure entity is gone
		assertNull(failingRepository.findByFirstName("Max"));
	}
}
