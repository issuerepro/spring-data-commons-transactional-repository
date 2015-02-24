package transactional.repository.issues.repro.factory;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@NoRepositoryBean
public interface CrudRepository<T, ID extends Serializable> extends
        JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

	@Override
	List<T> findAll(Predicate predicate);

	@Override
	List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

}
