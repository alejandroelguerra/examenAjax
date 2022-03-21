package des.kanban.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Rol implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	Long id_rol;
	@Column(name="nombre_rol")
	String nombre_rol;
	
	@ManyToMany(mappedBy =  "roles")
	List<Usuario> usuarios;

	public Rol() {
	}

	public Rol(Long id_rol, String nombre_rol) {
		this.id_rol = id_rol;
		this.nombre_rol = nombre_rol;

	}

	public Long getId_rol() {
		return id_rol;
	}

	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_rol, nombre_rol, usuarios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(id_rol, other.id_rol) && Objects.equals(nombre_rol, other.nombre_rol)
				&& Objects.equals(usuarios, other.usuarios);
	}

	@Override
	public String toString() {
		return "Rol [id_rol=" + id_rol + ", nombre_rol=" + nombre_rol + ", usuarios=" + usuarios + "]";
	}
	
	
	
	
	
}
