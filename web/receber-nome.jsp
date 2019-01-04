<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/01/19
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- Passagem de parâmetros de uma tela para outra -->
<%="Nome recebido: " + request.getParameter("nome") %>

<br/>
<%= request.getContextPath()%> <!-- Vários objetos implícitos, bastante utilizado-->
<% response.sendRedirect("http://www.uol.com.br"); %> <!--  Redirecionar para outra página web ou do sistema mesmo-->


</body>
</html>
