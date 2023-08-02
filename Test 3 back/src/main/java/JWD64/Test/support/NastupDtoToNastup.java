package JWD64.Test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.NastupDto;
import JWD64.Test.model.Nastup;
import JWD64.Test.service.FestivalService;
import JWD64.Test.service.IzvodjacService;
import JWD64.Test.service.NastupService;

@Component
public class NastupDtoToNastup implements Converter<NastupDto, Nastup>{

	@Autowired
	private NastupService nastupService;
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private IzvodjacService izvodjacService;
	
	@Override
	public Nastup convert(NastupDto dto) {
		Nastup n;
		if (dto.getId() == null) {
			n = new Nastup();
		}else {
			n = nastupService.findOne(dto.getId()); 
		}
		if (n != null) {
			n.setId(dto.getId());
			n.setFestival(festivalService.findOne(dto.getFestivalId()));
			n.setIzvodjac(izvodjacService.findOne(dto.getIzvodjacId()));
		}
		return n;
	}

}
