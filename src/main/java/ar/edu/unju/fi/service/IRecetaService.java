package ar.edu.unju.fi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Receta;

public interface IRecetaService {

	public Receta obtenerReceta();
	public void guardarReceta(Receta receta,MultipartFile imagen) throws IOException;
	public List<Receta> obtenerRecetas();
	public void eliminarReceta(Long id);
	public Receta buscarReceta(Long id);
	public void modificarReceta(Receta recetaModificada, MultipartFile imagenModificada) throws IOException;
	public List<Receta> filtrarRecetaCategoria(String categoria);
}