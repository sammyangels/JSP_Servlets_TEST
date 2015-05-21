package be.vdab.entities;

/**
 * Created by Samuel Engelen on 13/05/2015.
 */
public class Genre {
    private long id;
    private String naam;

    public Genre(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
