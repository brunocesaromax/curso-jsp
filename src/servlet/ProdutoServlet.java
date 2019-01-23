package servlet;

import beans.Produto;
import dao.ProdutoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/salvarProduto")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDao produtoDao = new ProdutoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        boolean flag = false;

        /*Clicou no botao cancelar*/
        if (acao != null && acao.equalsIgnoreCase("reset")) {

            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", produtoDao.listar());
            view.forward(request, response);

        } else {

            Long id;

            if (request.getParameter("id").isEmpty() || request.getParameter("id") == null) {
                id = null;
            } else {
                id = Long.valueOf(request.getParameter("id"));
            }

            String nome = request.getParameter("nome");
            Long quantidade = request.getParameter("quantidade") == null || request.getParameter("quantidade").isEmpty() ? Long.valueOf(0) : Long.valueOf(request.getParameter("quantidade"));
            Double valor = request.getParameter("valueOf") == null || request.getParameter("valueOf").isEmpty() ? Double.valueOf(0) : Double.valueOf(request.getParameter("valueOf"));

            if (nome == null || nome.isEmpty()){

                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", produtoDao.listar());
                request.setAttribute("msgNome","Campo de nome do produto é obrigatório!");
                view.forward(request, response);
                return;
            }


            Produto produto = new Produto(id,nome,quantidade,valor);

            if (id == null) {

                /*Validar que usuário seja único com o login*/
                Produto produtoBD = produtoDao.buscarByNome(nome);

                if (produtoBD == null) {
                    flag = true;
                } else {
                    request.setAttribute("msgNome", "Nome de produto já existe no sistema, tente outro!");
                }

                if (flag) {
                    produtoDao.salvar(produto);

                }else{//Manter os dados do produto que se quer cadastrar na tela
                    request.setAttribute("produto",produto);
                }

            } else {

                /*Para login*/
                Produto produtoBD = produtoDao.buscarByNome(nome);

                if (produtoBD != null && produtoBD.getId().equals(produto.getId())) {
                    flag = true;
                } else {
                    request.setAttribute("msgNome", "O nome do produto que foi modificado já existe no sistema para outro produto, tente outro!");
                }

                /*Estando tudo ok atualiza o produto*/
                if (flag) {
                    produto.setQuantidade(quantidade != null ? quantidade : 0);
                    produto.setQuantidade(valor != null ? quantidade : 0);
                    produtoDao.atualizar(produto);
                }
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", produtoDao.listar());
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        Long idProduto;

        if (request.getParameter("produto") == null || request.getParameter("produto").isEmpty()) {
            idProduto = null;
        } else {
            idProduto = Long.valueOf(request.getParameter("produto"));
        }


        if (acao.equalsIgnoreCase("delete")) {
            produtoDao.delete(idProduto);

            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", produtoDao.listar());
            view.forward(request, response);

        } else if (acao.equalsIgnoreCase("editar")) {
            Produto produto = produtoDao.buscar(idProduto);


            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produto", produto);
            view.forward(request, response);
        } else if (acao.equalsIgnoreCase("listartodos")) {

            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", produtoDao.listar());
            view.forward(request, response);
        }
    }
}
