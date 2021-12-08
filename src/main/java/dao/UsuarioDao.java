package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	    if (consultarUsuario(usuario.getLogin()) == null) {

		query = "insert into usuario (login,senha,nome, email,imagem,tipofile,useradmin,usuario_id,sexo,cep,rua,bairro,cidade,uf,numero,datanascimento) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		insert.setString(10, usuario.getCep());
		insert.setString(11, usuario.getRua());
		insert.setString(12, usuario.getBairro());
		insert.setString(13, usuario.getCidade());
		insert.setString(14, usuario.getUf());
		insert.setString(15, usuario.getNumero());
		insert.setDate(16, Date.valueOf(usuario.getNascimento()));

		insert.execute();
		conn.commit();

		if (usuario.getImage64() != null && !usuario.getImage64().isEmpty()) {

		    query = "update usuario set imagem=?,tipofile=? where id =?";
		    insert = conn.prepareStatement(query);

		    insert.setString(1, usuario.getImage64());
		    insert.setString(2, usuario.getExtensaofoto());
		    insert.setLong(3, consultarUsuario(usuario.getLogin()).getId());

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

	return this.consultarUsuario(usuario.getLogin());
    }

    public void atualizar(UsuarioBean user) {

	String query = "update usuario set nome=?, email=?, imagem=?,tipofile=?,login=?, senha=?,"
		+ "useradmin=?, sexo=?,cep=?,rua=?,bairro=?,cidade=?,uf=?,numero=?,datanascimento=? where id=?";

	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setString(1, user.getNome());
	    statment.setString(2, user.getEmail());
	    statment.setString(3, user.getImage64());
	    statment.setString(4, user.getExtensaofoto());
	    statment.setString(5, user.getLogin());
	    statment.setString(6, user.getSenha());
	    statment.setString(7, user.getPerfil());
	    statment.setString(8, user.getSexo());
	    statment.setString(9, user.getCep());
	    statment.setString(10, user.getRua());
	    statment.setString(11, user.getBairro());
	    statment.setString(12, user.getCidade());
	    statment.setString(13, user.getUf());
	    statment.setString(14, user.getNumero());
	    statment.setDate(15, Date.valueOf(user.getNascimento()));
	    statment.setLong(16, user.getId());

	    statment.executeUpdate();
	    conn.commit();

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

    public List<UsuarioBean> listar(Long usuarioLogado, int offset, int n) {

	List<UsuarioBean> listar = new ArrayList<>();

	String query = "select * from usuario where usuario_id = ? order by id offset ? fetch first ? rows only";
	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(1, usuarioLogado);
	    statment.setInt(2, offset);
	    statment.setInt(3, n);

	    ResultSet resultSet = statment.executeQuery();

	    while (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String login = resultSet.getString("login");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipofile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		UsuarioBean usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipofile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

		listar.add(usuario);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return listar;

    }

    public List<UsuarioBean> listarUsuarios(Long usuarioLogado) {

	List<UsuarioBean> listar = new ArrayList<>();

	String query = "select * from usuario where usuario_id = ?";
	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(1, usuarioLogado);

	    ResultSet resultSet = statment.executeQuery();

	    while (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String login = resultSet.getString("login");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipofile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		UsuarioBean usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipofile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));
		usuario.setNascimento(resultSet.getDate("datanascimento")==null?null:resultSet.getDate("datanascimento").toLocalDate());

		listar.add(usuario);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return listar;

    }

    public int listarPaginaPaginacao(String nome, Long usuarioLogado, int limit) {

	Double npagina = null;

	String query = "select count(1) as total from usuario where upper(nome) like upper(?) and usuario_id = ?";
	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(2, usuarioLogado);
	    statment.setString(1, "%" + nome + "%");

	    ResultSet resultSet = statment.executeQuery();

	    resultSet.next();
	    Double cadastros = resultSet.getDouble("total");
	    npagina = cadastros / limit;
	    Double correcao = npagina % 2;

	    if (correcao > 0) {

		npagina++;

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return npagina.intValue();

    }

    public Integer numeroPaginas(Long usuarioLogado, int limit) {

	String query = "SELECT count(*) as total from usuario where usuario_id=?";
	Double npagina = null;
	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(1, usuarioLogado);
	    ResultSet resultSet = statment.executeQuery();
	    resultSet.next();
	    Double cadastros = resultSet.getDouble("total");
	    npagina = cadastros / limit;
	    Double correcao = npagina % 2;

	    if (correcao > 0) {

		npagina++;

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return npagina.intValue();

    }

    public List<UsuarioBean> ConsultaUsuarioPaginado(Long usuarioLogado, int offset, int limit) {

	List<UsuarioBean> listar = new ArrayList<>();

	String query = "select * from usuario where usuario_id = ? order by id offset ? fetch first ? row only";
	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(1, usuarioLogado);
	    statment.setInt(2, offset);
	    statment.setInt(3, limit);

	    ResultSet resultSet = statment.executeQuery();

	    while (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String login = resultSet.getString("login");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipofile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		UsuarioBean usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipofile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

		listar.add(usuario);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return listar;

    }

    public List<UsuarioBean> listarTodos(int offset, int n) {

	List<UsuarioBean> listar = new ArrayList<>();
	String query = "select * from usuario order by id offset ? fetch first ? rows";
	try {
	    PreparedStatement statment = conn.prepareStatement(query);

	    statment.setInt(1, offset);
	    statment.setInt(2, n);

	    ResultSet resultSet = statment.executeQuery();

	    while (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String login = resultSet.getString("login");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		UsuarioBean usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

		listar.add(usuario);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return listar;

    }

    public void delete(String id) {

	String sql = "delete from usuario where id=?";
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

    public UsuarioBean consultarUsuario(String login) {

	String query = "select * from usuario where upper(login) = upper(?)";
	UsuarioBean usuario = null;

	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setString(1, login);
	    ResultSet resultSet = statment.executeQuery();

	    if (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setString(1, login);
	    ResultSet resultSet = statment.executeQuery();

	    if (resultSet.next()) {

		Long id = resultSet.getLong("id");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));
		usuario.setNascimento(resultSet.getDate("datanascimento") == null ? null
			: LocalDate.parse(resultSet.getDate("datanascimento").toString(), formatter));

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

		String login = resultSet.getString("login");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		usuario = new UsuarioBean(Long.parseLong(id), nome, email, login, senha, perfil, sexo, image64,
			tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return usuario;

    }

    public UsuarioBean consultarById(String id, Long userLogado) {

	String query = "select * from usuario where id = ? and usuario_id=?";
	UsuarioBean usuario = null;

	try {
	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setLong(1, Long.parseLong(id));
	    statment.setLong(2, userLogado);

	    ResultSet resultSet = statment.executeQuery();

	    if (resultSet.next()) {

		String login = resultSet.getString("login");
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		usuario = new UsuarioBean(Long.parseLong(id), nome, email, login, senha, perfil, sexo, image64,
			tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));
	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return usuario;

    }

    public List<UsuarioBean> consultaByName(String nome, Long usuarioLogado, int offset, int n) {

	List<UsuarioBean> listar = new ArrayList<>();
	String query = "select * from usuario where upper(nome) like upper(?) and usuario_id = ? "
		+ "order by id offset ? fetch first ? rows only";

	try {

	    PreparedStatement statment = conn.prepareStatement(query);
	    statment.setString(1, "%" + nome + "%");
	    statment.setLong(2, usuarioLogado);
	    statment.setInt(3, offset);
	    statment.setInt(4, n);
	    ResultSet resultSet = statment.executeQuery();

	    while (resultSet.next()) {

		String login = resultSet.getString("login");
		Long id = resultSet.getLong("id");
		String email = resultSet.getString("email");
		String senha = resultSet.getString("senha");
		String sexo = resultSet.getString("sexo");
		String image64 = resultSet.getString("imagem");
		String tipoFile = resultSet.getString("tipofile");
		String perfil = resultSet.getString("useradmin");

		UsuarioBean usuario = new UsuarioBean(id, nome, email, login, senha, perfil, sexo, image64, tipoFile);

		usuario.setCep(resultSet.getString("cep"));
		usuario.setRua(resultSet.getString("rua"));
		usuario.setBairro(resultSet.getString("bairro"));
		usuario.setCidade(resultSet.getString("cidade"));
		usuario.setUf(resultSet.getString("uf"));
		usuario.setNumero(resultSet.getString("numero"));

		listar.add(usuario);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return listar;

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
