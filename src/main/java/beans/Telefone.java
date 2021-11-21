package beans;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String numero;
	private UsuarioBean usuario_pai_id;
	private UsuarioBean usuario_cad_id;

	public Telefone() {
	};

	public Telefone(int id, String numero, UsuarioBean usuario_pai_id, UsuarioBean usuario_cad_id) {

		this.id = id;
		this.numero = numero;
		this.usuario_pai_id = usuario_pai_id;
		this.usuario_cad_id = usuario_cad_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public UsuarioBean getUsuario_pai_id() {
		return usuario_pai_id;
	}

	public void setUsuario_pai_id(UsuarioBean usuario_pai_id) {
		this.usuario_pai_id = usuario_pai_id;
	}

	public UsuarioBean getUsuario_cad_id() {
		return usuario_cad_id;
	}

	public void setUsuario_cad_id(UsuarioBean usuario_cad_id) {
		this.usuario_cad_id = usuario_cad_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numero, usuario_cad_id, usuario_pai_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id) && Objects.equals(numero, other.numero)
				&& Objects.equals(usuario_cad_id, other.usuario_cad_id)
				&& Objects.equals(usuario_pai_id, other.usuario_pai_id);
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", usuario_pai_id=" + usuario_pai_id + ", usuario_cad_id="
				+ usuario_cad_id + "]";
	}

}
