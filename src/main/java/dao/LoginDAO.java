package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class LoginDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection conn;
	private String login;
	private String senha;

	public LoginDAO() {

		conn = SingleConnection.getConnection();

	}

	public boolean validarLoginSenha(String login, String senha) throws SQLException {

		String query = "Select * from usuario where login= ? and senha = ?";
		PreparedStatement ps;

		ps = conn.prepareStatement(query);
		ps.setString(1, login);
		ps.setString(2, senha);

		ResultSet resultado = ps.executeQuery();

		if (resultado.next()) {

			this.login = login;
			this.senha = senha;

			return true;

		} else {

			this.login = null;
			this.senha = null;

			return false;

		}

	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
