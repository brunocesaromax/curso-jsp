<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/01/19
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"/>

<html>
<head>
    <title>Cabecalho</title>
</head>
<body>

<%--Recuperando atributos de um certo Bean enviado via post--%>
<jsp:setProperty name="calcula" property="*"/>
<h3>Seja bem-vindo ao sistema JSP!</h3>


</body>
</html>
