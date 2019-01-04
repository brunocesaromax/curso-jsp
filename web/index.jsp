<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/01/19
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<h1>Bem-vindo ao curso de JSP</h1>
<%= "Seu sucesso garantido"%>

<form action="receber-nome.jsp">
    <input type="text" id="nome" name="nome">
    <input type="submit" value="Enviar">
</form>

<!-- Tag declarativa-->
<%!
    int cont = 2;
    public int retorna(int n){
        return n * 3;
    }
%>

<%= retorna(cont)%>

</body>
</html>
