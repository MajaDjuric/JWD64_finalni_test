package JWD64.Test.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import JWD64.Test.dto.NastupDto;
import JWD64.Test.model.Festival;
import JWD64.Test.model.Izvodjac;
import JWD64.Test.model.Nastup;
import JWD64.Test.service.NastupService;
import JWD64.Test.support.NastupDtoToNastup;
import JWD64.Test.support.NastupToNastupDto;

@RestController
@RequestMapping(value = "/api/nastupi", produces = MediaType.APPLICATION_JSON_VALUE)
public class NastupController {
	
	@Autowired
	private NastupService nastupService;
	
	@Autowired
	private NastupDtoToNastup toNastup;
	
	@Autowired
	private NastupToNastupDto toNastupDto;
	
//	@PreAuthorize("permitAll()")
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<NastupDto> getOne (@PathVariable Long id){
//		Nastup nastup = nastupService.findOne(id);
//		if (nastup  == null) {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<> (toNastupDto.convert(nastup) , HttpStatus.OK);
//	}
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<NastupDto>> getAll (@RequestParam (required = false, defaultValue = "0") int pageNo,
												   @RequestParam (required = false) Long izvodjacId,
												   @RequestParam (required = false) Long festivalId){
		Page<Nastup> page= nastupService.search(izvodjacId, festivalId, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<> (toNastupDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Nastup nastup = nastupService.findOne(id);
		if (nastup == null) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		nastupService.delete(id);
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NastupDto> create (@Valid @RequestBody NastupDto nastupDto){ 
		Nastup nastup= toNastup.convert(nastupDto);
		Festival festival = nastup.getFestival();
		List<Nastup> nastupi = festival.getNastupi();
		List<Izvodjac> izvodjaci = new ArrayList<Izvodjac>();
		for (Nastup nastup2 : nastupi) {
			izvodjaci.add(nastup2.getIzvodjac());
			}
		for (Izvodjac izvodjac : izvodjaci) {
			if (nastup.getIzvodjac().getDrzava().equalsIgnoreCase(izvodjac.getDrzava())) {
				return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
			}
		}
		Nastup noviNastup = nastupService.save(nastup);
		return new ResponseEntity<> (toNastupDto.convert(noviNastup),HttpStatus.CREATED);
}
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NastupDto> update (@PathVariable Long id, @Valid @RequestBody NastupDto nastupDto){ 
		if (!id.equals(nastupDto.getId())) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		Nastup nastup= toNastup.convert(nastupDto);
		Nastup izmenjeniNastup = nastupService.update(nastup);
		return new ResponseEntity<> (toNastupDto.convert(izmenjeniNastup),HttpStatus.OK);
}
}



