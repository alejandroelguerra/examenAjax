package des.kanban.modelo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Rol;

@Repository
public interface RolRepository extends PagingAndSortingRepository<Rol, Long> {

}
