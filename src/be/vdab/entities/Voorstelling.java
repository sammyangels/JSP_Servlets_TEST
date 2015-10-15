package be.vdab.entities;

import java.math.BigDecimal;
//import java.sql.Date;
import java.sql.Timestamp;

public class Voorstelling {
    private long id;
    private String titel;
    private String uitvoerders;
    private Timestamp datum;
    private long genreid;
    private BigDecimal prijs;
    private long vrijeplaatsen;

    public Voorstelling(long id, String titel, String uitvoerders, Timestamp datum, long genreid, BigDecimal prijs, long vrijeplaatsen) {
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

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public java.util.Date getDatum() {
        return datum;
    }

    public long getGenreid() {
        return genreid;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public void setVrijeplaatsen(long vrijeplaatsen) {
        this.vrijeplaatsen = vrijeplaatsen;
    }
}
