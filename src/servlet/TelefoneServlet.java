package servlet;

import beans.BeanCursoJSP;
import beans.Telefone;
import beans.TipoTelefone;
import dao.TelefoneDao;
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
    private TelefoneDao telefoneDao = new TelefoneDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numero = request.getParameter("numero");
        TipoTelefone tipoTelefone = TipoTelefone.valor(request.getParameter("tipo"));

        BeanCursoJSP usuario = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

        Telefone fone = new Telefone();
        fone.setNumero(numero);
        fone.setTipo(tipoTelefone);
        fone.setUsuario(usuario.getId());

        telefoneDao.salvar(fone);

        /*Jogar usuario novamente na seção*/
        request.getSession().setAttribute("userEscolhido", usuario); // Colocando certo usuário na seção
        request.setAttribute("userEscolhido", usuario); // Colocando certo usuário na seção

        RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
        request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
        request.setAttribute("msg", "Telefone salvo com sucesso!");
        view.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao.endsWith("addFone")) {

            String user = request.getParameter("user");

            BeanCursoJSP usuario = usuarioDao.buscar(Long.valueOf(user));

            request.getSession().setAttribute("userEscolhido", usuario); // Colocando certo usuário na seção
            request.setAttribute("userEscolhido", usuario); // Colocando certo usuário na seção

            RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
            request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
            //  request.setAttribute("msg", "Telefone salvo com sucesso!");
            view.forward(request, response);

        } else if (acao.endsWith("deleteFone")) {

            String foneId = request.getParameter("foneId");
            telefoneDao.delete(Long.valueOf(foneId));

            BeanCursoJSP usuario = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

            RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
            request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
            request.setAttribute("msg", "Telefone removido com sucesso!");
            view.forward(request, response);
        }
    }
}
