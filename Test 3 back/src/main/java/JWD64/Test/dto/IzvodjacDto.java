package JWD64.Test.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class IzvodjacDto {

	private Long id;
	private String ime;
	private String zanr;
	
	@Size(min = 4, message = "Drzava mora da sadrzi najmanje 4 karaktera.")
	private String drzava;
	
	@Positive(message = "Broj clanova mora da bude pozitivan broj.")
	private int brojClanova;
	
	private int brojNastupa;

	public int getBrojNastupa() {
		return brojNastupa;
	}

	public void setBrojNastupa(int brojNastupa) {
		this.brojNastupa = brojNastupa;
	}

	public IzvodjacDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public int getBrojClanova() {
		return brojClanova;
	}

	public void setBrojClanova(int brojClanova) {
		this.brojClanova = brojClanova;
	}
	
	
}
