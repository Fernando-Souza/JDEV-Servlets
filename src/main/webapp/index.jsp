
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>curso-jsp</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="login" name="login"> <br>
				<br>Senha: <input type="password" id="senha" name="senha"><br>
				<br> <button type="submit" id="enviar" value="Salvar">SALVAR</button>
			</form>
		</div>
	</div>
</body>

</html>