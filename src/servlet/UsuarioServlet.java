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
        boolean flag = false;

        /*Clicou no botao cancelar, Validando via JS*/
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
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");

            /*Primeiramente validar campos obrigatórios*/
            if (login == null || login.isEmpty()){

                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", usuarioDao.listar());
                request.setAttribute("msgLogin","Login deve ser informado!");
                view.forward(request, response);
                return;

            }else if (senha == null || senha.isEmpty()){

                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", usuarioDao.listar());
                request.setAttribute("msgLogin","Campo senha deve ser informado!");
                view.forward(request, response);
                return;

            }else if (nome == null || nome.isEmpty()){

                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", usuarioDao.listar());
                request.setAttribute("msgLogin","Campo nome deve ser informado!");
                view.forward(request, response);
                return;
            }


            /*Caso campos obrigatórios estejam presentes, prosseguir rotina*/
            BeanCursoJSP usuario = new BeanCursoJSP();
            usuario.setId(id);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setTelefone(telefone);
            usuario.setCep(cep);
            usuario.setRua(rua);
            usuario.setBairro(bairro);
            usuario.setCidade(cidade);
            usuario.setEstado(estado);

            if (id == null) {

                /*Validar que usuário seja único com o login*/
                BeanCursoJSP usuarioBD = usuarioDao.buscarByLogin(login);

                if (usuarioBD == null) {
                    flag = true;
                } else {
                    request.setAttribute("msgLogin", "Login já existe no sistema, tente outro!");
                }

                /*Validar que usuário seja único com a senha*/
                if (request.getAttribute("msgLogin") == null) {

                    usuarioBD = usuarioDao.buscarBySenha(senha);

                    if (usuarioBD == null) {
                        flag = true;
                    } else {
                        flag = false;
                        request.setAttribute("msgSenha", "Senha já existe no sistema, tente outra!");
                    }
                }

                if (flag) {
                    usuarioDao.salvar(usuario);
                    request.setAttribute("msgSucesso" , "Usuário cadastrado com sucesso!");

                }else{//Manter os dados do usuário que se quer cadastrar na tela
                    request.setAttribute("user",usuario);
                }

            } else {

                /*Para login*/
                BeanCursoJSP usuarioBD = usuarioDao.buscarByLogin(login);

                if (usuarioBD != null && usuarioBD.getId().equals(usuario.getId())) {
                    flag = true;// Pode atualizar, pois o login é o mesmo do usuário que se está manipulando
                } else {
                    request.setAttribute("msgLogin", "O login que foi modificado já existe no sistema para outro usuário, tente outro!");
                }

                /*Caso não haja erro quanto ao login, verificar senha*/
                if (request.getAttribute("msgLogin") == null) {

                    /*Para senha*/
                    usuarioBD = usuarioDao.buscarBySenha(senha);

                    if (usuarioBD == null || usuarioBD.getId().equals(usuario.getId())) {
                        flag = true; //Pode atualizar

                    } else {
                        flag = false;
                        request.setAttribute("msgSenha", "A senha que foi modificada já existe no sistema para outro usuário, tente outra!");
                    }
                }

                /*Estando tudo ok atualiza o usuario*/
                if (flag) {
                    usuarioDao.atualizar(usuario);
                    request.setAttribute("msgSucesso" , "Usuário atualizado com sucesso!");
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
