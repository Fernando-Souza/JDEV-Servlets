<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
																	class="form-control" 
																	value="${newuser.id}"> <span class="form-bar"></span>
																<label class="float-label">ID</label>
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
																	onClick="limpaForm()" id="novo">Editar</button>																
																<button type="button"
																	class="btn btn-danger  btn-round waves-effect waves-light"
																	id="excluir" onClick="criarDelete()">Excluir</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span>${msg_sucesso}</span>
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
	<script type="text/javascript">

	
		function limpaForm() {

			var elementos = document.getElementById("userForm").elements;
			for (p = 0; p < elementos.length; p++) {

				elementos[p].value = "";

			}
		}
		
		function criarDelete() {

			if(confirm("Deseja realmente excluir os dados?")){
			document.getElementById("userForm").method = 'get';
			document.getElementById("acao").value = 'deletar';
			document.getElementById("userForm").submit();
			}
		}
				
	</script>
</body>

</html>