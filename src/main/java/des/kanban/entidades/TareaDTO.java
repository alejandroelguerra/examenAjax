package des.kanban.entidades;

import java.io.Serializable;

public class TareaDTO implements Serializable {
	private Long id,idUsuario,  idProyecto;
	private String titulo ,descripcion,usuario,prioridad,estado ;
	
	
	
	
	public TareaDTO(Long id, Long idUsuario, Long idProyecto, String titulo, String descripcion, String usuario,
			String prioridad, String estado) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idProyecto = idProyecto;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.prioridad = prioridad;
		this.estado = estado;
	}


	public TareaDTO(Long id, String titulo, String descripcion, String usuario, String prioridad, String estado) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.prioridad = prioridad;
		this.estado = estado;
	}


	public TareaDTO() {
	}


	public TareaDTO(String titulo, String descripcion, String usuario, String prioridad, String estado) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.prioridad = prioridad;
		this.estado = estado;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	

}
