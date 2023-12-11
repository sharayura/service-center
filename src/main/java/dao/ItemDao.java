package dao;

import entity.Item;
import exception.DaoException;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDao implements Dao<Long, Item> {

    private static final ItemDao INSTANCE = new ItemDao();

    private ItemDao() {
    }

    public static ItemDao getInstance() {
        return INSTANCE;
    }

    private final static String DELETE_SQL = """
            DELETE from items
            where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            SELECT id, order_id, user_id, name, quantity, cost, price
            FROM items
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private static String UPDATE_SQL = """
            UPDATE items
            SET
            order_id = ?,
            user_id = ?,
            name = ?,
            quantity = ?,
            cost = ?,
            price = ?
            where id = ?;
            """;

    private final static String SAVE_SQL = """
            INSERT into items
            (order_id, user_id, name, quantity, cost, price)
            values (?, ?, ?, ?, ?, ?);
            """;

    private final static String ORDER_SUM = """
            SELECT sum(price) sum from items
            WHERE order_id = ?;
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
    public List<Item> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var result = statement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (result.next()) {
                items.add(buildItem(result));
            }
            return items;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Item buildItem(ResultSet result) throws SQLException {
        return new Item(
                result.getLong("id"),
                result.getLong("order_id"),
                result.getLong("user_id"),
                result.getString("name"),
                result.getInt("quantity"),
                result.getLong("cost"),
                result.getLong("price")
        );
    }

    @Override
    public Item save(Item item) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, item.getOrderId());
            statement.setLong(2, item.getUserId());
            statement.setString(3, item.getName());
            statement.setInt(4, item.getQuantity());
            statement.setLong(5, item.getCost());
            statement.setLong(6, item.getPrice());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                item.setId(keys.getLong("id"));
            }
            return item;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Item item) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1, item.getOrderId());
            statement.setLong(2, item.getUserId());
            statement.setString(3, item.getName());
            statement.setInt(4, item.getQuantity());
            statement.setLong(5, item.getCost());
            statement.setLong(6, item.getPrice());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Item> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            Item item = null;
            var result = statement.executeQuery();
            if (result.next()) {
                item = buildItem(result);
            }
            return Optional.ofNullable(item);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Long getOrderSum(Long orderId) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(ORDER_SUM)) {
            statement.setLong(1, orderId);
            Long sum = 0L;
            var result = statement.executeQuery();
            if (result.next()) {
                sum = result.getLong("sum");
            }
            return sum;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
