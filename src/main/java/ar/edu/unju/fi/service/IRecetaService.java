package ar.edu.unju.fi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Receta;

public interface IRecetaService {

	List<Receta> getListaReceta();	
	void guardar(Receta receta);	
	Receta getBy(Long id);	
	void modificar(Receta receta);	
	void eliminar(Receta recetaEncontrada);	
	Receta getReceta();	
	List<Receta> getListaRecetaFiltrada(String categoria);
}