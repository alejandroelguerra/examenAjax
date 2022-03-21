package des.kanban.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.modelo.TareaRepository;

@Transactional
@Service
public class TareaService implements TareaServicio {

	@Autowired
	private TareaRepository daoTarea;
	@Override
	public Tarea obtenerTareaPorId(Long idTarea) {
		Optional<Tarea> t=daoTarea.findById(idTarea);
		return t.orElse(new Tarea());
	}

	@Override
	public Tarea crearTarea( Tarea tarea) {
		return daoTarea.save(tarea);
	}

	@Override
	public Tarea borrarTareaPorId(Long idTarea) {
		
		
		 daoTarea.deleteById(idTarea);
		return null;
	}

	@Override
	public Tarea modificarTarea(Tarea tarea) {
		
		return daoTarea.save(tarea);
	}

	@Override
	public ArrayList<Tarea> buscarTodos() {
		return (ArrayList<Tarea>) daoTarea.findAll();
	}

	@Override
	public ArrayList<Tarea> BuscarTareasUsuario(Long idUsuario) {
		
		return (ArrayList<Tarea>) daoTarea.findAllTareasUsuarioById(idUsuario);
	}





}
