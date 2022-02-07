package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.SingleConnection;
import dao.DaoVersinadorBanco;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

    private static Connection connection;

    public FilterAutenticacao() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {

        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();

            String usuarioLogado = (String) session.getAttribute("usuario");
            String urlParaAutenticar = req.getServletPath();

            /* Validar se o usuário está logado, senão redirecionar para a tela de login */

            if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/LoginServlet")) {

                RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
                request.setAttribute("msg", "Por favor realize o login!");
                redireciona.forward(request, response);
                return;/* para a execução e redireciona para o login */

            } else {

                chain.doFilter(request, response);
            }

            connection.commit(); /* se não der nenhuma exceção, commita para o banco de dados */
        } catch (Exception e) {

            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("msg", e.getMessage());
            dispatcher.forward(request, response);
            try {
                connection.rollback();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        }
    }

    public void init(FilterConfig fConfig) throws ServletException {

        connection = SingleConnection.getConnection();
        DaoVersinadorBanco daoVersionador = new DaoVersinadorBanco();
        String caminhoPastaSQL = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;
        File[] filesql = new File(caminhoPastaSQL).listFiles();

        StringBuilder sql = new StringBuilder();

        for (File file : filesql) {

            boolean arquivoRodado = daoVersionador.isArquivoSqlRodado(file.getName());

            if (!arquivoRodado) {

                try (FileInputStream reader = new FileInputStream(file);) {

                    Scanner lerArquivo = new Scanner(reader, "UTF-8");

                    while (lerArquivo.hasNext()) {

                        sql.append(lerArquivo.nextLine());
                        sql.append("\n");
                    }

                    connection.prepareStatement(sql.toString()).execute();
                    daoVersionador.gravarArquivoSQLRodado(file.getName());
                    connection.commit();
                    lerArquivo.close();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

    }

}
