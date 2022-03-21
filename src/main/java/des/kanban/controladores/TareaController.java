package des.kanban.controladores;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
@RequestMapping("tarea")
public class TareaController {

	@Autowired
	private TareaServicio tareaServicio;
	@Autowired
	private ProyectoServicio proyectoServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/{id}")
	public ModelAndView getIndex (@PathVariable Long id,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		ArrayList<Usuario> usuarios= (ArrayList<Usuario>) usuarioServicio.obtenerTodosLosUsuarios();
		Tarea t=tareaServicio.obtenerTareaPorId(id);
		session.setAttribute("idTarea", id);
		mav.addObject("tarea",t);
		mav.addObject("usuarios", usuarios);
		mav.setViewName("tarea/perfilTarea");
		
		return mav;
	}
	@GetMapping("/modificar/{id}")
	public ModelAndView getEditar (@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		Tarea t=tareaServicio.obtenerTareaPorId(id);
		mav.addObject("tarea",t);
		mav.setViewName("tarea/perfilTarea");
		
		return mav;
	}
	
	@PostMapping(value="/editar")
	public String postEditar ( @RequestParam String inputNombre,@RequestParam String inputPrioridad,@RequestParam String inputEstado,@RequestParam Long inputTrabajadores,HttpSession session) {
		
		Long idProyecto=(Long) session.getAttribute("idProyecto");
		Long idTarea=(Long) session.getAttribute("idTarea");
		Tarea t=tareaServicio.obtenerTareaPorId(idTarea);
		Usuario u=usuarioServicio.obtenerUsuarioPorId(inputTrabajadores);
		t.setTitulo(inputNombre);
		t.setPrioridad(inputPrioridad);
		t.setEstado(inputEstado);
		t.setUsuarios(u);
		tareaServicio.modificarTarea(t);
		
		return "redirect:/proyecto/"+idProyecto;
	}
	
	/* CREAR SIN AJAX
	@PostMapping(value = "/crear")
	public ResponseEntity<Tarea> postcrear(@RequestParam String inputNombre,@RequestParam String inputPrioridad
			,@RequestParam Long inputTrabajadores,HttpSession session) {
		Long idProyecto=(Long) session.getAttribute("idProyecto");
		Proyecto p= proyectoServicio.obtenerProyectoPorId(idProyecto);
		Usuario u=usuarioServicio.obtenerUsuarioPorId(inputTrabajadores);
		Tarea t= new Tarea(p,u,inputNombre,inputPrioridad,"Preparada");
		tareaServicio.crearTarea(t);
		
		return "redirect:/proyecto/"+idProyecto";
		
		*/
	//Crear con AJAX
	@ResponseBody
	@PostMapping(value = "/crear")
	public ResponseEntity<Tarea> guardar(@RequestBody Tarea tarea,HttpSession session) {
		
		ResponseEntity<Tarea> resp;
		Long idProyecto=(Long) session.getAttribute("idProyecto");
		Proyecto p= proyectoServicio.obtenerProyectoPorId(idProyecto);
		Tarea t= new Tarea();
		t.setProyecto(p);
		t.setEstado("Preparada");
		t.setPrioridad(tarea.getPrioridad());
		t.setTitulo(tarea.getTitulo());
		t.setUsuarios(usuarioServicio.obtenerUsuarioPorId(tarea.getUsuarios().getId_usuario()));
		tareaServicio.crearTarea(t);
		
		resp = new ResponseEntity<Tarea>(t,HttpStatus.OK);
		return resp;

	}


			
}

