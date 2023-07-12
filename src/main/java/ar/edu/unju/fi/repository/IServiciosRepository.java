package ar.edu.unju.fi.repository;


import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiciosRepository extends CrudRepository <IndiceMasaCorporal, Long>{
	
//	void guardarIMC(IndiceMasaCorporal imc);
	
	List<IndiceMasaCorporal> findByEstado(boolean estado);
//	List<IndiceMasaCorporal> findByUsuarioAndEstadoOrderByFechaImcDesc(Usuario usuario, boolean estado);
	  Optional<IndiceMasaCorporal> findById(Long id);
	List<IndiceMasaCorporal> findAllByOrderByFechaImcDesc();
	List<IndiceMasaCorporal> findByUsuarioOrderByFechaImcDesc(Usuario usuario);


}
