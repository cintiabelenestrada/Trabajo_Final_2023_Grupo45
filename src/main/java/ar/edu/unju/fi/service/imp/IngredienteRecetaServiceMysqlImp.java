package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IngredienteReceta;
import ar.edu.unju.fi.repository.IIngredienteRecetaRepository;
import ar.edu.unju.fi.service.IIngredienteRecetaService;

@Service("ingredienterecetaServiceMysqlImp")
public class IngredienteRecetaServiceMysqlImp implements IIngredienteRecetaService {

	@Autowired
	private IngredienteReceta ingredienteReceta;

	@Autowired
	private IIngredienteRecetaRepository ingredienteRecetaRepository;

	@Override
	public IngredienteReceta obtenerIngredienteReceta() {
		return ingredienteReceta;
	}

	@Override
	public List<IngredienteReceta> obtenerIngredienteRecetas() {
		return ingredienteRecetaRepository.findByEstado(true);
	}

	// @Override
	// public void guardarIngrediente(Ingrediente ingrediente) {
	// 	ingrediente.setEstado(true);
	// 	ingredienteRepository.save(ingrediente);
	// }

	// @Override
	// public void modificarIngrediente(Ingrediente ingredienteModificado) {
	// 	ingredienteModificado.setEstado(true);
	// 	ingredienteRepository.save(ingredienteModificado);
	// }

	// @Override
	// public Ingrediente buscarIngrediente(Long id) {
	// 	return ingredienteRepository.findById(id).get();
	// }

	// @Override
	// public void eliminarIngrediente(Ingrediente ingrediente) {
	// 	ingrediente.setEstado(false);
	// 	ingredienteRepository.save(ingrediente);
	// }

}