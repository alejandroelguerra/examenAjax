package des.kanban.servicios;

import java.util.ArrayList;
import java.util.List;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;

public interface ProyectoServicio {

	public Proyecto obtenerProyectoPorId(Long idProyecto);
	
	public Proyecto crearProyecto(Proyecto proyecto,Long id_usuario);
	
	public Boolean  borrarProyecto(Proyecto proyecto);
	
	public Proyecto modificarProyecto(Proyecto proyecto);
	
	public ArrayList<Proyecto>buscarTodos();
	
	public  Proyecto borrarProyectoPorId(Long idProyecto);
	
	public ArrayList<Proyecto>buscarPorPatron(String patron);
	
}