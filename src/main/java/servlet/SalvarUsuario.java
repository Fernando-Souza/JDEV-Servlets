package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioBean;
import dao.UsuarioDao;

@WebServlet("/salvarUsuario")
public class SalvarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDao daoUser = new UsuarioDao();

	public SalvarUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		if (acao.equalsIgnoreCase("delete")) {
			daoUser.delete(user);
			RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
			request.setAttribute("usuarios", daoUser.listar());
			view.forward(request, response);
		}
		if (acao.equalsIgnoreCase("editar")) {

			UsuarioBean userBean = daoUser.consultar(user);
			RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
			request.setAttribute("user", userBean);
			view.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		UsuarioBean user = new UsuarioBean(id != null && !id.isEmpty() ? Long.parseLong(id) : null, nome, email, login,
				senha);

		if (id == null || id.isEmpty()) {

			daoUser.salvar(user);

		} else {

			daoUser.atualizar(user);

		}

		request.setAttribute("msg_sucesso", "Operação realizada com sucesso!");
		request.setAttribute("usuario", user);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	}

}
