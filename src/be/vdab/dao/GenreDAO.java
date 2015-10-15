package be.vdab.dao;

import be.vdab.entities.Genre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(GenreDAO.class.getName());

    private static final String BEGIN_SELECT = "select * from genres ";
    private static final String FIND_ALL_SQL = BEGIN_SELECT + "order by naam";
    private static final String READ_SQL = BEGIN_SELECT + "WHERE id=?";


    /**
     * Finds all entries in genres.
     *
     * @return a list of all genres
     */
    public List<Genre> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL)) {
            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(resultSetRijNaarGenre(resultSet));
            }
            return genres;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }


    private Genre resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
        return new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
    }

    /**
     * Finds a specific genre.
     *
     * @param id  the genre id (String) parameter
     * @return the genre, null if not processed
     */
    public Genre findByGenre(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetRijNaarGenre(resultSet);
                }
                return null;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

}
