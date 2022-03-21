package des.kanban.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table
public class Tarea implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tarea")
	Long id_tarea;
	
	@ManyToOne()
    @JoinColumn(name = "id_proyecto")
	Proyecto proyecto;
	
	
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	Usuario usuarios;
	
	
	
	@Column
	String titulo;
	@Column
	String descripcion;
	@Column
	String prioridad;
	@Column
	String estado;
	
	
	public Tarea() {
	}


	public Tarea(Long id_tarea, Proyecto proyecto, String titulo, String descripcion, String prioridad, String estado) {
		this.id_tarea = id_tarea;
		this.proyecto = proyecto;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estado = estado;
	}
	


	public Tarea(Proyecto proyecto, Usuario usuarios, String titulo, String prioridad, String estado) {
		this.proyecto = proyecto;
		this.usuarios = usuarios;
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.estado = estado;
	}


	public Long getId_tarea() {
		return id_tarea;
	}


	public void setId_tarea(Long id_tarea) {
		this.id_tarea = id_tarea;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Usuario getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
