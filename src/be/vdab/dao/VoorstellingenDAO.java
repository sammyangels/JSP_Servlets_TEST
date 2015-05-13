package be.vdab.dao;

import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Samuel Engelen on 28/04/2015.
 */
public class VoorstellingenDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(VoorstellingenDAO.class.getName());

    private static final String BEGIN_SELECT = "SELECT * FROM voorstellingen ";
    private static final String FIND_ALL_SQL = BEGIN_SELECT + "WHERE genreid = ? AND datum >= NOW() ORDER BY datum";
//    private static final String GENRE = "where genre ";


    public List<Voorstelling> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL)) {
            List<Voorstelling> voorstellingen = new ArrayList<>();
            while (resultSet.next()) {
                voorstellingen.add(resultSetRijNaarVoorstellingen(resultSet));
            }
            return voorstellingen;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
            throw new DAOException(ex);
        }
    }

    private Voorstelling resultSetRijNaarVoorstellingen(ResultSet resultSet) throws SQLException {
        return new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"),
                                resultSet.getString("uitvoerders"), resultSet.getDate("datum"),
                                resultSet.getLong("genreid"), resultSet.getBigDecimal("prijs"),
                                resultSet.getLong("vrijeplaatsen"));
    }

//    public Genre read(long id) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
//            statement.setLong(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSetRijNaarPizza(resultSet);
//                }
//                return null;
//            }
//        } catch (SQLException ex) {
//            logger.log(Level.SEVERE, "Probleem met database pizzaluigi", ex);
//            throw new DAOException(ex);
//        }
//    }

//    public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
//        try (Connection connection = dataSource.getConnection();
//            PreparedStatement statement = connection.prepareStatement(FIND_BY_PRIJS_BETWEEN_SQL)) {
//            List<Pizza> pizzas = new ArrayList<>();
//            statement.setBigDecimal(1, van);
//            statement.setBigDecimal(2, tot);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    pizzas.add(resultSetRijNaarPizza(resultSet));
//                }
//                return pizzas;
//            }
//        } catch (SQLException ex) {
//            logger.log(Level.SEVERE, "Probleem met database pizzaluigi", ex);
//            throw new DAOException(ex);
//        }
//    }
//
//    public void create(Pizza pizza) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, pizza.getNaam());
//            statement.setBigDecimal(2, pizza.getPrijs());
//            statement.setBoolean(3, pizza.isPikant());
//            statement.executeUpdate();
//            try (ResultSet resultSet = statement.getGeneratedKeys()) {
//                resultSet.next();
//                pizza.setId(resultSet.getLong(1));
//            }
//        } catch (SQLException ex) {
//            logger.log(Level.SEVERE, "Probleem met database pizzaluigi", ex);
//            throw new DAOException(ex);
//        }
//    }
}
