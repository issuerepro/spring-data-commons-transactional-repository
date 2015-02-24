package transactional.repository.issues.repro.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

public class CrudRepositoryFactory extends JpaRepositoryFactory {

	public CrudRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected <T, ID extends Serializable> CrudRepositoryImpl<?, ?> getTargetRepository(
	        RepositoryMetadata metadata, EntityManager entityManager) {
		JpaEntityInformation<?, Serializable> entityInformation =
		        getEntityInformation(metadata.getDomainType());
		return new CrudRepositoryImpl(entityInformation, entityManager);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return CrudRepository.class;
	}

}
