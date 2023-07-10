package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unju.fi.entity.Usuario;

public interface IRegistroRepository extends CrudRepository<Usuario,Long> {
	/**
     * Busca y devuelve una lista de productos por estado.
     *
     * @param estado el estado del producto
     * @return una lista de productos que coinciden con el estado especificado
     */
	public List<Usuario> findByEstado (boolean estado);
	public Optional<Usuario> findById (Long id);
	
	
	
	/*Metodos agregados para Servicios, y el calculo de IMC
	 * @author Jonathan R. Mascareño date: 9/7/23
	 * */
	
	 @Query("SELECT u.estatura FROM Usuario u WHERE u.id = :id")
	    String findEstaturaById(@Param("id") Long id);
}
