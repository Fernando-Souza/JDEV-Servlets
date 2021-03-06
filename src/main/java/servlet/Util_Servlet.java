package servlet;

import java.io.Serializable;

import beans.UsuarioBean;
import dao.UsuarioDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletUtil")
public class Util_Servlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;

    private UsuarioDao daoUser = new UsuarioDao();

    public Util_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getUsuarioLogado(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("usuario");

        return daoUser.consultarUserLogado(login).getId();
    }

    public UsuarioBean getUsuarioLogadoObjt(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("usuario");

        return daoUser.consultarUserLogado(login);
    }

}
