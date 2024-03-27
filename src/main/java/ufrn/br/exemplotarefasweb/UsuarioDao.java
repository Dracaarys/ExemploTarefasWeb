package ufrn.br.exemplotarefasweb;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {
    
    public void cadastrarUsuario(Usuario u) {

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement(
                    "insert into usuario (nome, email, senha) values (?,?,?)");

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());

            stmt.executeUpdate();
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }
    }
    public Usuario(String email) {

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement(
                    "insert into usuario (nome, email, senha) values (?,?,?)");

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());

            stmt.executeUpdate();
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }
    }

}
