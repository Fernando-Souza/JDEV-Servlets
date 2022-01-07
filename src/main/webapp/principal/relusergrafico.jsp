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
														<h4 class="sub-title">Relatório do usuário</h4>
														<form class="form-material" action="<%=request.getContextPath()%>/salvarUsuario" id="formUser" method="get">
															<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao" value="imprimirRelatorioUser">
															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label class="sr-only" for="dataInicial">Data Inicial</label> <input type="text" class="form-control mb-2" id="dataInicial" name="dataInicial" value="${dataInicial}">
																</div>
																<div class="col-auto">
																	<div class="input-group mb-2">
																		<label class="sr-only" for="dataFinal">Data Final</label><input type="text" class="form-control" id="dataFinal" name="dataFinal" value="${dataFinal}">
																	</div>
																</div>
																<div class="col-auto">
																	<button type="button" onclick="gerarGrafico();" class="btn btn-primary mb-2">Gerar Gráfico</button>																	
																</div>
															</div>

														</form>
														<div style="height: 700px; overflow: scroll">
															<div>
																<canvas id="myChart"></canvas>
															</div>
														</div>
													</div>
												</div>

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
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

		<script type="text/javascript">		
		
		
			function gerarGrafico() {
				
				const myChart = new Chart(
	                    document.getElementById('myChart'),
	                    {
	                        type: 'line',
	                        data: {
	                            labels: [
	                                'January',
	                                'February',
	                                'March',
	                                'April',
	                                'May',
	                                'June',
	                              ],
	                            datasets: [{
	                              label: 'Gráfico de média salarial por tipo',
	                              backgroundColor: 'rgb(255, 99, 132)',
	                              borderColor: 'rgb(255, 99, 132)',
	                              data: [0, 10, 5, 2, 20, 30, 45],
	                            }]
	                          },
	                        options: {}
	                      }
	                  );

			}

			$(function() {

				$("#dataInicial").datepicker(
						{
							dateFormat : 'dd/mm/yy',
							dayNames : [ 'Domingo', 'Segunda', 'Terça',
									'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
							dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S',
									'D' ],
							dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
									'Qui', 'Sex', 'Sáb', 'Dom' ],
							monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
									'Abril', 'Maio', 'Junho', 'Julho',
									'Agosto', 'Setembro', 'Outubro',
									'Novembro', 'Dezembro' ],
							monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
									'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out',
									'Nov', 'Dez' ],
							nextText : 'Próximo',
							prevText : 'Anterior'
						});
			});

			$(function() {

				$("#dataFinal").datepicker(
						{
							dateFormat : 'dd/mm/yy',
							dayNames : [ 'Domingo', 'Segunda', 'Terça',
									'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
							dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S',
									'D' ],
							dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
									'Qui', 'Sex', 'Sáb', 'Dom' ],
							monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
									'Abril', 'Maio', 'Junho', 'Julho',
									'Agosto', 'Setembro', 'Outubro',
									'Novembro', 'Dezembro' ],
							monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
									'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out',
									'Nov', 'Dez' ],
							nextText : 'Próximo',
							prevText : 'Anterior'
						});
			});
		</script>
</body>

</html>