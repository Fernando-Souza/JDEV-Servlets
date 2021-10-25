package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UsuarioBean;
import connection.SingleConnection;

public class UsuarioDao {

	private Connection conn;

	public UsuarioDao() {

		conn = SingleConnection.getConnection();
	}

	public UsuarioBean salvar(UsuarioBean usuario, Long usuarioLogado) {

		String query;
		PreparedStatement insert;

		try {

			if (consultar(usuario.getLogin()) == null) {

				query = "insert into usuario (login,senha,nome, email,imagem,tipofile,useradmin,usuario_id,sexo) values (?,?,?,?,?,?,?,?,?)";
				insert = conn.prepareStatement(query);

				insert.setString(1, usuario.getLogin());
				insert.setString(2, usuario.getSenha());
				insert.setString(3, usuario.getNome());
				insert.setString(4, usuario.getEmail());
				insert.setString(5, usuario.getImage64());
				insert.setString(6, usuario.getExtensaofoto());
				insert.setString(7, usuario.getPerfil());
				insert.setLong(8, usuarioLogado);
				insert.setString(9, usuario.getSexo());

				insert.execute();
				conn.commit();

				if (usuario.getImage64() != null && !usuario.getImage64().isEmpty()) {

					query = "update usuario set imagem=?,tipofile=? where id =?";
					insert = conn.prepareStatement(query);

					insert.setString(1, usuario.getImage64());
					insert.setString(2, usuario.getExtensaofoto());
					insert.setLong(3, consultar(usuario.getLogin()).getId());

					insert.executeUpdate();
					conn.commit();

				}

			} else {

				atualizar(usuario);

			}

		} catch (SQLException e) {

			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return this.consultar(usuario.getLogin());
	}

	public List<UsuarioBean> listar(Long usuarioLogado) {

		List<UsuarioBean> listar = new ArrayList<>();

		String query = "select * from usuario where useradmin != 'Administrador' and usuario_id = ?";
		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setLong(1, usuarioLogado);

			ResultSet resultSet = statment.executeQuery();

			while (resultSet.next()) {

				UsuarioBean usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"));
				listar.add(usuario);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listar;

	}

	public List<UsuarioBean> listar() {

		List<UsuarioBean> listar = new ArrayList<>();
		String query = "select * from usuario where useradmin != 'Administrador'";
		try {
			PreparedStatement statment = conn.prepareStatement(query);

			ResultSet resultSet = statment.executeQuery();

			while (resultSet.next()) {

				UsuarioBean usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"));
				listar.add(usuario);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listar;

	}

	public void delete(String id) {

		String sql = "delete from usuario where id=? and useradmin != 'Administrador'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(id));

			ps.execute();
			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public UsuarioBean consultar(String login) {

		String query = "select * from usuario where upper(login) = upper(?)";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, login);
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"), resultSet.getString("imagem"),
						resultSet.getString("tipofile"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	public UsuarioBean consultarUsuario(String login) {

		String query = "select * from usuario where upper(login) = upper(?)";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, login);
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	public UsuarioBean consultarUserLogado(String login) {

		String query = "select * from usuario where upper(login) = upper(?)";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, login);
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	public UsuarioBean consultarById(String id) {

		String query = "select * from usuario where id = ?";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setLong(1, Long.parseLong(id));
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	public UsuarioBean consultarById(String id, Long userLogado) {

		String query = "select * from usuario where id = ? and useradmin !='Administrador' and usuario_id=?";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setLong(1, Long.parseLong(id));
			statment.setLong(2, userLogado);

			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("useradmin"), resultSet.getString("sexo"), resultSet.getString("imagem"),
						resultSet.getString("tipofile"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	public List<UsuarioBean> consultaByName(String nome, Long usuarioLogado) {

		List<UsuarioBean> listar = new ArrayList<>();
		String query = "select * from usuario where upper(nome) like upper(?) and useradmin != 'Administrador' and usuario_id = ? ";

		try {

			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, "%" + nome + "%");
			statment.setLong(2, usuarioLogado);
			ResultSet resultSet = statment.executeQuery();

			while (resultSet.next()) {

				UsuarioBean usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("useradmin"),
						resultSet.getString("sexo"));
				listar.add(usuario);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listar;

	}

	public void atualizar(UsuarioBean user) {

		String query = "update usuario set nome=?, email=?, imagem=?,tipofile=? login=?, senha=?,useradmin=? sexo=? where id=?";

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, user.getNome());
			statment.setString(2, user.getEmail());
			statment.setString(3, user.getFotouser().split(",")[1]);
			statment.setString(4, user.getExtensaofoto());
			statment.setString(5, user.getLogin());
			statment.setString(6, user.getSenha());
			statment.setString(7, user.getPerfil());
			statment.setString(8, user.getSexo());
			statment.setLong(9, user.getId());

			statment.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public boolean validarLogin(String login) {

		String query = "select count(1) > 0 as existe from usuario where upper(login) = upper(?)";

		boolean teste = false;

		try {

			PreparedStatement st = conn.prepareStatement(query);

			st.setString(1, login);

			ResultSet resultado = st.executeQuery();

			resultado.next();

			teste = resultado.getBoolean("existe");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return teste;

	}

	public void gravarImagem(String file, String login, String senha) {

		String query = "UPDATE USUARIO SET imagem = ?, tipofile=? WHERE login = ? AND senha=?";
		String base_64 = file.split(",")[1];
		String tipo_arquivo = file.split(",")[0].split(";")[0].split("/")[1];

		try {
			PreparedStatement insert = conn.prepareStatement(query);

			insert.setString(1, base_64);
			insert.setString(2, tipo_arquivo);
			insert.setString(3, login);
			insert.setString(4, senha);

			insert.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
