package servlet;

import java.io.IOException;
import java.util.List;

import beans.Telefone;
import beans.UsuarioBean;
import dao.TelefoneDao;
import dao.UsuarioDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String acao = request.getParameter("acao");
        String idpai = request.getParameter("userp");

        if (acao.equals("excluir") && acao != null && !acao.isEmpty()) {

            String idFone = request.getParameter("idtel");
            UsuarioBean pai = userDao.consultarById(idpai);
            telDao.deleteTelefone(Long.parseLong(idFone));
            List<Telefone> telefones = telDao.listaTelefone(pai.getId());
            request.setAttribute("telefones", telefones);
            request.setAttribute("msg", "Telefone exlcuído!");
            request.setAttribute("usuario", pai);
            request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
            return;

        }

        if (acao.equals("listar") && acao != null && !acao.isEmpty()) {

            UsuarioBean usuario = userDao.consultarById(iduser);
            List<Telefone> telefones = telDao.listaTelefone(usuario.getId());
            request.setAttribute("telefones", telefones);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

        } else {

            List<UsuarioBean> userLogins = userDao.listar(super.getUsuarioLogado(request), 0, 5);
            request.setAttribute("totalPagina", userDao.numeroPaginas(super.getUsuarioLogado(request), 5));
            request.setAttribute("userLogins", userLogins);
            request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario_pai_id = request.getParameter("id");
        String numero = request.getParameter("numero");

        if (telDao.existeFone(numero, Long.parseLong(usuario_pai_id)) == false) {

            Telefone telefone = new Telefone();

            telefone.setNumero(numero);
            telefone.setUsuario_pai_id(userDao.consultarById(usuario_pai_id));
            telefone.setUsuario_cad_id(super.getUsuarioLogadoObjt(request));

            telDao.salvarTelefone(telefone);

            request.setAttribute("msg", "Telefone salvo com sucesso!");

        } else {

            request.setAttribute("msg", "Telefone já cadastrado!");

        }

        List<Telefone> telefones = telDao.listaTelefone(Long.parseLong(usuario_pai_id));

        request.setAttribute("telefones", telefones);

        request.setAttribute("usuario", userDao.consultarById(usuario_pai_id));

        request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

    }

}
