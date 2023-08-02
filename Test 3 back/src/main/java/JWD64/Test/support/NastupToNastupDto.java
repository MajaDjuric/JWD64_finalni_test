package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.NastupDto;
import JWD64.Test.model.Nastup;

@Component
public class NastupToNastupDto implements Converter<Nastup, NastupDto>{

	@Override
	public NastupDto convert(Nastup n) {
		NastupDto dto = new NastupDto();
		dto.setFestivalId(n.getFestival().getId());
		dto.setFestivalNaziv(n.getFestival().getNaziv());
		dto.setId(n.getId());
		dto.setIzvodjacId(n.getIzvodjac().getId());
		dto.setIzvodjacIme(n.getIzvodjac().getIme());
		return dto;
	}
	
	public List<NastupDto> convert (List<Nastup> nastupi){
		List<NastupDto> nastupiDto = new ArrayList<NastupDto>();
		for (Nastup nastup : nastupi) {
			nastupiDto.add(convert(nastup));
		}
		return nastupiDto;
	}

}
