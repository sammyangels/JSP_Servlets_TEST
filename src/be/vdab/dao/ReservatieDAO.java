package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReservatieDAO extends AbstractDAO {
    private static final Logger logger = Logger.getLogger(ReservatieDAO.class.getName());

    private static final String INSERT_SQL = "INSERT INTO reservaties (klantid, voorstellingsid, plaatsen) VALUES (?,?,?)";
    private static final String UPDATE_SQL = "UPDATE voorstellingen SET vrijeplaatsen = ? WHERE id = ?";

    /**
     * Adds a new reservation to the table 'reservaties'.
     *
     * @param klantid  the client id parameter
     * @param voorstellingsid  the show id parameter
     * @param vrijeplaatsen  the available seats parameter
     * @param plaatsen  the reserved seats parameter
     * @return true if processing is successful, false if unsuccessful
     */
    public boolean nieuweReservatie(Long klantid, Long voorstellingsid, Long vrijeplaatsen, Long plaatsen) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            if (vrijeplaatsen >= plaatsen) {
                try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                    statement.setLong(1, vrijeplaatsen - plaatsen);
                    statement.setLong(2, voorstellingsid);
                    if (statement.executeUpdate() != 0) {
                        try (PreparedStatement statement2 = connection.prepareStatement(INSERT_SQL)) {
                            statement2.setLong(1, klantid);
                            statement2.setLong(2, voorstellingsid);
                            statement2.setLong(3, plaatsen);
                            if (statement2.executeUpdate() != 0) {
                                connection.commit();
                                return true;
                            } else return false;
                        }
                    } else return false;
                }
            } else return false;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }
}
