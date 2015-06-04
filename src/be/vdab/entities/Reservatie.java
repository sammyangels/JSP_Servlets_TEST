package be.vdab.entities;

/**
 * Created by Samuel Engelen on 13/05/2015.
 */
public class Reservatie {
    private Voorstelling voorstelling;
    private long plaatsen;

    public Reservatie() {
    }

    public Reservatie(Voorstelling voorstelling, long plaatsen) {
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public void setVoorstelling(Voorstelling voorstelling) {
        this.voorstelling = voorstelling;
    }

    public long getPlaatsen() {
        return plaatsen;
    }

    public void setPlaatsen(long plaatsen) {
        this.plaatsen = plaatsen;
    }
}
