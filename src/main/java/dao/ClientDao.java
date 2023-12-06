package dao;

import entity.Client;
import exception.DaoException;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Long, Client>{

    private static final ClientDao INSTANCE = new ClientDao();

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }

    private final static String DELETE_SQL = """
            DELETE from clients
            where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            SELECT id, name, phone, additional_info
            FROM clients
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private static String UPDATE_SQL = """
            UPDATE clients
            SET
            name = ?,
            phone = ?,
            additional_info = ?
            where id = ?;
            """;

    private final static String SAVE_SQL = """
            INSERT into clients
            (name, phone, additional_info)
            values (?, ?, ?);
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
    public List<Client> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var result = statement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (result.next()) {
                clients.add(buildClient(result));
            }
            return clients;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Client buildClient(ResultSet result) throws SQLException {
        return new Client(
                result.getLong("id"),
                result.getString("name"),
                result.getString("phone"),
                result.getString("additional_info")
        );
    }

    @Override
    public Client save(Client client) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAdditionalInfo());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                client.setId(keys.getLong("id"));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Client client) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAdditionalInfo());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            Client client = null;
            var result = statement.executeQuery();
            if (result.next()) {
                client = buildClient(result);
            }
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
