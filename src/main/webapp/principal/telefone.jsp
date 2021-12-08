<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
														<h4 class="sub-title">Cad.Telefone</h4>
														<form class="form-material" action="<%=request.getContextPath()%>/TelefoneServlet" id="formFone" method="post">
															<div class="form-group form-default form-static-label">
																<input readonly="readonly" type="text" name="id" id="id" class="form-control" value="${usuario.id}"> <span class="form-bar"></span> <label class="float-label">ID</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input readonly="readonly" type="text" name="nome" id="nome" class="form-control" required="required" value="${usuario.nome}"> <span class="form-bar"></span> <label class="float-label">Nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero" class="form-control" required="required"> <span class="form-bar"></span> <label class="float-label">Numero</label>
															</div>
															<button type="submit" class="btn btn-success btn-round waves-effect waves-light" id="salvar">Salvar</button>
														</form>
													</div>
												</div>
											</div>
											
										</div>
										<span id="msg">${msg}</span>
										<!-- Page-body end -->
										<div style="height: 300px; overflow: scroll">
											<table class="table" id="tabelaresultadosview">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Numero</th>
														<th scope="col">Excluir</th>														

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${telefones}" var="tel">
														<tr>
															<td><c:out value="${tel.id}"></c:out></td>
															<td><c:out value="${tel.numero}"></c:out></td>
															<td><a class="btn btn-success" href="<%= request.getContextPath()%>/TelefoneServlet?acao=excluir&idtel=${tel.id}&userp=${usuario.id}">Excluir</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
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
</body>

</html>