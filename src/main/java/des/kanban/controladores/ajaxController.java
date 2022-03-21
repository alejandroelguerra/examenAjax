package des.kanban.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.TareaDTO;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller

public class ajaxController {

	@Autowired
	private TareaServicio tareaService;

	@Autowired
	private ProyectoServicio proyectoService;
	@Autowired
	private UsuarioServicio usuarioService;

	@ResponseBody
	@GetMapping(value = "/todos/proyecto/{idProyecto}")
	public List<TareaDTO> obtenerTodos(@PathVariable(name = "idProyecto") Long id) {

		Proyecto p = proyectoService.obtenerProyectoPorId(id);
		List<TareaDTO> tareasDto = new ArrayList<TareaDTO>();
		
		List<Tarea> tareas = p.getTareas();
		
		
		for (Tarea t: tareas) {
			TareaDTO dto = new TareaDTO(
					t.getId_tarea(),
					t.getUsuarios().getId_usuario(),
					t.getProyecto().getId_proyecto(),
					t.getTitulo(),
				t.getDescripcion(),
				t.getUsuarios().getNombre_usuario(),
				t.getPrioridad(),
				t.getEstado());
			tareasDto.add(dto);
		}
		
		
//		tareaService.obtenerTareasPorProyecto(p).stream().forEach(t -> tareas.add(new TareaDTO (t.getId_tarea(), t.getTitulo(),
//				t.getDescripcion(), t.getUsuarios(), t.getPrioridad(), t.getEstado())));
		return  tareasDto;
	}

	@ResponseBody
	@PostMapping(value = "/anadir/{idProyecto}")
	public ResponseEntity<TareaDTO> guardarTarea(@RequestBody Tarea tarea, HttpSession session,@PathVariable Long idProyecto) {
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		//ResponseEntity<TareaDTO> resp;
		Usuario u = usuarioService.obtenerUsuarioPorId(idUsuario);
		Proyecto p=proyectoService.obtenerProyectoPorId(idProyecto);
		tarea.setUsuarios(u);
		tarea.setProyecto(p);
		Tarea t = tareaService.crearTarea(tarea);
		TareaDTO dto = new TareaDTO(
				t.getId_tarea(),
				t.getUsuarios().getId_usuario(),
				t.getProyecto().getId_proyecto(),
				t.getTitulo(),
			t.getDescripcion(),
			t.getUsuarios().getNombre_usuario(),
			t.getPrioridad(),
			t.getEstado());
		
		return new ResponseEntity<TareaDTO>(dto, HttpStatus.OK);

	}
	@ResponseBody
	@PostMapping("/modificar/{idTarea}")
	public ResponseEntity<TareaDTO> postmodificarTarea (@PathVariable Long idTarea, @RequestBody Map<String, String> json) {
		
		String Nombre = json.get("titulo");
		String Prioridad = json.get("prioridad");
		String Trabajador = json.get("empleado");
		String Estado = json.get("estado"); 
		String Descripcion = json.get("descripcion");
		Usuario u = usuarioService.obtenerUsuarioPorId(Long.parseLong(Trabajador));
		Tarea tarea = tareaService.obtenerTareaPorId(idTarea);
		tarea.setUsuarios(u);
		tarea.setTitulo(Nombre);
		tarea.setPrioridad(Prioridad);
		//tarea.setEstado(Estado);
		tarea.setDescripcion(Descripcion);
		TareaDTO dto = new TareaDTO(
				tarea.getId_tarea(),
				tarea.getUsuarios().getId_usuario(),
				tarea.getProyecto().getId_proyecto(),
				tarea.getTitulo(),
				tarea.getDescripcion(),
				tarea.getUsuarios().getNombre_usuario(),
				tarea.getPrioridad(),
				tarea.getEstado());
		
		return new ResponseEntity<TareaDTO>(dto, HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "/borrar/{id}")
	public ResponseEntity<Tarea> borrar(@PathVariable Long id) {

		Optional<Tarea>opti =Optional.of(tareaService.obtenerTareaPorId(id));
		ResponseEntity<Tarea> resp;
		if(opti.isPresent()) {
			tareaService.borrarTareaPorId(id);
			resp= new ResponseEntity<Tarea>(HttpStatus.ACCEPTED);
		}else {
			resp= new ResponseEntity<Tarea>(HttpStatus.BAD_REQUEST);
		}
		
			return resp;
	}
	
	
	@ResponseBody
    @GetMapping("/misTareas")
    public List<TareaDTO> getMisTareas(HttpSession session) {

		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		List<Tarea> tareas=tareaService.BuscarTareasUsuario(idUsuario);
        List<TareaDTO> tareasDto = new ArrayList<TareaDTO>();

        for (Tarea t: tareas) {
			TareaDTO dto = new TareaDTO(
					t.getId_tarea(),
					t.getUsuarios().getId_usuario(),
					t.getProyecto().getId_proyecto(),
					t.getTitulo(),
				t.getDescripcion(),
				t.getUsuarios().getNombre_usuario(),
				t.getPrioridad(),
				t.getEstado());
			tareasDto.add(dto);
		}
        return tareasDto;
    }
	
	
}
