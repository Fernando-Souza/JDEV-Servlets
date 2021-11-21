package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioBean;
import dao.TelefoneDao;
import dao.UsuarioDao;

@WebServlet("/TelefoneServlet")
public class TelefoneServlet extends Util_Servlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDao userDao = new UsuarioDao();
	private TelefoneDao telDao = new TelefoneDao();

	public TelefoneServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String iduser = request.getParameter("iduser");

		if (iduser != null && iduser.isEmpty()) {

			UsuarioBean usuario = userDao.consultarById(iduser);
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} else {
			List<UsuarioBean> logins = userDao.listar(super.getUsuarioLogado(request), 0, 5);
			request.setAttribute("totalPagina", userDao.numeroPaginas(super.getUsuarioLogado(request), 5));
			request.setAttribute("userLogins", logins);
			RequestDispatcher view = request.getRequestDispatcher("principal/usuario.jsp");
			view.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
