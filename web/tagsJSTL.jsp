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


<c:out value="${'bem vindo ao JSTL'}"/>

<c:forEach var="n" begin="1" end="100">
    Item : ${n}
    <br/>

</c:forEach>

Quebrando string
<c:forTokens items="Bruno-Cesar-Vicente" delims="-" var="nome">

    Nome: <c:out value="${nome}"/>
    <br/>
</c:forTokens>


Montar uma url
<c:url value="/acessoLiberado.jsp" var="acesso">

    <c:param name="para1" value="111"/>
    <c:param name="para1" value="222"/>
    <c:param name="para1" value="333"/>
</c:url>

${acesso}


<c:set var="numero" value="${100/2}"/>

<c:if test="${numero >= 50}">
    <c:redirect url="https://www.google.com.br"/>

</c:if>

<c:if test="${numero < 50}">
    <c:redirect url="http://www.javaavancado.com"/>

</c:if>



<p/>
<p/>
<p/>
<p/>


</body>
</html>

