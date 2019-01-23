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
    <meta charset="UTF-8">
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <title>Cadastro de usuário</title>
    <link rel="stylesheet" href="resources/css/cadastro.css">

</head>
<body>

<a href="acessoLiberado.jsp">Início</a>
<a href="index.jsp">Sair</a>

<center><h1>Cadastro de telefones</h1></center>

<center><h3 style="color: green">${msg}</h3></center>

<%--Validação feita também com javascript--%>
<form action="salvarTelefones" id="formFone" onsubmit=" return validarCampos()" method="post"
      accept-charset="ISO-8859-1">
    <ul class="form-style-1">
        <li>

            <table>
                <tr>
                    <td>User:</td>
                    <td><input type="text" readonly="readonly" id="id" name="id"
                               value="${userEscolhido.id}"></td>
                    <td><input type="text" readonly="readonly" id="nome" name="nome"
                               value="${userEscolhido.nome}"></td>
                </tr>

                <tr>
                    <td>Número:</td>
                    <td><input type="text" id="numero" name="numero"></td>
                    <td>
                        <select name="tipo" id="tipo" style="width: 193px">
                            <option>Casa</option>
                            <option>Celular</option>
                            <option>Trabalho</option>
                        </select>
                    </td>
                </tr>


                <tr>
                    <td></td>
                    <td><input type="submit" value="Salvar"/>
                    </td>
                </tr>

            </table>


        </li>
    </ul>
</form>

<!-- Apresentando a tabela através do jstl-->
<div class="container">
    <table class="responsive-table">
        <caption>Usuários cadastrados</caption>
        <tr>
            <th>Id</th>
            <th>Número</th>
            <th>Tipo</th>
            <th>Excluir</th>
        </tr>

        <c:forEach items="${telefones}" var="fone">
            <tr>
                    <%--<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
                    <td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
                    <td style="width: 150px"><c:out value="${fone.tipo}"></c:out></td>--%>
                <td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png"
                                                                             alt="Excluir" title="Excluir"
                                                                             width="20px" height="20px"></a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--Validação do lado cliente, para economizar processamento desnecessário do lado do servidor--%>
<script type="text/javascript">

    function validarCampos() {

        if (document.getElementById("numero").value === '') {
            alert('Informe o numero');
            return false;

        }else if (document.getElementById("tipo").value === '') {
            alert('Informe o tipo');
            return false;
        }

        return true;
    }

    function limparCampos() {
        document.getElementById('formUser').reset();
        return false;
    }

    function isEdicao() {

        if (document.getElementById('id') !== '') {

            return true;
        } else {
            return false;
        }

    }

</script>

</body>
</html>
