<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="beans.UsuarioBean"%>

<!DOCTYPE html>
<html lang="pt-BR">
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
														<form class="form-material" enctype="multipart/form-data" action="<%=request.getContextPath()%>/salvarUsuario" id="userForm" method="post">
															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" value="${newuser.id}"> <span class="form-bar"></span> <label class="float-label">ID</label>
															</div>
															<div class="form-group form-default input-group mb4">
																<div class="input-group-prepend">
																	<c:choose>
																		<c:when test="${newuser.fotouser != '' && newuser.fotouser !=null }">
																			<a href="<%= request.getContextPath()%>/salvarUsuario?acao=downloadFoto&id=${newuser.id}"> <img alt="imagem user" id="fotobase64" src="${newuser.fotouser }" width="70px">
																			</a>
																		</c:when>
																		<c:otherwise>
																			<img alt="imagem user" id="fotobase64" src="../assets/images/avatar-1.jpg" width="70px">
																		</c:otherwise>
																	</c:choose>
																</div>
																<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotobase64','fileFoto')" class="form-control-file"
																	style="margin-top: 15px; margin-left: 15px">
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome" class="form-control" required="required" value="${newuser.nome}"> <span class="form-bar"></span> <label class="float-label">Nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nascimento" id="nascimento" class="form-control" required="required" value="${newuser.nascimento}"> <span class="form-bar"></span> <label
																	class="float-label">Data de Nascimento</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="rendamensal" id="rendamensal" class="form-control" required="required" value="${newuser.rendamensal}"> <span class="form-bar"></span> <label
																	class="float-label">Renda Mensal</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email" class="form-control" required="required" value="${newuser.email}"> <span class="form-bar"></span> <label class="float-label">Email</label>
															</div>
															<div class="form-group form-default form-static-label">

																<select class="form-control" aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o perfil]</option>
																	<option value="Administrador"
																		<%UsuarioBean usuario = (UsuarioBean) request.getAttribute("newuser");
if (usuario != null && usuario.getPerfil().equals("Administrador")) {

    out.print(" ");
    out.print("selected=\"selected\"");
    out.print(" ");

}%>>Administrador</option>

																	<option value="Secretaria" <%if (usuario != null && usuario.getPerfil().equals("Secretaria")) {

    out.print(" ");
    out.print("selected=\"selected\"");
    out.print(" ");

}%>>Secretária</option>

																	<option value="Auxiliar" <%if (usuario != null && usuario.getPerfil().equals("Auxiliar")) {
    out.print(" ");
    out.print("selected =\"selected\"");
    out.print(" ");

}%>>Auxiliar</option>
																</select> <span class="form-bar"></span> <label class="float-label">Perfil</label>
															</div>

															<div class="form-group form-default form-float-label">

																<input type="radio" name="sexo" value="Masculino"
																	<%UsuarioBean user = (UsuarioBean) request.getAttribute("newuser");
if (user != null && user.getSexo().equals("Masculino")) {
    out.print(" ");
    out.print("checked=\"checked\"");
    out.print(" ");
}%>><label>Masculino</label>
																<input type="radio" name="sexo" value="Feminino"
																	<%if (user != null && user.getSexo().equals("Feminino")) {

    out.print(" ");
    out.print("checked=\"checked\"");
    out.print(" ");
}%>><label>Feminino</label>

															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input onblur="pesquisaCep()" type="text" name="cep" id="cep" class="form-control" required="required" autocomplete="off" value="${newuser.cep}">
																<label class="float-label">Cep</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="rua" id="rua" class="form-control" required="required" autocomplete="off" value="${newuser.rua}"> <label
																	class="float-label">Logradouro</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="bairro" id="bairro" class="form-control" required="required" autocomplete="off" value="${newuser.bairro}"> <label
																	class="float-label">Bairro</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="cidade" id="cidade" class="form-control" required="required" autocomplete="off" value="${newuser.cidade}"> <label
																	class="float-label">Cidade</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="uf" id="uf" class="form-control" required="required" autocomplete="off" value="${newuser.uf}"> <label
																	class="float-label">UF</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="numero" id="numero" class="form-control" required="required" autocomplete="off" value="${newuser.numero}"> <label
																	class="float-label">Numero</label>
															</div>

															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${newuser.login}"> <label
																	class="float-label">Login</label>
															</div>
															<div class="form-group form-default form-static-label">
																<span class="form-bar"></span> <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value=""> <label class="float-label">Senha</label>
															</div>
															<div class="card-header">
																<button type="submit" class="btn btn-success btn-round waves-effect waves-light" id="salvar">Cadastrar</button>
																<button type="button" class="btn btn-primary btn-round ewaves-effect waves-light" onClick="limpaForm()" id="novo">Novo</button>
																<button type="button" class="btn btn-danger  btn-round waves-effect waves-light" id="excluir" onClick="criaDeleteComAjax()">Excluir</button>
																<c:if test="${newuser.id>0}">
																	<a href="<%= request.getContextPath()%>/TelefoneServlet?acao=listar&iduser=${newuser.id}" class="btn btn-success btn-round waves-effect waves-light">Telefone</a>
																</c:if>
																<button type="button" class="btn btn-secondary btn-round" data-toggle="modal" data-target="#exampleModal">Pesquisar</button>
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
										<nav aria-label="Page navigation example">
											<ul class="pagination">
												<%
												int totalPagina = (int) request.getAttribute("totalPagina");
												for (int p = 0; p < totalPagina; p++) {
												    String url = request.getContextPath() + "/salvarUsuario?acao=paginar&pagina=" + (p * 5);
												    out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "#\">" + (p + 1) + "</a></li>");

												}
												%>

											</ul>
										</nav>
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
	<jsp:include page="modal.jsp"></jsp:include>
	<script type="text/javascript">
	
	var dataNascimento = $("#nascimento").val;	

	if(dataNascimento != null && dataNascimento !=''){
	
	var dateFormat =  new Date(dataNascimento);
	$("#nascimento").val(dateFormat.toLocaleDateString('pt-BR',{timezone:'UTC'}));
	
	}
	
	$( function() {
	      
	      $("#nascimento").datepicker({
	            dateFormat: 'dd/mm/yy',
	            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
	            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
	            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
	            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
	            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
	            nextText: 'Próximo',
	            prevText: 'Anterior'
	        });
	} );
	
	</script>

</body>

</html>