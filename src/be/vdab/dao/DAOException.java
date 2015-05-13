package be.vdab.dao;

/**
 * Created by Samuel Engelen on 5/05/2015.
 */
public class DAOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DAOException(Throwable cause) {
        super(cause);
    }
}
