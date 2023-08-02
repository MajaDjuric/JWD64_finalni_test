package JWD64.Test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import JWD64.Test.model.Festival;

@Service
public interface FestivalService {

	Festival findOne (Long id);
	List<Festival> findAll();
}
