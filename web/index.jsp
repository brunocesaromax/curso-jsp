<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/01/19
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page" />

<html>
<head>
    <title>$Title$</title>
</head>
<body>

<h1>Index</h1>

<!-- Usando páginas diferentes para se usar no cabecalho e no rodapé de uma página
<jsp:include page="cabecalho.jsp"/>
<h3>Counteúdo da página</h3>
<jsp:include page="rodape.jsp"/>
-->

<%= calcula.calcula(8000)%>


</body>
</html>
