package servlet;

import dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long seriaVersionUID = 1L;
    private LoginDao loginDao = new LoginDao();

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        RequestDispatcher dispatcher = null;

        try {
            if (loginDao.validarLogin(login, senha)) {
                dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("acessoNegado.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
