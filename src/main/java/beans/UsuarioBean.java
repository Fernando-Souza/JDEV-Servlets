package beans;

public class UsuarioBean {

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	// private boolean admin = false;
	private String perfil;

	public UsuarioBean() {

	}

	public UsuarioBean(Long id, String nome, String email, String login, String senha, String perfil) {

		this(id, nome, email, login, perfil);
		this.senha = senha;

	}

	public UsuarioBean(Long id, String nome, String email, String login, String perfil) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.perfil = perfil;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {

		return perfil.equals("Administrador") ? true : false;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "UsuarioBean [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", perfil=" + perfil + "]";
	}

}
