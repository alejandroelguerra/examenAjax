package des.kanban.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Proyecto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_proyecto")
	Long id_proyecto;
	@Column
	String nombre;
	@Column
	String descripcion;
	
	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Tarea> tareas;
	
	@ManyToMany(mappedBy =  "proyectos")
	List<Usuario> trabajadores;
	
	

	public Proyecto() {
	}
	

	public Proyecto(Long id_proyecto, String nombre, String descripcion, List<Usuario> trabajadores) {
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.trabajadores = trabajadores;
	}
	
	



	public Proyecto(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	


	public Proyecto(String nombre, String descripcion, List<Usuario> trabajadores) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.trabajadores = trabajadores;
	}


	public Proyecto(Long id_proyecto, String nombre, String descripcion) {
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	public Proyecto(Long id_proyecto, String nombre, String descripcion, List<Tarea> tareas,
			List<Usuario> trabajadores) {
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tareas = tareas;
		this.trabajadores = trabajadores;
	}


	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	public List<Tarea> getTareas() {
		return tareas;
	}


	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}


	public List<Usuario> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Usuario> trabajadores) {
		this.trabajadores = trabajadores;
	}

	
}
