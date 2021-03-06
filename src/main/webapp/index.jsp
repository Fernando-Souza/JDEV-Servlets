
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>curso-jsp</title>
<style type="text/css">

form{
position: absolute;
top:40%;
left:40%;
right:30%;
}

h1{
position: absolute;
top:15%;
left:38%;
right:20%;

}

.msg{color: red;}

</style>
</head>
<body>
<h1>Bem vindo ao curso JSP</h1>
	<form action="<%= request.getContextPath() %>/LoginServlet" method="post" class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">			
		<div class="mb-3">
			<label class="form-label">Login</label> 
			<input type="text" id="login" name="login" class="form-control" required="required">
			<div class="invalid-feedback">
             Campo Obrigatório!
      		</div>
      		<div class="valid-feedback">
             Ok!
      		</div>
		</div>		
		<div class="mb-3">
			<label class="form-label">Senha</label> 
			<input type="password"	id="senha" name="senha" class="form-control" required="required">
			<div class="invalid-feedback">
             Campo obrigatório!
      	</div>
      	<div class="invalid-feedback">
             Ok!
      		</div>			
		</div>	
		
		<button type="submit" id="enviar" value="Salvar" class="btn btn-primary">Acessar</button>		
		
		<div>
		<h6 class="msg">${msg}</h6>
		</div>
	</form>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
		
		<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()




		</script>
		
</body>

</html>