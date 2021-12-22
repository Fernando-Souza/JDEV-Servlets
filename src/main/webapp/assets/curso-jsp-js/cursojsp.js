
$("#rendamensal").maskMoney({showSymbol:true,symbol:"R$ ",decimal:",",thousands:"."});

const formmatter = new Intl.NumberFormat('pt-BR',{
    currency:'BRL',
    minimumFractionDigits:2
});

$("#rendamensal").val(formatter.format($("#rendamensal").val))

$("#rendamensal").focus


var dataNascimento = $("#dataNascimento").val;
var dateFormat =  new Date(dataNascimento);
$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR',{timezone:'UTC'}));
$("nome").focus;


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



$("#numero").keypress(function(event){
				return/\d/.test(String.fromCharCode(event.keyCode));
			});
			
$("#cep").keypress(function(event){
				return/\d/.test(String.fromCharCode(event.keyCode));
			});			
			

function pesquisaCep() {

			var cep = $("#cep").val();

			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#cep").val(dados.cep);
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#uf").val(dados.uf);

						} //end if.
						else {
							//CEP pesquisado não foi encontrado.
							limpa_formulário_cep();
							alert("CEP não encontrado.");
						}

					});

		}

		function visualizarImg(fotobase64, fileFoto) {

			var preview = document.getElementById(fotobase64);
			var fileUser = document.getElementById(fileFoto).files[0];
			var reader = new FileReader();
			reader.onloadend = function() {

				preview.src = reader.result;

			};

			if (fileUser) {

				reader.readAsDataURL(fileUser);
			} else {

				preview.src = '';

			}

		}
		function verEditar(id) {

			var urlAction = document.getElementById('userForm').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function buscarUserPageAjax(url) {

			var urlAction = document.getElementById('userForm').action;
			var nomeBusca = document.getElementById('nomeBusca').value;

			$
					.ajax(
							{
								method : "get",
								url : urlAction,
								data : url,
								success : function(response, textStatus, xhr) {

									var json = JSON.parse(response);

									$('#tabelaresultados > tbody > tr')
											.remove();
									$("#ulPaginacaoUserAjax > li").remove();

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

									document.getElementById("totalresultados").textContent = "Resultados encontrados:"
											+ json.length;

									var totalPagina = xhr
											.getResponseHeader("totalPagina");

									for (var p = 0; p < totalPagina; p++) {
										var url = "nomeBusca="
												+ nomeBusca
												+ "&acao=buscarUserAjaxPage&pagina="
												+ (p * 5);
										$("#ulPaginacaoUserAjax")
												.append(
														"<li class='page-item'><a class='page-link' href='#' onclick='buscarUserPageAjax(\""
																+ url
																+ "\")'>"
																+ (p + 1)
																+ "</a></li>");

									}
								}

							}).fail(
							function(xhr, status, errorThrown) {
								alert('Erro ao bbuscar o  usuário por nome'
										+ xhr.responseText);
							});

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
									success : function(response, textStatus,
											xhr) {

										var json = JSON.parse(response);

										$('#tabelaresultados > tbody > tr')
												.remove();
										$("#ulPaginacaoUserAjax > li").remove();

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

										var totalPagina = xhr
												.getResponseHeader("totalPagina");

										for (var p = 0; p < totalPagina; p++) {
											var url = "nomeBusca="
													+ nomeBusca
													+ "&acao=buscarUserAjaxPage&pagina="
													+ (p * 5);
											$("#ulPaginacaoUserAjax")
													.append(
															"<li class='page-item'><a class='page-link' href='#' onclick='buscarUserPageAjax(\""
																	+ url
																	+ "\")'>"
																	+ (p + 1)
																	+ "</a></li>");

										}
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
			
			var urlAction = document.getElementById('userForm').action;
			window.location.href = urlAction + '?acao=listarUser';
			
			
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