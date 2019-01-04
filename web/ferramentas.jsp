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

<form action="receber-nome-2.jsp">
    <input type="text" id="nome" name="nome">
    <input type="submit" value="Enviar">
</form>


<!-- Tag declarativa
<%!
    int cont = 2;
    public int retorna(int n){
        return n * 3;
    }
%>

<%= retorna(cont)%>
<br/>
-->

<!-- Pegando dados do web.xml, através do objeto implícito application
<%= application.getInitParameter("estado")%>
-->

<!-- Objetos ímplicitos da seção de um usuário por exemplo-->
<% session.setAttribute("curso", "curso de jsp");%>

<!-- Exemplos directiva
<%@page import="java.util.Date"%>
<%= "Data de hoje é: "+new Date()%>

<%@page info="Página do curso de JSP" %>

<%@page errorPage="erro.jsp" %>
<%=100/2%>
-->

<!-- Exemplo de inclusao de páginas dentro de outras
<%@include file="pagina-include.jsp"%>
-->

<!--<myprefix:minhatag/> -->

<!-- Mandar parametro via jsp:forward
<jsp:forward page="receber-nome.jsp">
<jsp:param name="paramforward" value="curso de jsp site java"/>
</jsp:forward>
-->


</body>
</html>
