package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UsuarioBean;
import connection.SingleConnection;

public class LoginDAO {

	private Connection conn;

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

			return true;

		} else {

			return false;

		}

	}

}
