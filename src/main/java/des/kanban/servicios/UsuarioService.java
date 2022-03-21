package des.kanban.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.entidades.Rol;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.RolRepository;
import des.kanban.modelo.UsuarioRepository;



@Transactional
@Service
public class UsuarioService implements UsuarioServicio, UserDetailsService {

	@Autowired
	private UsuarioRepository dao;

	@Autowired
	private RolRepository rolDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario obtenerUsuarioPorId(Long idUsuario) {
		Optional<Usuario> optional = dao.findById(idUsuario);
		return optional.orElse(new Usuario());
	}
	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre_usuario) {

		return dao.findByNombre_usuario(nombre_usuario);
	}
	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return (ArrayList<Usuario>) dao.findAll();
	}
	@Override
	public Usuario crearUsuario(Usuario usuario) {
		usuario.anadirRol(rolDao.findById(Long.parseLong("2")).get());
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			
		return dao.save(usuario);
	}


	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

		Usuario u = dao.findByNombre_usuario(usuario);
		Set<Rol> l = u.getRoles();
		Set<Rol> roles = new HashSet<>();

		l.stream().forEach(roles::add);

		u.setRoles(roles);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (Rol rol : u.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre_rol()));
		}

		return new org.springframework.security.core.userdetails.User(u.getNombre_usuario(), u.getPassword(),
				grantedAuthorities);
	}



	

}
