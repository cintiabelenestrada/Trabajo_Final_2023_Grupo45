package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Receta;
import java.util.List;


@Repository
public interface IRecetaRepository extends CrudRepository<Receta, Long> {

	/**
	 * Metodo para obtener todas las recetas por el estado
	 * @param estado
	 * @return
	 */
  public List<Receta> findByEstado(boolean estado);
  
  /**
   * Metodo para obtener todas las recetas por categoria
   * @param categoria
   * @return
   */
  public List<Receta> findByCategoria(String categoria);
  

	
}