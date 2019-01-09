<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/01/19
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"/>

<html>
<head>
    <title>Tela inicial</title>
    <link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

<div class="login-page">
    <div class="form">

        <form action="LoginServlet" method="post" class="login-form">

            Login:
            <input type="text" id="login" name="login">
            <br/>
            Senha:
            <input type="password" id="senha" name="senha">
            <br/>
            <button type="submit" value="Logar">Logar</button>

        </form>

    </div>
</div>

</body>
</html>
