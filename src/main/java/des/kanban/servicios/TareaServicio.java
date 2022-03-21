package des.kanban.servicios;

import java.util.ArrayList;
import java.util.List;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;

public interface TareaServicio {

	public Tarea obtenerTareaPorId(Long idTarea);
	
	public Tarea crearTarea(Tarea tarea);
	
	public Tarea  borrarTareaPorId(Long idTarea);
	
	public Tarea modificarTarea(Tarea tarea);
	
	public ArrayList<Tarea>buscarTodos();
	
	public ArrayList<Tarea>BuscarTareasUsuario(Long idUsuario);
	
}
