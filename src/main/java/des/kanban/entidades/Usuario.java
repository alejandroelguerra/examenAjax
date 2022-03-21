package des.kanban.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	Long id_usuario;
	@Column (name="contrasena")
	String password;
	@Column(name="nombre_usuario")
	String nombre_usuario;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_rol", nullable = false))
	private Set<Rol> roles=new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "proyecto_usuario", joinColumns = @JoinColumn(name = "id_proyecto", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_usuario", nullable = false))
	List<Proyecto> proyectos;
	
	/*revisar
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	List<Tarea> tareas;
	//@OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
	*/
	public Usuario() {
		
	}

	public Usuario(Long id_usuario, String password, String nombre_usuario, Set<Rol> roles, List<Proyecto> proyectos,
			List<Tarea> tareas) {
		this.id_usuario = id_usuario;
		this.password = password;
		this.nombre_usuario = nombre_usuario;
		this.roles = roles;
	}

	public Usuario(String password, String nombre_usuario) {
		this.password = password;
		this.nombre_usuario = nombre_usuario;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public boolean anadirRol(Rol rol) {
		rol.getUsuarios().add(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}

	public boolean anadirProyecto(Proyecto proyecto) {
		proyecto.getTrabajadores().add(this);
		return getProyectos().add(proyecto);
	}

	public void eliminarProyecto(Proyecto proyecto) {
		this.proyectos.remove(proyecto);
		proyecto.getTrabajadores().remove(this);
	}
	
}
