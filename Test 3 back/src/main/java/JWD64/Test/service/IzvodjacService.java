package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.Izvodjac;

public interface IzvodjacService {

	Izvodjac findOne (Long id);
	List<Izvodjac> findAll ();
	Izvodjac save (Izvodjac izvodjac);
}
