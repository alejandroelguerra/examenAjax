package des.kanban.modelo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{

	
	@Query("select u from Usuario u where u.nombre_usuario LIKE :nombre")
	Usuario findByNombre_usuario(String nombre);
	
}
