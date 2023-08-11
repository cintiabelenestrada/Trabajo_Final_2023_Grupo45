package ar.edu.unju.fi.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.util.UploadFile;

@Service("recetaServiceMysqlImp")
public class RecetaServiceMysqlImp implements IRecetaService {
	/**
	 * Inveccion e instanciado del objeto recetaRepository al contenedor
	 */
	@Autowired
	private IRecetaRepository recetaRepository;
	
	/**
	 * Inveccion e instanciado del objeto receta al contenedor
	 */
	@Autowired
	private Receta receta;
	
	/**@Autowired
	List<Receta> recetaFiltrada;**/
	/**
	 * Metodo que otiene el listado de receta
	 */
	@Override
	public List<Receta> getListaReceta() {		
		return recetaRepository.findByEstado(true);
	}

	/**
	 * Metodo para guardar una receta
	 */
	@Override
	public void guardar(Receta receta) {
		receta.setEstado(true);
		recetaRepository.save(receta);
		
	}

	/**
	 * Metodo para obtener una receta por su id
	 */
	@Override
	public Receta getBy(Long id) {		
		return recetaRepository.findById(id).get();
	}

	/**
	 * Metodo para guardar una receta modificada
	 */
	@Override
	public void modificar(Receta receta) {
		receta.setEstado(true);
		recetaRepository.save(receta);
		
	}

	/**
	 * Metodo para eliminar una receta
	 */
	@Override
	public void eliminar(Receta recetaEncontrada) {
		recetaEncontrada.setEstado(false);
		recetaRepository.save(recetaEncontrada);
		
	}

	/**
	 * Metodo para obtener un objeto tipo Receta
	 */
	@Override
	public Receta getReceta() {		
		return receta;
	}

	/**
	 * Metodo para encontrar una receta por su estado y categoria
	 */
	@Override
	public List<Receta> getListaRecetaFiltrada(String categoria) {
		/**recetaFiltrada.removeAll(recetaFiltrada);
		List<Receta> recetas = recetaRepository.findByEstado(true);
		for(Receta r:recetas) {
			if (r.getCategoria().equals(categoria)) {
				recetaFiltrada.add(r);
			}
			
		}
		
		return recetaFiltrada;**/  
		return recetaRepository.findByEstadoAndCategoria(true, categoria);
	}

}