<%--
  Created by IntelliJ IDEA.
  User: brunocesar
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

<center><h1>Cadastro de usuário</h1></center>

<center><h3 style="color: red">${msgLogin}</h3></center>
<center><h3 style="color: red">${msgSenha}</h3></center>

<form action="salvarUsuario" id="formUser" method="post" accept-charset="ISO-8859-1">
    <ul class="form-style-1">
        <li>

            <table>
                <tr>
                    <td>Código:</td>
                    <td><input type="number" readonly="readonly" id="id" name="id" value="${user.id}"
                               class="field-long">
                    </td>
                </tr>

                <tr>
                    <td>Nome:</td>
                    <td><input type="text" id="nome" name="nome" value="${user.nome}"></td>
                </tr>
                <tr>

                <tr>
                    <td>Telefone:</td>
                    <td><input type="text" id="telefone" name="telefone" value="${user.telefone}"></td>
                </tr>

                <tr>
                    <td>Login:</td>
                    <td><input type="text" id="login" name="login" value="${user.login}"></td>
                </tr>

                <tr>
                    <td>Senha:</td>
                    <td><input type="password" id="senha" name="senha" value="${user.senha}"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
                </tr>

            </table>


        </li>
    </ul>
</form>

<!-- Apresentando a tabela através do jstl-->
<div class="container">
    <table class="responsive-table">
        <caption>Usuários cadastrados</caption>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Telefone</th>
            <th>Login</th>
            <th>Excluir</th>
            <th>Editar</th>
        </tr>
        <c:forEach items="${usuarios}" var="user">
            <tr>
                <td style="width: 150px"><c:out value="${user.id}"></c:out></td>
                <td style="width: 150px"><c:out value="${user.nome}"></c:out></td>
                <td style="width: 150px"><c:out value="${user.telefone}"></c:out></td>
                <td style="width: 150px"><c:out value="${user.login}"></c:out></td>
                <td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png"
                                                                                alt="Excluir" title="Excluir"
                                                                                width="20px" height="20px"></a></td>
                <td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.png"
                                                                                alt="Editar" title="Editar" width="20px"
                                                                                height="20px"></a></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
