package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Ingrediente;

public interface IIngredienteService {

	List<Ingrediente> getListaIngrediente();
	Ingrediente getIngrediente(); 
	void guardarIngrediente(Ingrediente ingrediente); 
	Ingrediente findIngredienteById(Long id); 
	void eliminar(Ingrediente ingredienteEncontrado);
	void modificar (Ingrediente ingrediente); 
	void cargarIngrediente(); 
	List<Ingrediente> getIngredientesByIds(List<Long> ingredientesIds);
	
}