# Završni projekat na kursu Java Web Development (JWD64) - FTN informatika

  Aplikacija koja omogućava prikaz nastupa i izvođača na različitim festivalima.
  Okačen je neizmenjen projekat, u potpunosti onako kako je urađen na testu.
  Za izradu projekta polaznici imaju 3 sata.

# Tehnologije
  Backend- SpringBoot
  Frontend- React
_______________________________________________________________________________
# OBJAŠNJENJE PROJEKTA

- za pokretanje frontend dela aplikacije- npm install

# Login
  ROLE_ADMIN 
  korisnicčko ime: miroslav    lozinka: miroslav
  
  ROLE_KORISNIK
  korisnicčko ime: tamara      lozinka: tamara

  NEPRIJAVLJENI KORISNIK
  - samo prikaz i pretraga nastupa

# Izvođači:
  Admin- prikaz i dodavanje novog
  Korisnik- samo prikaz

# Nastupi:
  Korisnik i Admin -Prikaz, dodavanje i pretraga 
  Admin- Brisanje
  Klikom na bilo koji red iz tabele, iskače alert sa prikazom broja nastupa datog izvođača
  
  Implementirana pretraga nastupa po izvođačima i festivalima 
  Implementirana paginacija
  Implementirano dodavanje- sa ograničenjem da jedan festival ne može imati izvođače koji dolaze iz iste države (NastupiController -> @PostMapping)
