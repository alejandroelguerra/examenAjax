package des.kanban.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class IndexController {

	@Autowired
	private UsuarioServicio usuarioService;
	@Autowired
	private ProyectoServicio proyectoService;

	@GetMapping("/index")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView();
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioService.obtenerTodosLosUsuarios();
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) proyectoService.buscarTodos();
		mav.addObject("usuarios", usuarios);
		mav.addObject("proyectos", proyectos);
		mav.setViewName("index");

		return mav;
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");

		return mav;
	}

}
