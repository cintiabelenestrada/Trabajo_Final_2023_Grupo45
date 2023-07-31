package ar.edu.unju.fi.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.Testimonio;

@Repository
public interface ITestimonioRepository extends CrudRepository<Testimonio, Long>{
 
	/*recupera lista de testimonio*/
	public List<Testimonio> findByEstado(boolean estado);

	public List<Testimonio> findByFecha(LocalDate fecha);
	
}