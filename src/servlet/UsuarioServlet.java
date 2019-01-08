package servlet;

import beans.BeanCursoJSP;
import dao.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/salvarUsuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        BeanCursoJSP usuario = new BeanCursoJSP();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuarioDao.salvar(usuario);

        //Após salvar um novo usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
        RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
        request.setAttribute("usuarios", usuarioDao.listar());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
