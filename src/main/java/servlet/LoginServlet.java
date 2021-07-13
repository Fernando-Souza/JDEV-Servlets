package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDAO;


@WebServlet(urlPatterns={"/principal/LoginServlet","/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private LoginDAO daoLogin =  new LoginDAO();
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao!=null && !acao.isEmpty()&& acao.equalsIgnoreCase("logout")) {
			
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			
			redirecionar.forward(request, response);
				
		} else {
	
		doPost(request,response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			if(login != null && !login.isEmpty()&& senha != null && !senha.isEmpty()) {
				
				LoginDAO daoLogin = new LoginDAO();
				
				if (daoLogin.validarLoginSenha(login, senha)) {
				
				request.getSession().setAttribute("usuario", daoLogin.getLogin());
				
				if(url==null || url.equals("null")) {
					
					url = "principal/principal.jsp";
				}
				RequestDispatcher dispatcher =  request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
			}else {
				
				RequestDispatcher dispatcher =  request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg","Informe o login e senha corretamente!");
				dispatcher.forward(request, response);
				
			}
			
			}else {
				
				RequestDispatcher dispatcher =  request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg","Informe o login e senha corretamente!");
				dispatcher.forward(request, response);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher dispatcher =  request.getRequestDispatcher("/error.jsp");
			request.setAttribute("msg",e.getMessage());
			dispatcher.forward(request, response);
		}

	}

}
