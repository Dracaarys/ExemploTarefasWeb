package ufrn.br.exemplotarefasweb;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsuarioController {
     @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        // Recupera os parâmetros do formulário de login
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Verifica as credenciais do usuário
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.fazerLogin(email, senha);

        // Se as credenciais forem válidas, redireciona para a página principal
        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogado", usuario); // Armazena o usuário na sessão
            response.sendRedirect("doListar"); // Redireciona para a página principal
        } else {
            // Se as credenciais forem inválidas, exibe o formulário de login novamente com uma mensagem de erro
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Login</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h2>Faça login</h2>");
            writer.println("<form method='post' action='/login'>");
            writer.println("<label for='email'>Email:</label><br>");
            writer.println("<input type='text' id='email' name='email'><br>");
            writer.println("<label for='senha'>Senha:</label><br>");
            writer.println("<input type='password' id='senha' name='senha'><br><br>");
            writer.println("<input type='submit' value='Login'>");
            writer.println("</form>");
            writer.println("<p style='color: red;'>Credenciais inválidas. Tente novamente.</p>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
