package ar.edu.unju.fi.service;


import java.time.LocalDate;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

public interface IServiciosService {

	
//	void calcularIMC(Long id, double peso, String fechaActual);
	String calcularIMC(Usuario usuario, Integer pesoActual, LocalDate fechaActual);
//	void agregarIndiceMasaCorporal(Usuario usuario, IndiceMasaCorporal imc);
//	void calcularIMC(String codigoUsuario);

	double calcularPesoIdeal(Usuario usuario);
	
//	IndiceMasaCorporal calcularIMC(Usuario usuario, double peso);
//	Usuario getUsuarioPorCodigo(int codigoUsuario);
//	void guardarIMC(IndiceMasaCorporal imc);
	
//	void calcularIMC(double estatura, double peso);
//	 void agregarIndiceMasaCorporal(IndiceMasaCorporal imcCalculado);

}
