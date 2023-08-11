package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.IngredienteReceta;
import java.util.List;
// import ar.edu.unju.fi.entity.Receta;

public interface IIngredienteRecetaRepository extends CrudRepository<IngredienteReceta, Long> {
	
	public List<IngredienteReceta> findByEstado(boolean estado);
}