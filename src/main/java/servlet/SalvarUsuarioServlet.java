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
public class SalvarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UsuarioDao daoUser =  new UsuarioDao();
	
    public SalvarUsuarioServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		
		if(acao.equalsIgnoreCase("delete")) {
			daoUser.delete(user);
			RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
			request.setAttribute("usuarios",daoUser.listar());
			view.forward(request, response);
		}if(acao.equalsIgnoreCase("editar")) {
			
			
			UsuarioBean userBean = daoUser.consultar(user);
			RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
			request.setAttribute("user",userBean);
			view.forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		String login =  request.getParameter("login");
		String senha =  request.getParameter("senha");
		
		UsuarioBean user = new UsuarioBean(login,senha);
		user.setId(!id.isEmpty()?Long.parseLong(id):0);
		
		if(id==null || id.isEmpty()) {
			
			daoUser.salvar(user);
			
		}else {
			
			daoUser.atualizar(user);
			
		}
		
		
		RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
		request.setAttribute("usuarios", daoUser.listar());
		view.forward(request, response);
	}
	
	

}
