package teste;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import beans.UsuarioBean;
import dao.TesteDao;

public class TesteBancoJdbc {

	@Test
	public void Listar() {
		TesteDao testeDAO = new TesteDao();
		List<UsuarioBean> usuarios = new ArrayList<>();

		usuarios = testeDAO.listar();

		usuarios.stream().forEach(System.out::println);

	}

}
