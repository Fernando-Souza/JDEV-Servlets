<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<center>
		<h1>Cadastro de usuario</h1>
	</center>
	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>ID:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value=${user.id }></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value=${user.login }></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value=${user.senha } class="field-long"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Cadastrar" /></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">			
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td><c:out value="${user.login}"></c:out></td>
					<td><c:out value="${user.senha}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a></td>
				</tr>
			</c:forEach>



		</table>
	</div>
</body>
</html>