<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="beans.UsuarioBean" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-load.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbarmainmenu.jsp" />
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp" />
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cadastro de usuário</h4>
														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/salvarUsuario"
															id="userForm" method="post">
															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" value="${newuser.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID</label>
															</div>
															<div class="form-group form-default input-group mb4">
															<div class="input-group-prepend">
															<c:choose>
															<c:when test="${newuser.image64 != '' && newuser.image64 !=null }">
															<a href="<%= request.getContextPath()%>/salvarUsuario?acao=downloadFoto&id=${newuser.id}">
															<img alt="imagem user" id="fotobase64" src="${newuser.fotouser }" width="70px">
															</a>
															</c:when>
															<c:when test="${newuser.image64 == '' }">
															<img alt="imagem user" id="fotobase64" src="../assets/images/avatar-1.jpg" width="70px">
															</c:when>
															</c:choose>																						
															</div>
															<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotobase64','fileFoto')" class="form-control-file" style="margin-top: 15px; margin-left:15px">
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${newuser.nome}"> <span class="form-bar"></span>
																<label class="float-label">Nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	value="${newuser.email}"> <span
																	class="form-bar"></span> <label class="float-label">Email</label>
															</div>
															<div class="form-group form-default form-static-label">

																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o
																		perfil]</option>
																	<option value="Administrador" <%
																	
																	UsuarioBean usuario = (UsuarioBean) request.getAttribute("newuser");
																	
																	if(usuario!=null && usuario.getPerfil().equals("Administrador")){
																		
																		out.print(" ");
																		out.print("selected=\"selected\"");
																		out.print(" ");
																		
																	}%>>Administrador</option>
																	
																	<option value="Secretaria" <%																
																	
																	
																	if(usuario!=null && usuario.getPerfil().equals("Secretaria")){														
																
																		
																		out.print(" ");
																		out.print("selected=\"selected\"");
																		out.print(" ");
																		
																	}%>>Secretária</option>
																	<option value="Auxiliar" <%
																	
																	if(usuario!=null && usuario.getPerfil().equals("Auxiliar")){
																		
																		out.print(" ");
																		out.print("selected =\"selected\"");
																		out.print(" ");
																		
																	}%>>Auxiliar</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil</label>
															</div>
															<div class="form-group form-default form-float-label">
																<input type="radio" name="sexo" value="Masculino"<%
																
																UsuarioBean user = (UsuarioBean) request.getAttribute("newuser");
																
																if(user != null && user.getSexo().equals("Masculino")){
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																}
																
																%>>Masculino</>
																<input type="radio" name="sexo" value="Feminino" <%
																
																
																
																if(user != null && user.getSexo().equals("Feminino")){
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																}
																
																%>>Feminino</>
														    	
														    </div>

															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text"
																	name="login" id="login" class="form-control"
																	required="required" autocomplete="off"
																	value="${newuser.login}"> <label
																	class="float-label">Login</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="password"
																	name="senha" id="senha" class="form-control"
																	required="required" autocomplete="off" value="">
																<label class="float-label">Senha</label>
															</div>															
															<div class="card-header">
																<button type="submit"
																	class="btn btn-success btn-round waves-effect waves-light"
																	id="salvar">Cadastrar</button>
																<button type="button"
																	class="btn btn-primary btn-round ewaves-effect waves-light"
																	onClick="limpaForm()" id="novo">Novo</button>
																<button type="button"
																	class="btn btn-danger  btn-round waves-effect waves-light"
																	id="excluir" onClick="criaDeleteComAjax()">Excluir</button>
																<button type="button"
																	class="btn btn-secondary btn-round" data-toggle="modal"
																	data-target="#exampleModal">Pesquisar</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span>${msg_sucesso}</span>
										<div style="height: 300px; overflow: scroll">
											<table class="table" id="tabelaresultadosview">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${userLogins}" var="us">
														<tr>
															<td><c:out value="${us.id}"></c:out></td>
															<td><c:out value="${us.nome}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath()%>/salvarUsuario?acao=buscarEditar&id=${us.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBusca" aria-label="nome" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button"
								onclick="buscaUsuario();">Ir</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll">
						<table class="table" id="tabelaresultados">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>

				</div>
				<div class="modal-footer">
					<span id="totalresultados" style="position: relative"></span>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

	function visualizarImg(fotobase64,fileFoto){

		var preview = document.getElementById(fotobase64);
		var fileUser= document.getElementById(fileFoto).files[0];
		var reader =  new FileReader();
		reader.onloadend =  function(){

			preview.src = reader.result;

			};

			if(fileUser){

				reader.readAsDataURL(fileUser);
				}else{

					preview.src='';

					}




		}
		function verEditar(id) {

			var urlAction = document.getElementById('userForm').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function buscaUsuario() {
			var nomeBusca = document.getElementById("nomeBusca").value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

				var urlAction = document.getElementById('userForm').action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ "&acao=buscarUserAjax",
									success : function(response) {

										var json = JSON.parse(response);

										$('#tabelaresultados > tbody > tr')
												.remove();

										for (var p = 0; p < json.length; p++) {

											$('#tabelaresultados > tbody')
													.append(
															'<tr><td>'
																	+ json[p].id
																	+ '</td><td>'
																	+ json[p].nome
																	+ '</td><td><button onClick="verEditar('
																	+ json[p].id
																	+ ')" type="button" class="btn btn-info">Ver</button></td></tr>');

										}

										document
												.getElementById("totalresultados").textContent = "Resultados encontrados:"
												+ json.length;
									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao bbuscar o  usuário por nome'
											+ xhr.responseText);
								});

			}

		}

		function limpaForm() {

			var elementos = document.getElementById("userForm").elements;
			for (p = 0; p < elementos.length; p++) {

				elementos[p].value = "";

			}
		}

		function criarDelete() {

			if (confirm("Deseja realmente excluir os dados?")) {
				document.getElementById("userForm").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("userForm").submit();
			}
		}

		function criaDeleteComAjax() {
			if (confirm("Deseja realmente excluir os dados?")) {
				var urlAction = document.getElementById('userForm').action;
				var idUser = document.getElementById('id').value;
				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + "&acao=deletarajax",
					success : function(response) {
						limpaForm();
						alert(response)
					}

				}).fail(function(xhr, status, errorThrown) {
					alert('Erro ao deletar usuário por id' + xhr.responseText);
				});

			}

		}
	</script>
</body>

</html>