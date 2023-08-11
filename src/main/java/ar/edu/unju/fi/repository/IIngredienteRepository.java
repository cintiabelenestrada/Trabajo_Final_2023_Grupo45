package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Ingrediente;
import java.util.List;
// import ar.edu.unju.fi.entity.Receta;

@Repository
public interface IIngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	public List<Ingrediente> findByEstado(boolean estado);
	public List<Ingrediente> findAllById(Iterable<Long> ingredientesIds);

}