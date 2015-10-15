package be.vdab.dao;

import be.vdab.entities.Klant;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KlantDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(KlantDAO.class.getName());

    private static final String BEGIN_SELECT = "SELECT * FROM klanten ";
    private static final String FIND_ONE_SQL = BEGIN_SELECT + "WHERE gebruikersnaam = ?";
    private static final String CREATE_SQL = "INSERT INTO klanten(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord) VALUES (?,?,?,?,?,?,?,?)";

    /**
     * Finds a specific client based on username.
     *
     * @param gebruikersnaam  the username parameter
     * @return the client (Klant)
     */
    public Klant findOne(String gebruikersnaam) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_SQL)) {
            preparedStatement.setString(1, gebruikersnaam);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetRijNaarKlanten(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

    /**
     * Adds a new client (Klant) record to the table 'klanten'.
     *
     * @param klant  the client (Klant) parameter
     */
    public void create(Klant klant) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, klant.getVoornaam());
            statement.setString(2, klant.getFamilienaam());
            statement.setString(3, klant.getStraat());
            statement.setString(4, klant.getHuisnr());
            statement.setString(5, klant.getPostcode());
            statement.setString(6, klant.getGemeente());
            statement.setString(7, klant.getGebruikersnaam());
            statement.setString(8, klant.getPaswoord());
            if (statement.executeUpdate() != 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    klant.setId(resultSet.getLong(1));
                }
            } else {
                logger.log(Level.SEVERE, "Probleem met database cultuurhuis", "Geen nieuwe klant aangemaakt");
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
