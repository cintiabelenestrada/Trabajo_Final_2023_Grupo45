package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.IngredienteReceta;

public interface IIngredienteRecetaService {

	public IngredienteReceta obtenerIngredienteReceta();
	public List<IngredienteReceta> obtenerIngredienteRecetas();
	// public void guardarIngrediente(Ingrediente ingrediente);
	// public void modificarIngrediente(Ingrediente ingredienteModificado);
	// public Ingrediente buscarIngrediente(Long id);
	// public void eliminarIngrediente(Ingrediente ingrediente);
	
}