package be.vdab.dao;

import be.vdab.entities.Klant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Samuel Engelen on 21/05/2015.
 */
public class KlantDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(KlantDAO.class.getName());

    private static final String BEGIN_SELECT = "SELECT * FROM klanten ";
    private static final String FIND_ONE_SQL = BEGIN_SELECT + "WHERE gebruikersnaam = ?";
    private static final String INSERT_SQL = "INSERT INTO klanten"

    public Klant findOne(String gebruikersnaam) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_SQL)) {
            preparedStatement.setString(1, gebruikersnaam);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetRijNaarKlanten(resultSet);
                }
                return null;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

    private Klant resultSetRijNaarKlanten(ResultSet resultSet) throws SQLException {
        return new Klant(resultSet.getLong("id"), resultSet.getString("voornaam"),
                resultSet.getString("familienaam"), resultSet.getString("straat"),
                resultSet.getString("huisnr"), resultSet.getString("postcode"),
                resultSet.getString("gemeente"), resultSet.getString("gebruikersnaam"),
                resultSet.getString("paswoord"));
    }


}
