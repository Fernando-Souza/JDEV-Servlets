package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UsuarioBean;
import connection.SingleConnection;


public class TesteDao {

	private Connection connection;

	public TesteDao() {
		connection = SingleConnection.getConnection();
	}

	
	public List<UsuarioBean> listar() {

		String query = "Select * from usuario";
		PreparedStatement ps;
		List<UsuarioBean> usuarios =  new ArrayList<>();

		try {
			ps = connection.prepareStatement(query);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				
				UsuarioBean usuario = new UsuarioBean();
				
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));				
				usuarios.add(usuario);				
				
			}
			
			return usuarios;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
		

	}
	
	
}
