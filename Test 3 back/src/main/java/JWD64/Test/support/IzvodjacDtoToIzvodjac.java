package JWD64.Test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.IzvodjacDto;
import JWD64.Test.dto.NastupDto;
import JWD64.Test.model.Izvodjac;
import JWD64.Test.model.Nastup;
import JWD64.Test.service.IzvodjacService;

@Component
public class IzvodjacDtoToIzvodjac implements Converter<IzvodjacDto, Izvodjac>{

	@Autowired
	private IzvodjacService izvodjacService;
	
	@Override
	public Izvodjac convert(IzvodjacDto dto) {
		Izvodjac i;
		if (dto.getId() == null) {
			i = new Izvodjac();
		}else {
			i = izvodjacService.findOne(dto.getId());
		}
		if (i != null) {
			i.setBrojClanova(dto.getBrojClanova());
			i.setDrzava(dto.getDrzava());
			i.setId(dto.getId());
			i.setIme(dto.getIme());
			i.setZanr(dto.getZanr());
		}
		return i;
	} 

}
