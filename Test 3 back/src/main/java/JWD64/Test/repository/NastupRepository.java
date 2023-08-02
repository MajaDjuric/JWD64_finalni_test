package JWD64.Test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Nastup;

@Repository
public interface NastupRepository extends JpaRepositoryImplementation<Nastup, Long>  {

	Nastup findOneById (Long id);
//	List<Utakmica> findAll ();

	@Query("SELECT n FROM Nastup n WHERE "
			+ "(:izvodjacId IS NULL OR n.izvodjac.id = :izvodjacId) AND "
			+ "(:festivalId IS NULL or n.festival.id = :festivalId)")
	Page<Nastup> search (@Param ("izvodjacId") Long izvodjacId,
						@Param ("festivalId") Long festivalId, 
						Pageable pageable);

}