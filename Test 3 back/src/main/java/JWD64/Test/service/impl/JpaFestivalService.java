package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Festival;
import JWD64.Test.repository.FestivalRepository;
import JWD64.Test.service.FestivalService;

@Service
public class JpaFestivalService implements FestivalService {

	@Autowired
	private  FestivalRepository festivalRepository;

	@Override
	public Festival findOne(Long id) {
		return festivalRepository.findOneById(id);
	}

	@Override
	public List<Festival> findAll() {
		return festivalRepository.findAll();
	}
	
	
	
	
}
