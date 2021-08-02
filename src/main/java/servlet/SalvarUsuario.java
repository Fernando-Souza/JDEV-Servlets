package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.UsuarioBean;
import dao.UsuarioDao;

@WebServlet(urlPatterns = { "/salvarUsuario"})
public class SalvarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDao daoUser = new UsuarioDao();

	public SalvarUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String idUser = request.getParameter("id");
		String nomeBusca = request.getParameter("nomeBusca");

		try {

			if (acao.equalsIgnoreCase("deletar") && idUser != null) {
				daoUser.delete(idUser);
				RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("usuarios", daoUser.listar());
				request.setAttribute("msg", "Usuário removido com sucesso!");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deletarajax") && idUser != null) {

				daoUser.delete(idUser);
				response.getWriter().write("Excluído com sucesso!");

			} else if (acao.equalsIgnoreCase("buscarUserAjax") && nomeBusca != null) {

				List<UsuarioBean> usuariosDadosJson = daoUser.consultaByName(nomeBusca);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(usuariosDadosJson);
				response.getWriter().write(json);

			} else if (acao.equalsIgnoreCase("buscarEditar") && acao != null) {
				String id = request.getParameter("id");
				UsuarioBean user = daoUser.consultarById(id);
				List<UsuarioBean> usuariosList = daoUser.listar();
				request.setAttribute("userLogins", usuariosList);
				request.setAttribute("newuser", user);
				request.setAttribute("msg_sucesso", "Usuário em edição");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao.equalsIgnoreCase("listarUser") && acao != null) {

				List<UsuarioBean> usuariosList = daoUser.listar();
				request.setAttribute("userLogins", usuariosList);
				request.setAttribute("msg_sucesso", "Usuários carregados");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else {
				RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
				view.forward(request, response);
			}

			if (acao.equalsIgnoreCase("editar")) {

				UsuarioBean userBean = daoUser.consultar(idUser);
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

		String msg = "Operação realizada com sucesso!";

		UsuarioBean user = new UsuarioBean(id != null && !id.isEmpty() ? Long.parseLong(id) : null, nome, email, login,
				senha);

		if (user.getId() == null & !daoUser.validarLogin(login)) {

			user = daoUser.salvar(user);

		} else {

			msg = "Login já cadastrado no sistema.Informe um login diferente.";

		}

		request.setAttribute("newuser", user);
		request.setAttribute("msg_sucesso", msg);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	}

}
