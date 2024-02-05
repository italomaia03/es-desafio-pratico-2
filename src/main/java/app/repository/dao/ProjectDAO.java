package app.repository.dao;

import app.models.Projeto;
import app.repository.ConnectionDB;
import app.repository.dao.interfaces.IProjetoDAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;

public class ProjectDAO implements IProjetoDAO{
    ConnectionDB conn;

    public ProjectDAO() {
        Dotenv dotenv = Dotenv.load();
        this.conn = ConnectionDB.getInstance(dotenv.get("SQLITE_URL"));
    }

    @Override
    public Projeto get(Long id) throws SQLException, ParseException {
        ResultSet result;
        Projeto projeto = null;
        Connection conn = this.conn.connect();
        String sql = "SELECT idProjeto, " +
                "nomeProjeto, " +
                "localizacaoProjeto, " +
                "escopoInicialProjeto, " +
                "dataInicialProjeto, " +
                "isFinalizado, " +
                "clienteProjeto " +
                "FROM projetos " +
                "WHERE idProjeto = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        result = statement.executeQuery();

        while (result.next()) {
            Long idProjeto = result.getLong("idProjeto");
            String nomeProjeto = result.getString("nomeProjeto");
            String localizacaoProjeto = result.getString("localizacaoProjeto");
            String escopoInicialProjeto = result.getString("escopoInicialProjeto");
            String dataInicioProjeto = result.getString("dataInicialProjeto");
            Long clienteProjetoId = result.getLong("clienteProjeto");
            boolean isFinalizado = result.getBoolean("isFinalizado");

            projeto = new Projeto(idProjeto,nomeProjeto, localizacaoProjeto, escopoInicialProjeto, dataInicioProjeto, isFinalizado, clienteProjetoId)  ;
        }

        statement.close();
        conn.close();
        return projeto;
    }

    @Override
    public List<Projeto> getAll() throws SQLException, ParseException {
        ResultSet result;
        List<Projeto> projetos = new ArrayList<>();
        Connection conn = this.conn.connect();
        String sql = "SELECT idProjeto, " +
                "nomeProjeto, " +
                "localizacaoProjeto, " +
                "escopoInicialProjeto, " +
                "dataInicialProjeto, " +
                "isFinalizado, " +
                "clienteProjeto " +
                "FROM projetos ";
        Statement statement = conn.createStatement();
        result = statement.executeQuery(sql);

        while (result.next()) {
            Long idProjeto = result.getLong("idProjeto");
            String nomeProjeto = result.getString("nomeProjeto");
            String localizacaoProjeto = result.getString("localizacaoProjeto");
            String escopoInicialProjeto = result.getString("escopoInicialProjeto");
            String dataInicioProjeto = result.getString("dataInicialProjeto");
            Long clienteProjetoId = result.getLong("clienteProjeto");
            boolean isFinalizado = result.getBoolean("isFinalizado");

            projetos.add(new Projeto(idProjeto,nomeProjeto, localizacaoProjeto, escopoInicialProjeto, dataInicioProjeto, isFinalizado, clienteProjetoId));
        }

        statement.close();
        conn.close();
        return projetos;
    }

    @Override
    public boolean save(Projeto projeto) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "INSERT INTO projetos (nomeProjeto, " +
                "localizacaoProjeto, " +
                "escopoInicialProjeto, " +
                "dataInicialProjeto, " +
                "clienteProjeto, " +
                "isFinalizado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, projeto.getNomeProjeto());
        statement.setString(2, projeto.getLocalizacaoProjeto());
        statement.setString(3, projeto.getEscopoInicial());
        statement.setString(4, formatter.format(projeto.getDataInicioPrevista()));
        statement.setLong(5, projeto.getClienteProjetoId());
        statement.setBoolean(6, projeto.getIsFinalizado());
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }

    @Override
    public boolean update(Long idProjeto, Projeto projeto) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "UPDATE projetos SET (nomeProjeto = ?," +
                " localizacaoProjeto = ?, " +
                "escopoInicialProjeto = ?, " +
                "dataInicialProjeto = ?, " +
                "clienteProjeto = ?," +
                "isFinalizado = ?) WHERE idProjeto = ?";

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, projeto.getNomeProjeto());
        statement.setString(2, projeto.getLocalizacaoProjeto());
        statement.setString(3, projeto.getEscopoInicial());
        statement.setString(4, formatter.format(projeto.getDataInicioPrevista()));
        statement.setLong(5, projeto.getClienteProjetoId());
        statement.setBoolean(6, projeto.getIsFinalizado());
        statement.setLong(7, idProjeto);
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }

    @Override
    public boolean delete(Long idProjeto) throws SQLException {
        boolean result;
        Connection conn = this.conn.connect();
        String sql = "DELETE FROM projetos WHERE idProjeto = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, idProjeto);
        result = statement.execute();
        statement.close();
        conn.close();

        return result;
    }
}
