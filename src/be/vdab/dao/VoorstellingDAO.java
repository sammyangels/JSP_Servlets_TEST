package be.vdab.dao;

import be.vdab.entities.Voorstelling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Samuel Engelen on 28/04/2015.
 */
public class VoorstellingDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(VoorstellingDAO.class.getName());

    private static final String BEGIN_SELECT = "SELECT * FROM voorstellingen ";
    private static final String FIND_ALL_SQL = BEGIN_SELECT + "WHERE genreid = ? AND datum >= NOW() ORDER BY datum";
    private static final String FIND_ONE_SQL = BEGIN_SELECT + "WHERE id = ?";

    /**
     * Finds all shows per genre.
     *
     * @param genreId  the genre id parameter
     * @return a list of shows
     */
    public List<Voorstelling> findAllPerGenre(Long genreId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            preparedStatement.setLong(1, genreId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Voorstelling> voorstellingen = new ArrayList<>();
                while (resultSet.next()) {
                    voorstellingen.add(resultSetRijNaarVoorstellingen(resultSet));
                }
                return voorstellingen;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

    /**
     * Finds a specific show.
     *
     * @param id  the show id parameter
     * @return the show
     */
    public Voorstelling findOne(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetRijNaarVoorstellingen(resultSet);
                }
                return null;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

    private Voorstelling resultSetRijNaarVoorstellingen(ResultSet resultSet) throws SQLException {
        return new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"),
                resultSet.getString("uitvoerders"), resultSet.getTimestamp("datum"),
                resultSet.getLong("genreid"), resultSet.getBigDecimal("prijs"),
                resultSet.getLong("vrijeplaatsen"));
    }
}