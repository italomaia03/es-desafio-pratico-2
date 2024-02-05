package app.repository.dao;

import app.models.Cliente;
import app.repository.ConnectionDB;
import app.repository.dao.interfaces.IClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
public class ClienteDAO implements IClienteDAO {
    ConnectionDB conn;

    public ClienteDAO() {
        Dotenv dotenv = Dotenv.load();
        this.conn = ConnectionDB.getInstance(dotenv.get("SQLITE_URL"));
    }

    @Override
    public Cliente get(Long id) throws SQLException {
        ResultSet result;
        Cliente cliente = null;
        Connection conn = this.conn.connect();
        String sql = "SELECT idCliente, nomeCliente FROM clientes WHERE idCliente = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        result = statement.executeQuery();

        while (result.next()) {
            Long idCliente = result.getLong("idCliente");
            String nomeCliente = result.getString("nomeCliente");
            cliente = new Cliente(idCliente, nomeCliente);
        }

        statement.close();
        conn.close();
        return cliente;
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        ResultSet result;
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = this.conn.connect();
        String sql = "SELECT idCliente, nomeCliente FROM clientes";
        Statement statement = conn.createStatement();
        result = statement.executeQuery(sql);

        while (result.next()) {
            Long idCliente = result.getLong("idCliente");
            String nomeCliente = result.getString("nomeCliente");
            clientes.add(new Cliente(idCliente, nomeCliente));
        }

        statement.close();
        conn.close();
        return clientes;
    }

    @Override
    public boolean save(Cliente cliente) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "INSERT INTO clientes (nomeCliente) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }

    @Override
    public boolean update(Long idCliente, Cliente cliente) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "UPDATE clientes SET (nomeCliente = ?) WHERE idCliente = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setLong(2, idCliente);
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }

    @Override
    public boolean delete(Long idCliente) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, idCliente);
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }
}
