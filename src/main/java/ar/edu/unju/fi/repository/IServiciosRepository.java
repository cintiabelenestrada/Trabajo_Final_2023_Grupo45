package ar.edu.unju.fi.repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiciosRepository extends CrudRepository <IndiceMasaCorporal, Long>{
	
//	void guardarIMC(IndiceMasaCorporal imc);

}
