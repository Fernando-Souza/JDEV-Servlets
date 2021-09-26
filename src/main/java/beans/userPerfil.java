package beans;

public enum userPerfil {

	ADMINISTRADOR(1), AUXILIAR(2), SECRETARIO(3);

	int id;

	userPerfil(int id) {

		this.id = id;
	}

}
