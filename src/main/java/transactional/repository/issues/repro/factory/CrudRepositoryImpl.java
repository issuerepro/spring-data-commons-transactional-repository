package transactional.repository.issues.repro.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;

public class CrudRepositoryImpl<T, ID extends Serializable> extends
        QueryDslJpaRepository<T, ID> implements CrudRepository<T, ID> {

	public CrudRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
	        EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	public CrudRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
	        EntityManager entityManager, EntityPathResolver resolver) {
		super(entityInformation, entityManager, resolver);
	}

}
