package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Festival;

@Repository
public interface FestivalRepository  extends JpaRepositoryImplementation<Festival, Long>  {

	Festival findOneById (Long id);
	List<Festival> findAll();
}
