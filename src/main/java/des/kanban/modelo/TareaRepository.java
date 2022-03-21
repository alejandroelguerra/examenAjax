package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Tarea;

@Repository
public interface TareaRepository extends PagingAndSortingRepository<Tarea, Long>{

	@Query ("from Tarea where id_usuario LIKE :id")
    List<Tarea> findAllTareasUsuarioById(Long id);

}
