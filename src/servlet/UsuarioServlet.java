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

        String acao = request.getParameter("acao");

        /*Clicou no botao cancelar*/
        if (acao != null && acao.equalsIgnoreCase("reset")) {

            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", usuarioDao.listar());
            view.forward(request, response);

        } else {

            Long id;

            if (request.getParameter("id").isEmpty() || request.getParameter("id") == null) {
                id = null;
            } else {
                id = Long.valueOf(request.getParameter("id"));
            }

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");

            BeanCursoJSP usuario = new BeanCursoJSP();
            usuario.setId(id);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setTelefone(telefone);

            if (id == null) {

                /*Validar que usuário seja único com o login*/
                BeanCursoJSP usuarioBD = usuarioDao.buscarByLogin(login);

                if (usuarioBD == null) {
                    usuarioDao.salvar(usuario);
                } else {
                    request.setAttribute("msgLogin", "Login já existe no sistema, tente outro!");
                }

                /*Validar que usuário seja único com a senha*/
                usuarioBD = usuarioDao.buscarBySenha(senha);

                if (usuarioBD == null) {
                    usuarioDao.salvar(usuario);
                } else {
                    request.setAttribute("msgSenha", "Senha já existe no sistema, tente outra!");
                }

            } else {

                /*Para login*/
                BeanCursoJSP usuarioBD = usuarioDao.buscarByLogin(login);

                if (usuarioBD != null && usuarioBD.getId().equals(usuario.getId())) {
                    usuarioDao.atualizar(usuario);
                } else {
                    request.setAttribute("msgLogin", "O login que foi modificado já existe no sistema para outro usuário, tente outro!");
                }

                /*Para senha*/
                usuarioBD = usuarioDao.buscarBySenha(senha);

                if (usuarioBD != null && usuarioBD.getId().equals(usuario.getId())) {
                    usuarioDao.atualizar(usuario);
                } else {
                    request.setAttribute("msgSenha", "A senha que foi modificada já existe no sistema para outro usuário, tente outra!");
                }

            }

            //Após salvar um novo usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", usuarioDao.listar());
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        Long idUser;

        if (request.getParameter("user") == null || request.getParameter("user").isEmpty()) {
            idUser = null;
        } else {
            idUser = Long.valueOf(request.getParameter("user"));
        }


        if (acao.equalsIgnoreCase("delete")) {
            usuarioDao.delete(idUser);

            //Após deletar um usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", usuarioDao.listar());
            view.forward(request, response);

        } else if (acao.equalsIgnoreCase("editar")) {
            BeanCursoJSP beanCursoJSP = usuarioDao.buscar(idUser);


            //Após deletar um usuário, redirecionar para a mesma página de cadastro e passar como parâmetro um lista de usuário para que possa ser apresentada
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("user", beanCursoJSP);
            view.forward(request, response);
        } else if (acao.equalsIgnoreCase("listartodos")) {

            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", usuarioDao.listar());
            view.forward(request, response);
        }
    }
}
