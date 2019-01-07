package servlet;

import beans.BeanCursoJSP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long seriaVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BeanCursoJSP beanCursoJSP = new BeanCursoJSP();

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        RequestDispatcher dispatcher;

        if (beanCursoJSP.validarLoginSenha(login, senha)) {
            dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("acessoNegado.jsp");
        }

        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
