package des.kanban.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Usuario;
import des.kanban.servicios.UsuarioServicio;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServicio usuarioService;

	@GetMapping("/alta")
	public ModelAndView getAlta() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/alta");

		return mav;
	}

	@PostMapping(value = "/alta")
	public String postRegistrarse(@RequestParam String usuario, @RequestParam String password) {

		String redirectCorrecto = "redirect:/index";

		Usuario user = new Usuario(password, usuario);
		usuarioService.crearUsuario(user);
		return redirectCorrecto;

	}

}
