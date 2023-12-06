package dao;

import entity.Order;
import entity.Status;
import exception.DaoException;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Long, Order> {

    private static final OrderDao INSTANCE = new OrderDao();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    private final static String DELETE_SQL = """
            DELETE from orders
            where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            SELECT id, created, type, model, serial, problem, set, additional_info, status, client_id
            FROM orders
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private static String UPDATE_SQL = """
            UPDATE orders
            SET
            created = ?,
            type = ?,
            model = ?,
            serial = ?,
            problem = ?,
            set = ?,
            additional_info = ?,
            status = ?,
            client_id = ?
            where id = ?;
            """;

    private final static String SAVE_SQL = """
            INSERT into orders
            (created, type, model, serial, problem, set, additional_info, status, client_id)
            values (?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var result = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (result.next()) {
                orders.add(buildOrder(result));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order buildOrder(ResultSet result) throws SQLException {
        return new Order(
                result.getLong("id"),
                result.getTimestamp("created").toLocalDateTime(),
                result.getString("type"),
                result.getString("model"),
                result.getString("serial"),
                result.getString("problem"),
                result.getString("set"),
                result.getString("additional_info"),
                Status.valueOf(result.getString("status")),
                result.getLong("client_id")
        );
    }

    @Override
    public Order save(Order order) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getCreated()));
            statement.setString(2, order.getType());
            statement.setString(3, order.getModel());
            statement.setString(4, order.getSerial());
            statement.setString(5, order.getProblem());
            statement.setString(6, order.getSet());
            statement.setString(7, order.getAdditional_info());
            statement.setString(8, order.getStatus().name());
            statement.setLong(9, order.getClientId());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                order.setId(keys.getLong("id"));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Order order) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getCreated()));
            statement.setString(2, order.getType());
            statement.setString(3, order.getModel());
            statement.setString(4, order.getSerial());
            statement.setString(5, order.getProblem());
            statement.setString(6, order.getSet());
            statement.setString(7, order.getAdditional_info());
            statement.setString(8, order.getStatus().name());
            statement.setLong(9, order.getClientId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            Order order = null;
            var result = statement.executeQuery();
            if (result.next()) {
                order = buildOrder(result);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
