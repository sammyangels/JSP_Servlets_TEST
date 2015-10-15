package be.vdab.entities;

public class Klant {
    private long id;
    private String voornaam;
    private String familienaam;
    private String straat;
    private String huisnr;
    private String postcode;
    private String gemeente;
    private String gebruikersnaam;
    private String paswoord;

    public Klant() {
    }

    public Klant(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
    }

    public Klant(long id, String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String paswoord) {
        this(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klant)) return false;

        Klant klant = (Klant) o;

        return getId() == klant.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return voornaam + " " + familienaam + " " + straat + " " + huisnr + " " + postcode + " " + gemeente;
    }
}
