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

<%--Quando se chama a servlet via href costuma-se ser enviado para o doGet da servlet--%>
<a href="salvarUsuario?acao=listartodos"><img src="resources/img/user.png" title="Cadastrar novos usuários" style="border: solid; color: #1a1a1a" width="100px" height="100px"></a>
<a href="salvarProduto?acao=listartodos"><img src="resources/img/produto.png" title="Cadastrar novos produtos"  style="border: solid; color: #1a1a1a" width="100px" height="100px"></a>

</body>
</html>
