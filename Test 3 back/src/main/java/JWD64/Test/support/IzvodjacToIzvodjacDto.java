package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.IzvodjacDto;
import JWD64.Test.model.Izvodjac;

@Component
public class IzvodjacToIzvodjacDto implements Converter<Izvodjac, IzvodjacDto>{

	@Override
	public IzvodjacDto convert(Izvodjac i) {
		IzvodjacDto dto = new IzvodjacDto();
		dto.setBrojClanova(i.getBrojClanova());
		dto.setDrzava(i.getDrzava());
		dto.setId(i.getId());
		dto.setIme(i.getIme());
		dto.setZanr(i.getZanr());
		dto.setBrojNastupa(i.getNastupi().size());
		return dto;
	}
	
	public List<IzvodjacDto> convert (List<Izvodjac> izvodjaci){
		List<IzvodjacDto> izvodjaciDto = new ArrayList<IzvodjacDto>();
		for (Izvodjac izvodjac : izvodjaci) {
			izvodjaciDto.add(convert(izvodjac));
		}
		return izvodjaciDto;
	}

}
