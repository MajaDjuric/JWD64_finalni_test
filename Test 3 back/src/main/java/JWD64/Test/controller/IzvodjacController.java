package JWD64.Test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JWD64.Test.dto.IzvodjacDto;
import JWD64.Test.model.Izvodjac;
import JWD64.Test.service.IzvodjacService;
import JWD64.Test.support.IzvodjacDtoToIzvodjac;
import JWD64.Test.support.IzvodjacToIzvodjacDto;

@RestController
@RequestMapping(value = "/api/izvodjaci", produces = MediaType.APPLICATION_JSON_VALUE)
public class IzvodjacController {

	@Autowired
	private IzvodjacService  izvodjacService;
	
	@Autowired
	private IzvodjacDtoToIzvodjac toIzvodjac;
	
	@Autowired
	private IzvodjacToIzvodjacDto toIzvodjacDto;
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<IzvodjacDto>> getAll (){
		List<Izvodjac> izvodjaci= izvodjacService.findAll();
		return new ResponseEntity<> (toIzvodjacDto.convert(izvodjaci),HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IzvodjacDto> create (@Valid @RequestBody IzvodjacDto izvodjacDto){ 
		Izvodjac izvodjac = toIzvodjac.convert(izvodjacDto);
		Izvodjac noviIzvodjac= izvodjacService.save(izvodjac);
		return new ResponseEntity<> (toIzvodjacDto.convert(noviIzvodjac),HttpStatus.CREATED);
}
} 
	
