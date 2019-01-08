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
    <title>Cadastro de usuário</title>
</head>
<body>

<h1>Cadastro de usuário</h1>

<form action="salvarUsuario" method="post">

    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" id="login" name="login"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" id="senha" name="senha"></td>
        </tr>
    </table>

    <input type="submit" value="Salvar">

</form>

<!-- Apresentando a tabela através do jstl-->
<table>
    <c:forEach items="${usuarios}" var="user">
        
        <tr>
            <td style="width: 150px"> <c:out value="${user.login}"></c:out></td>
            <td> <c:out value="${user.senha}"></c:out></td>
        </tr>
        
    </c:forEach>
</table>

</body>
</html>
