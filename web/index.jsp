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
    <title>$Title$</title>
</head>
<body>

<form action="LoginServlet" method="post">

    Login:
    <input type="text" id="login" name="login">
    <br/>
    Senha:
    <input type="text" id="senha" name="senha">
    <br/>
    <input type="submit" value="Logar">

</form>

</body>
</html>
