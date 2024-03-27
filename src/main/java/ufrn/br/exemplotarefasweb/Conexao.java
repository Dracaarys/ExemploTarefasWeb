package ufrn.br.exemplotarefasweb;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /*
    Default env:
    DATABASE_HOST=localhost;DATABASE_PORT=5432;DATABASE_NAME=tarefadb;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=postgres
    */
    public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = System.getenv("localhost");
        String dbPort = System.getenv("8080");
        String dbName = System.getenv("clientedb");

        String username = System.getenv("postgres");
        String password = System.getenv("1234");
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}