package beans;

public class UsuarioBean {

	private Long id;
	private String login;
	private String senha;

	public UsuarioBean(String login, String senha) {

		this.login = login;
		this.senha = senha;

	}

	public UsuarioBean(Long id, String login, String senha) {

		this(login, senha);
		this.id = id;

	}

	public UsuarioBean() {

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

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

}
