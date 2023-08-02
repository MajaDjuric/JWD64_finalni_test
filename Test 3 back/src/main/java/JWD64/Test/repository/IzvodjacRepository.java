package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Izvodjac;

@Repository
public interface IzvodjacRepository extends JpaRepositoryImplementation<Izvodjac, Long>  {

	Izvodjac findOneById (Long id);
	List<Izvodjac> findAll ();
}
