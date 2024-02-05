package app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB connectionDB = null;
    private final String URL;
    private String user;
    private String password;
    private String database;
    private String sgbd;
    private String hostname;
    private Integer PORT;

    private ConnectionDB(String user, String password, String database, String sgbd, String hostname, Integer PORT) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.sgbd = sgbd;
        this.hostname = hostname;
        this.PORT = PORT;
        this.URL = String.format("jdbc:%s://%s:%s@%s:%d/%s", this.sgbd, this.user, this.password, this.hostname, this.PORT, this.database);
    }

    private ConnectionDB(String url) {
        this.URL = url;
        System.out.println("SUCESSO: Conexão com a base de dados foi estabelecida.");
    }

    public static ConnectionDB getInstance(String URL) {
        if (connectionDB == null) {
            connectionDB = new ConnectionDB(URL);
        }
        return connectionDB;
    }
    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.URL);
        } catch (SQLException e) {
            System.err.println("ERRO: Não foi possível conectar com a base de dados.");
            System.err.println("ERRO: Por favor, verifique as configurações de conexão");
            System.err.println("ERRO: e reinicie a aplicação");
            System.err.println(e);
            System.exit(0);
        }
        return conn;
    }

    public static void main(String[] args) {
        ConnectionDB connectionDB1 = ConnectionDB.getInstance("/home/italo/Documents/ufca/3-semestre/engenharia-software/desafio-pratico-2/src/test/java/app/db/app.db");
        connectionDB1.connect();
    }
}
