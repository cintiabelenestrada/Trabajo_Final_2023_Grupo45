package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;
import jakarta.annotation.PostConstruct;

@Service("ingredienteServiceMysqlImp")
public class IngredienteServiceMysqlImp implements IIngredienteService {

	/**
	 * Inveccion e instanciado del objeto ingredienteRepository al contenedor
	 */
	@Autowired
	private IIngredienteRepository ingredienteRepository;
	
	/**
	 * Inveccion e instaciado del objeto ingrediente al contenedor
	 */
	@Autowired
	private Ingrediente ingrediente;
	
/**	@Autowired
	List<Ingrediente> listaReceta;
	@Autowired
	List<Ingrediente> listaAux;**/
	
	/**
	 * Metodo para obtener la lista de ingredientes
	 */
	@Override
	public List<Ingrediente> getListaIngrediente() {
		
		return ingredienteRepository.findByEstado(true);
	}

	/**
	 * Metodo para obtener un ingrediente
	 */
	@Override
	public Ingrediente getIngrediente() {		
		return ingrediente;
	}

	/**
	 * Metodo para guardar un ingrediente en la base de datos
	 */
	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
		
	}

	/**
	 * Metodo para buscar un ingrediente
	 */
	@Override
	public Ingrediente findIngredienteById(Long id) {
		
		return ingredienteRepository.findById(id).get();
	}

	/**
	 * Metodo para cargar un listado de ingredientes a la base de datos
	 */
	@PostConstruct
	@Override
	public void cargarIngrediente() {
		ingrediente = new Ingrediente(Long.parseLong("1"), "Sal", true);
		ingredienteRepository.save(ingrediente);
		ingrediente = new Ingrediente(Long.parseLong("2"), "Pimienta", true);
		ingredienteRepository.save(ingrediente);
		ingrediente = new Ingrediente(Long.parseLong("3"), "Condimentos", true);
		ingredienteRepository.save(ingrediente);
		
	}
	/**
	 * Metodo para eliminar un ingrediente por id, la eliminacion es lógica
	 */

	@Override
	public void eliminar(Ingrediente ingredienteEncontrado) {
		ingredienteEncontrado.setEstado(false);
		ingredienteRepository.save(ingredienteEncontrado);
		
	}
	/**
	 * Metodo para modificar un ingrediente
	 */

	@Override
	public void modificar(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
		
	}

	@Override
	public List<Ingrediente> getIngredientesByIds(List<Long> ingredientesIds) {
	/**	listaReceta.removeAll(listaReceta);
		listaAux.removeAll(listaAux);
		listaAux=ingredienteRepository.findByEstado(true);
		for(Long id:ingredientesIds) {
			for(Ingrediente ingre:listaAux) {
				if(ingre.getId().equals(id)) {
					listaReceta.add(ingre);
					break;
					
				}
			}
			
		}**/
		return ingredienteRepository.findAllById(ingredientesIds);
	}

}