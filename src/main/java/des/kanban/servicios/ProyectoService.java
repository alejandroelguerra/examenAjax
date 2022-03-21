package des.kanban.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.UsuarioRepository;

@Transactional
@Service
public class ProyectoService implements ProyectoServicio {
	
	@Autowired
	private ProyectoRepository daoProyecto;
	@Autowired
	private UsuarioRepository daoUsuario;

	@Override
	public Proyecto obtenerProyectoPorId(Long idProyecto) {
		Optional<Proyecto> p=daoProyecto.findById(idProyecto);
		return p.orElse(new Proyecto());
	}

	@Override
	public Proyecto crearProyecto(Proyecto proyecto,Long id_Usuario) {
		//List<Usuario> user = (List<Usuario>) daoUsuario.findById(id_Usuario).orElse(null);
		//proyecto.setTrabajadores(user);

		return daoProyecto.save(proyecto);
	}

	@Override
	public Boolean borrarProyecto(Proyecto proyecto) {
		
		return null;
	}

	@Override
	public Proyecto modificarProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Proyecto> buscarTodos() {
		return (ArrayList<Proyecto>) daoProyecto.findAll();
		
	}

	@Override
	public Proyecto borrarProyectoPorId(Long idProyecto) {
		Optional<Proyecto>p=daoProyecto.findById(idProyecto);
		if(p.isPresent()) {
			daoProyecto.delete(p.get());
		}
		return p.orElse(new Proyecto());
	}

	@Override
	public ArrayList<Proyecto> buscarPorPatron(String patron) {
		
		return (ArrayList<Proyecto>) daoProyecto.findByNombreContainsOrDescripcionContains(patron, patron);
	}

}
