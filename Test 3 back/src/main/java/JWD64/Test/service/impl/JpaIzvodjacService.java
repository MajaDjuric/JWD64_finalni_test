package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Izvodjac;
import JWD64.Test.repository.IzvodjacRepository;
import JWD64.Test.service.IzvodjacService;

@Service
public class JpaIzvodjacService implements IzvodjacService{

	@Autowired
	private IzvodjacRepository izvodjacRepository;

	@Override
	public Izvodjac findOne(Long id) {
		return izvodjacRepository.findOneById(id);
	}

	@Override
	public List<Izvodjac> findAll() {
		return izvodjacRepository.findAll();
	}

	@Override
	public Izvodjac save(Izvodjac izvodjac) {
		return izvodjacRepository.save(izvodjac);
	}
	
}
