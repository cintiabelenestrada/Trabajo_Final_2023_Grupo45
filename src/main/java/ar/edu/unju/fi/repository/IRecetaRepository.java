package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Receta;
import java.util.List;


@Repository
public interface IRecetaRepository extends CrudRepository<Receta, Long> {

	public List <Receta> findByEstado(boolean estado);
	public List <Receta> findByEstadoAndCategoria(boolean estado, String categoria);
  

	
}