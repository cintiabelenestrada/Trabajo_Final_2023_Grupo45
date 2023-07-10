package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Ingrediente;

public interface IIngredienteService {

	public Ingrediente obtenerIngrediente();
	public List<Ingrediente> obtenerIngredientes();
	public void guardarIngrediente(Ingrediente ingrediente);
	public void modificarIngrediente(Ingrediente ingredienteModificado);
	public Ingrediente buscarIngrediente(Long id);
	public void eliminarIngrediente(Ingrediente ingrediente);
	
}