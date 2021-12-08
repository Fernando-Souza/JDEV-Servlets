package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.UsuarioBean;
import dao.UsuarioDao;

@WebServlet(urlPatterns = { "/salvarUsuario" })
@MultipartConfig
public class UsuarioServlet extends Util_Servlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDao daoUser = new UsuarioDao();

	public UsuarioServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String idUser = request.getParameter("id");

		try {

			if (acao.equalsIgnoreCase("deletar") && idUser != null) {
				daoUser.delete(idUser);
				RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("usuarios", daoUser.listar(super.getUsuarioLogado(request), 0, 5));
				request.setAttribute("totalPagina", daoUser.numeroPaginas(super.getUsuarioLogado(request), 5));
				request.setAttribute("msg", "Usuário removido com sucesso!");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deletarajax") && idUser != null) {

				daoUser.delete(idUser);
				response.getWriter().write("Excluído com sucesso!");

			} else if (acao.equalsIgnoreCase("buscarUserAjax") && !acao.isEmpty()) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<UsuarioBean> usuariosDadosJson = daoUser.consultaByName(nomeBusca, super.getUsuarioLogado(request),
						0, 5);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(usuariosDadosJson);
				response.addHeader("totalPagina",
						"" + daoUser.listarPaginaPaginacao(nomeBusca, super.getUsuarioLogado(request), 5));
				response.getWriter().write(json);

			} else if (acao.equalsIgnoreCase("buscarUserAjaxPage") && !acao.isEmpty() && acao != null) {

				int nPagina = Integer.parseInt(request.getParameter("pagina"));
				String nomeBusca = request.getParameter("nomeBusca");

				List<UsuarioBean> usuariosDadosJson = daoUser.consultaByName(nomeBusca, super.getUsuarioLogado(request),
						nPagina, 5);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(usuariosDadosJson);
				response.addHeader("totalPagina",
						"" + daoUser.listarPaginaPaginacao(nomeBusca, super.getUsuarioLogado(request), 5));
				response.getWriter().write(json);

			} else if (acao.equalsIgnoreCase("buscarEditar") && !acao.isEmpty() && acao != null) {
				String id = request.getParameter("id");
				UsuarioBean user = daoUser.consultarById(id, super.getUsuarioLogado(request));
				List<UsuarioBean> usuariosList = daoUser.listar(super.getUsuarioLogado(request), 0, 5);
				request.setAttribute("userLogins", usuariosList);
				request.setAttribute("newuser", user);
				request.setAttribute("totalPagina", daoUser.numeroPaginas(super.getUsuarioLogado(request), 5));

				request.setAttribute("msg_sucesso", "Usuário em edição");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao.equalsIgnoreCase("listarUser") && acao != null && !acao.isEmpty()) {

				List<UsuarioBean> usuariosList = daoUser.listar(super.getUsuarioLogado(request), 0, 5);
				request.setAttribute("userLogins", usuariosList);
				request.setAttribute("totalPagina", daoUser.numeroPaginas(super.getUsuarioLogado(request), 5));
				request.setAttribute("msg_sucesso", "Usuários carregados");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao.equalsIgnoreCase("downloadFoto") && acao != null) {

				UsuarioBean usuario = daoUser.consultarById(idUser, super.getUsuarioLogado(request));
				if (usuario.getImage64() != null && !usuario.getImage64().isEmpty()) {

					response.setHeader("Content-Disposition",
							"attatchment;filename=arquivo." + usuario.getExtensaofoto());
					response.getOutputStream().write(Base64.decodeBase64(usuario.getImage64()));

				}
			} else if (acao.equalsIgnoreCase("paginar") && acao != null) {

				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				List<UsuarioBean> listapaginada = daoUser.ConsultaUsuarioPaginado(this.getUsuarioLogado(request),
						offset, 5);
				request.setAttribute("userLogins", listapaginada);
				request.setAttribute("totalPagina", daoUser.numeroPaginas(this.getUsuarioLogado(request), 5));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else {
				request.setAttribute("totalPagina", daoUser.numeroPaginas(super.getUsuarioLogado(request), 5));
				RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
				view.forward(request, response);
			}

			if (acao.equalsIgnoreCase("editar")) {

				UsuarioBean userBean = daoUser.consultarUsuario(idUser);
				RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("user", userBean);
				view.forward(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		String sexo = request.getParameter("sexo");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String uf = request.getParameter("uf");
		String numero = request.getParameter("numero");

		String msg = "Operação realizada com sucesso!";

		UsuarioBean user = new UsuarioBean(id != null && !id.isEmpty() ? Long.parseLong(id) : null, nome, email, login,
				senha, perfil, sexo);

		user.setCep(cep);
		user.setRua(rua);
		user.setBairro(bairro);
		user.setCidade(cidade);
		user.setUf(uf);
		user.setNumero(numero);

		if (ServletFileUpload.isMultipartContent(request)) {

			Part part = request.getPart("fileFoto"); // pegaa foto da tel
			byte[] foto = IOUtils.toByteArray(part.getInputStream());
			String imagebase64 = "data:image/" + part.getContentType().split("/")[1] + ";base64,"
					+ Base64.encodeBase64String(foto);

			user.setFotouser(imagebase64);
			user.setImage64(Base64.encodeBase64String(foto));
			user.setExtensaofoto(part.getContentType().split("/")[1]);

		}

		if (user.getId() == null & !daoUser.validarLogin(login)) {

			user = daoUser.salvar(user, super.getUsuarioLogado(request));

		} else {

			daoUser.atualizar(user);

			msg = "Login já cadastrado no sistema.Informe um login diferente.";

		}

		request.setAttribute("newuser", user);
		request.setAttribute("msg_sucesso", msg);
		request.setAttribute("totalPagina", daoUser.numeroPaginas(super.getUsuarioLogado(request), 5));
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	}

}