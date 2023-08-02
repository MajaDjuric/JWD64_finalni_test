package JWD64.Test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Nastup;
import JWD64.Test.repository.NastupRepository;
import JWD64.Test.service.NastupService;

@Service
public class JpaNastupService implements NastupService{

	@Autowired
	private NastupRepository nastupRepository;

	@Override
	public Nastup findOne(Long id) {
		return nastupRepository.findOneById(id);
	}

	@Override
	public Page<Nastup> search(Long izvodjacId, Long festivalId, int pageNo) {
		return nastupRepository.search(izvodjacId, festivalId, PageRequest.of(pageNo, 2));
	}

	@Override
	public Nastup save(Nastup nastup) {
		return nastupRepository.save(nastup);
	}

	@Override
	public Nastup update(Nastup nastup) {
		return nastupRepository.save(nastup);

	}

	@Override
	public void delete(Long id) {
		nastupRepository.deleteById(id);
	}



}
