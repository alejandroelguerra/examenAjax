package des.kanban.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
@RequestMapping("proyecto")
public class ProyectoController {

	@Autowired
	private ProyectoServicio proyectoServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/{id}")
	public ModelAndView getPerfil(@PathVariable Long id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Proyecto p = proyectoServicio.obtenerProyectoPorId(id);
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioServicio.obtenerTodosLosUsuarios();
		session.setAttribute("idProyecto", p.getId_proyecto());
		mav.addObject("proyecto", p);
		mav.addObject("usuarios", usuarios);
		mav.setViewName("proyecto/perfilProyecto");

		return mav;
	}

	@GetMapping("/borrar/{id}")
	public ModelAndView getBorrar(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		proyectoServicio.borrarProyectoPorId(id);
		mav.setViewName("redirect:/index");

		return mav;
	}

	@PostMapping(value = "/crear")
	public String postCrear(@RequestParam String inputNombre, @RequestParam String inputDescripcion,
			@RequestParam Long id_usuario) {

		Proyecto p = new Proyecto(inputNombre, inputDescripcion);

		proyectoServicio.crearProyecto(p, id_usuario);

		String redirectCorrecto = "redirect:/index";

		return redirectCorrecto;

	}

	@GetMapping(value = "/buscar")
	public ModelAndView buscarPorPatronDeNombre(@RequestParam String patron) {

		ModelAndView mav = new ModelAndView();
		ArrayList<Proyecto> proyecto = proyectoServicio.buscarPorPatron(patron);
		if (proyecto.isEmpty() || patron.equals("")) {
			System.err.println("adada");
			mav.setViewName("redirect:/index");
			return mav;
		} else {
			mav.addObject("proyectos", proyecto);
			mav.setViewName("index");
			return mav;
		}

	}

}
