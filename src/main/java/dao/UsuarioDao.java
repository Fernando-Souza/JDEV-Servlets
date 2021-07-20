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
	
	public void salvar (UsuarioBean usuario) {
		
		String query = "insert into usuario (login,senha,nome, email) values (?,?,?,?)";
		
		try {
			PreparedStatement insert = conn.prepareStatement(query);
			
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());			
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
			
			insert.execute();
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	public List<UsuarioBean>listar(){
		
		List<UsuarioBean> listar =  new ArrayList<>();
		String query = "select * from usuario";
		try {
			PreparedStatement statment =  conn.prepareStatement(query);
			ResultSet resultSet = statment.executeQuery();
			
			while(resultSet.next()) {
				
				UsuarioBean usuario =  new UsuarioBean(resultSet.getLong("id"),resultSet.getString("nome"),resultSet.getString("email"),resultSet.getString("login"),resultSet.getString("senha"));
				listar.add(usuario);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listar;
		
	}
	
public void delete(String login) {
		
		String sql = "delete from usuario where login=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login);
			
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

public UsuarioBean consultar(String user){
	
	String query = "select * from usuario where login=?";
	UsuarioBean usuario=null; 
	
	try {
		PreparedStatement statment =  conn.prepareStatement(query);
		statment.setString(1,user);
		ResultSet resultSet = statment.executeQuery();
		
		if(resultSet.next()) {
			
			usuario =  new UsuarioBean(resultSet.getLong("id"),resultSet.getString("nome"),resultSet.getString("email"),resultSet.getString("login"),resultSet.getString("senha"));
						
		}
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return usuario;
	
}

public void atualizar(UsuarioBean user){
	
	String query = "update usuario set nome=?, email=?, login=?, senha=? where id=?";
		
	try {
		PreparedStatement statment =  conn.prepareStatement(query);
		statment.setString(1,user.getNome());
		statment.setString(2,user.getEmail());
		statment.setString(3,user.getLogin());
		statment.setString(4,user.getSenha());
		statment.setLong(5,user.getId());
		
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

}
