package ufrn.br.exemplotarefasweb;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void atualizarUsuario(Usuario u) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement(
                    "UPDATE usuario SET nome=?, email=?, senha=? WHERE id=?");

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
        

            stmt.executeUpdate();
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            ex.printStackTrace();
            
        }
    }

    public Usuario fazerLogin(String email, String senha) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            connection = Conexao.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM usuario WHERE email=? AND senha=?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException | URISyntaxException ex) {
            ex.printStackTrace();
            // Pode tratar a exceção aqui
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return usuario;
    }
}
