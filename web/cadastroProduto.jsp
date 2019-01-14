<%--
  Created by IntelliJ IDEA.
  produto: brunocesar
  Date: 08/01/19
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <title>Cadastro de usuário</title>
    <link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>

<center><h1>Cadastro de produto</h1></center>

<center><h3 style="color: red">${msgNome}</h3></center>

<form action="salvarProduto" id="formProduto" method="post" accept-charset="ISO-8859-1">
    <ul class="form-style-1">
        <li>

            <table>
                <tr>
                    <td>Código:</td>
                    <td><input type="number" readonly="readonly" id="id" name="id" value="${produto.id}"
                               class="field-long">
                    </td>
                </tr>

                <tr>
                    <td>Nome:</td>
                    <td><input type="text" id="nome" name="nome" value="${produto.nome}"></td>
                </tr>
                <tr>

                <tr>
                    <td>Quantidade:</td>
                    <td><input type="number" id="quantidade" min="0" name="quantidade" value="${produto.quantidade}"></td>
                </tr>

                <tr>
                    <td>Valor R$:</td>
                    <td><input type="number" step="0.01" placeholder="0.00" min="0" id="valor" name="valor" value="${produto.valor}"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formproduto').action = 'salvarProduto?acao=reset'"></td>
                </tr>

            </table>


        </li>
    </ul>
</form>

<!-- Apresentando a tabela através do jstl-->
<div class="container">
    <table class="responsive-table">
        <caption>Produtos cadastrados</caption>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Quantidade</th>
            <th>Valor</th>
            <th>Excluir</th>
            <th>Editar</th>
        </tr>
        <c:forEach items="${produtos}" var="produto">
            <tr>
                <td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
                <td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
                <td style="width: 150px"><c:out value="${produto.quantidade}"></c:out></td>
                <td style="width: 150px"><c:out value="R$ ${produto.valor}"></c:out></td>
                <td><a href="salvarProduto?acao=delete&produto=${produto.id}"><img src="resources/img/excluir.png"
                                                                                alt="Excluir" title="Excluir"
                                                                                width="20px" height="20px"></a></td>
                <td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img src="resources/img/editar.png"
                                                                                alt="Editar" title="Editar" width="20px"
                                                                                height="20px"></a></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
