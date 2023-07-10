package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;

@Service("ingredienteServiceMysqlImp")
public class IngredienteServiceMysqlImp implements IIngredienteService {

	@Autowired
	private Ingrediente ingrediente;

	@Autowired
	private IIngredienteRepository ingredienteRepository;

	/**
	 * Metodo que retorna ingrediente
	 * @return ingrediente
	 */
	@Override
	public Ingrediente obtenerIngrediente() {
		return ingrediente;
	}

	/**
	 * Metodo que retorna la lista de ingredientes
	 * @return la lista de ingredientes
	 */
	@Override
	public List<Ingrediente> obtenerIngredientes() {
		return ingredienteRepository.findByEstado(true);
	}

	/**
	 * Metodo que almacena el ingrediente en la base de datos
	 * @param ingrediente representa el ingrediente que se guardara en la base de datos
	 */
	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
	}

	/**
	 * Metodo que modifica el ingrediente 
	 * @param ingredienteModificado representa el ingrediente modificado
	 */
	@Override
	public void modificarIngrediente(Ingrediente ingredienteModificado) {
		ingredienteModificado.setEstado(true);
		ingredienteRepository.save(ingredienteModificado);
	}

	/**
	 * Metodo que busca el ingrediente de acuerdo a su id
	 * @param id representa el id del ingrediente que se desea buscar
	 * @return el ingrediente encontrado
	 */
	@Override
	public Ingrediente buscarIngrediente(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	/**
	 * Metodo que elimina el ingrediente de la base de datos
	 * @param ingrediente representa el ingrediente que se desea eliminar
	 */
	@Override
	public void eliminarIngrediente(Ingrediente ingrediente) {
//		Ingrediente unIngrediente = new Ingrediente();
//		unIngrediente = buscarIngrediente(id);
//		unIngrediente.setEstado(false);
//		ingredienteRepository.save(unIngrediente);
		ingrediente.setEstado(false);
		ingredienteRepository.save(ingrediente);
	}

}