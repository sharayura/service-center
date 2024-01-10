package dao;

import entity.Role;
import entity.User;
import exception.DaoException;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Long, User>{

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private final static String DELETE_SQL = """
            DELETE from users
            where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            SELECT id, login, pwd, role, profit_percent
            FROM users
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private static String UPDATE_SQL = """
            UPDATE users
            SET
            login = ?,
            pwd = ?,
            role = ?,
            profit_percent = ?
            where id = ?;
            """;

    private final static String SAVE_SQL = """
            INSERT into users
            (login, pwd, role, profit_percent)
            values (?, ?, ?, ?);
            """;

    private static final String GET_BY_LOGIN_AND_PWD_SQL =
            "SELECT * FROM users WHERE login = ? AND pwd = ?";

    private static final String GET_BY_LOGIN_SQL =
            "SELECT * FROM users WHERE login = ?";

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
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var result = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (result.next()) {
                users.add(buildUser(result));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet result) throws SQLException {
        return new User(
                result.getLong("id"),
                result.getString("login"),
                result.getString("pwd"),
                Role.valueOf(result.getString("role")),
                result.getInt("profit_percent")
        );
    }

    @Override
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getRole().name());
            statement.setInt(4, user.getProfitPercent());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getRole().name());
            statement.setInt(4, user.getProfitPercent());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            User user = null;
            var result = statement.executeQuery();
            if (result.next()) {
                user = buildUser(result);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @SneakyThrows
    public Optional<User> findByLoginAndPassword(String login, String password) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(GET_BY_LOGIN_AND_PWD_SQL)) {
            statement.setString(1, login);
            statement.setString(2, password);

            var resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }
    @SneakyThrows
    public Optional<User> findByLogin(String login) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(GET_BY_LOGIN_SQL)) {
            statement.setString(1, login);

            var resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }
}
