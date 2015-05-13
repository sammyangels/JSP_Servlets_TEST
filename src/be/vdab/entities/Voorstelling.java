package be.vdab.entities;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Samuel Engelen on 13/05/2015.
 */
public class Voorstelling {
    private long id;
    private String titel;
    private String uitvoerders;
    private Date datum;
    private long genreid;
    private BigDecimal prijs;
    private long vrijeplaatsen;

    public Voorstelling(long id, String titel, String uitvoerders, Date datum, long genreid, BigDecimal prijs, long vrijeplaatsen) {
        this.id = id;
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = datum;
        this.genreid = genreid;
        this.prijs = prijs;
        this.vrijeplaatsen = vrijeplaatsen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public void setUitvoerders(String uitvoerders) {
        this.uitvoerders = uitvoerders;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public long getGenreid() {
        return genreid;
    }

    public void setGenreid(long genreid) {
        this.genreid = genreid;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public long getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public void setVrijeplaatsen(long vrijeplaatsen) {
        this.vrijeplaatsen = vrijeplaatsen;
    }
}
