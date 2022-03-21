package des.kanban.security;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import des.kanban.entidades.Usuario;
import des.kanban.servicios.UsuarioServicio;



public class AuthenticationSuccessHandlerImp implements AuthenticationSuccessHandler {

	@Autowired
	private UsuarioServicio usuarioService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		HttpSession session = request.getSession();
		
		Usuario usuario = usuarioService.obtenerUsuarioPorNombre(userDetails.getUsername());
		
		session.setAttribute("nombre", usuario.getNombre_usuario());
		session.setAttribute("idUsuario", usuario.getId_usuario());
		
		//handle(request, response, authentication);
		
		boolean isCliente = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("trabajador")) {
				isCliente = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("manager")) {
				isAdmin = true;
				break;
			}
		}
		
		String targetUrl;
		if (isCliente) {
			targetUrl = "/index";
		} else if (isAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
