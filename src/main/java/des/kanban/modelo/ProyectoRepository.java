package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Proyecto;

@Repository
public interface ProyectoRepository  extends PagingAndSortingRepository<Proyecto, Long>{

	//@Query("SELECT i FROM Proyecto i where i.nombre LIKE %:patron% or i.descripcion LIKE %:patron%")
	List<Proyecto> findByNombreContainsOrDescripcionContains(String patron,String patron2);
}
