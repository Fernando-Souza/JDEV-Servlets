<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri = "http://java.sun.com/jsp/jstl/core" %>

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
														<form class="form-material"
															action="<%=request.getContextPath()%>/salvarUsuario"
															id="userForm" method="post">
															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" value="${newuser.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID</label>
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
							<td><a class="btn btn-success" href="<%= request.getContextPath()%>/salvarUsuario?acao=buscarEditar&id=${us.id}">Ver</a></td>							
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