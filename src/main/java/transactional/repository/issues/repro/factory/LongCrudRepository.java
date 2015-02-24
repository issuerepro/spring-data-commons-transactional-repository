package transactional.repository.issues.repro.factory;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LongCrudRepository<T> extends CrudRepository<T, Long> {

	@Override
	T findOne(Long id);

	@Override
	// un-commenting @Transactional results in successfull passing the test
	// @Transactional
	void delete(Long id);

	@Override
	boolean exists(Long id);

	@Override
	T getOne(Long id);

}
