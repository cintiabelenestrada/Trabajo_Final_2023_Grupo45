package ar.edu.unju.fi.service;


import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

public interface IServiciosService {

	
	
	List<IndiceMasaCorporal> getImcs();

	String calcularIMC(Usuario usuario, Integer pesoActual, LocalDate fechaActual);
	void guardarImc(IndiceMasaCorporal indiceMasaCorporal);
	IndiceMasaCorporal getImcById(Long id);
	double calcularPesoIdeal(Usuario usuario);
	void eliminarImc(Long id);
	
}
