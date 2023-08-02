package JWD64.Test.dto;

public class NastupDto {

	private Long id;
	private Long festivalId;
	private String festivalNaziv;
	private Long izvodjacId;
	private String izvodjacIme;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getFestivalId() {
		return festivalId;
	}
	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}
	public String getFestivalNaziv() {
		return festivalNaziv;
	}
	public void setFestivalNaziv(String festivalNaziv) {
		this.festivalNaziv = festivalNaziv;
	}
	public Long getIzvodjacId() {
		return izvodjacId;
	}
	public void setIzvodjacId(Long izvodjacId) {
		this.izvodjacId = izvodjacId;
	}
	public String getIzvodjacIme() {
		return izvodjacIme;
	}
	public void setIzvodjacIme(String izvodjacIme) {
		this.izvodjacIme = izvodjacIme;
	}
	public NastupDto() {
		super();
	}
	
	
}
