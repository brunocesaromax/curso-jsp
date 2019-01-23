package servlet;

import beans.BeanCursoJSP;
import beans.TipoTelefone;
import dao.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/salvarTelefones")
public class TelefoneServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numero = request.getParameter("numero");
        TipoTelefone tipoTelefone = TipoTelefone.valor(request.getParameter("tipo"));

        BeanCursoJSP usuario = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");

        BeanCursoJSP usuario = usuarioDao.buscar(Long.valueOf(user));

        request.getSession().setAttribute("userEscolhido",usuario); // Colocando certo usuário na seção
        request.setAttribute("userEscolhido",usuario); // Colocando certo usuário na seção

        RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
       // request.setAttribute("telefones",telefoneDao.listar());
        request.setAttribute("msg", "Telefone salvo com sucesso!");
        view.forward(request, response);

    }
}
