package JWD64.Test.service;

import org.springframework.data.domain.Page;

import JWD64.Test.model.Nastup;

public interface NastupService {

	Nastup findOne (Long id);
	Page<Nastup> search (Long izvodjacId, Long festivalId, int pageNo);
	Nastup save (Nastup nastup);
	Nastup update (Nastup nastup);
	void delete (Long id);
}
