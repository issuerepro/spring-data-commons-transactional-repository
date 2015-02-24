package transactional.repository.issues.repro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String firstName;

	protected Person() {
		// no-args constructor required by JPA spec
		// this one is protected since it shouldn't be used directly
	}

	public Person(String firstName) {
		this.firstName = firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + "]";
	}

}
