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

        Long id ;

        if (request.getParameter("id").isEmpty() || request.getParameter("id") == null ){
            id = null;
        }else{
            id= Long.valueOf(request.getParameter("id"));
        }

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        BeanCursoJSP usuario = new BeanCursoJSP();
        usuario.setId(id);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        if (id == null ){
            usuarioDao.salvar(usuario);
        }else{
            usuarioDao.atualizar(usuario);
        }

        //Após salvar um novo usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
        RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
        request.setAttribute("usuarios", usuarioDao.listar());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String user = request.getParameter("user");

        if (acao.equalsIgnoreCase("delete")) {
            usuarioDao.delete(user);

            //Após deletar um usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", usuarioDao.listar());
            view.forward(request, response);

        }else if (acao.equalsIgnoreCase("editar")){
            BeanCursoJSP beanCursoJSP = usuarioDao.buscar(user);


            //Após deletar um usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("user", beanCursoJSP);
            view.forward(request, response);
        }
    }
}
