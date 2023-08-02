package JWD64.Test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JWD64.Test.dto.FestivalDto;
import JWD64.Test.model.Festival;
import JWD64.Test.service.FestivalService;
import JWD64.Test.support.FestivalTofestivalDto;

@RestController
@RequestMapping(value = "/api/festivali", produces = MediaType.APPLICATION_JSON_VALUE)
public class FestivalController {

	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private FestivalTofestivalDto tofestivalDto;
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<FestivalDto>> getAll (){
		List<Festival> festivali= festivalService.findAll();
		return new ResponseEntity<> (tofestivalDto.convert(festivali),HttpStatus.OK);
	}
}
	 
