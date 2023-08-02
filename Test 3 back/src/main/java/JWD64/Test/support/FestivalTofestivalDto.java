package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.FestivalDto;
import JWD64.Test.model.Festival;

@Component
public class FestivalTofestivalDto implements Converter<Festival, FestivalDto>{

	@Override
	public FestivalDto convert(Festival f) {
		FestivalDto dto = new FestivalDto();
		dto.setId(f.getId());
		dto.setNaziv(f.getNaziv());
		return dto;
	}
	
	public  List<FestivalDto > convert (List<Festival> festivali){
		List<FestivalDto> festivaliDto = new ArrayList<FestivalDto>();
		for (Festival festival : festivali) {
			festivaliDto.add(convert(festival));
		}
		return festivaliDto;
	}

	
}
