package be.vdab.dao;

import be.vdab.entities.Genre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Samuel Engelen on 28/04/2015.
 */
public class GenresDAO extends AbstractDAO {
    private final static Logger logger = Logger.getLogger(GenresDAO.class.getName());

    private static final String BEGIN_SELECT = "select * from genres ";
    private static final String FIND_ALL_SQL = BEGIN_SELECT + "order by naam";
    private static final String READ_SQL = BEGIN_SELECT + "WHERE id=?";
//    private static final String GENRE = "where genre ";



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

    public Genre read(String id) {
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
