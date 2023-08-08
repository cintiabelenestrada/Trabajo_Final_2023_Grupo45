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
	 * Inyeccion del objeto Receta
	 */
	@Autowired
	private Receta receta;
	
	/**
	 * Inyeccion del objeto UploadFile
	 */
	@Autowired
	private UploadFile uploadFile;
	
	/**
	 * Inyeccion de la interfaz receta repository
	 */
	@Autowired
	private IRecetaRepository recetaRepository;
	
	/**
	 * Metodo que retorna objeto receta.
	 * @return un objeto de tipo usuario.
	 */
	@Override
	public Receta obtenerReceta() {
		return receta;
	}

	/**
	 * Metodo que guarda receta
	 * @param receta representa  objeto de tipo receta
	 * @param imagen representa la imagen de la receta
	 */
	@Override
	public void guardarReceta(Receta receta, MultipartFile imagen) throws IOException {
		String nombreImagen = uploadFile.copy(imagen);
		receta.setImagen(nombreImagen);
		receta.setEstado(true);
		recetaRepository.save(receta);
	}

	/**
	 * Metodo para obtener la lista de recetas
	 * cuyo estado sea true (activo).
	 * @return la lista de recetas cuyo estado sea true.
	 */
	@Override
	public List<Receta> obtenerRecetas() {
		return recetaRepository.findByEstado(true);
	}

	/**
	 * Eliminacion logica de receta
	 * cambiando el atributo  estado a falso.
	 * @param id representa el id de una receta.
	 */
	@Override
	public void eliminarReceta(Long id) {
		Receta unaReceta = new Receta();
		unaReceta = buscarReceta(id);
		unaReceta.setEstado(false);
		recetaRepository.save(unaReceta);
	}

	/**
	 * Metodo que retorna  receta por id
	 * @param id representa el id de una receta a buscar.
	 * @return la receta encontrada.
	 */
	@Override
	public Receta buscarReceta(Long id) {
		return recetaRepository.findById(id).get();
	}

	/**
	 * Metodo para modificar usuario
	 * @param recetaModificada representa una receta a modificar.
	 * @param imagen representa la imagen de la receta que se va modificar
	 */
	@Override
	public void modificarReceta(Receta recetaModificada, MultipartFile imagenModificada) throws IOException {
		recetaModificada.setEstado(true);

		if (!imagenModificada.isEmpty()) {
			String imagenString = imagenModificada.getOriginalFilename();

			if (imagenString.compareTo(buscarReceta(recetaModificada.getId()).getImagen()) != 0) {
				uploadFile.delete(buscarReceta(recetaModificada.getId()).getImagen());
				imagenString = uploadFile.copy(imagenModificada);
				recetaModificada.setImagen(imagenString);
			}
		}else {
			recetaModificada.setImagen(buscarReceta(recetaModificada.getId()).getImagen());
		}
		
		recetaRepository.save(recetaModificada);

	}

	/**
	 * Metodo que filtra recetas por su categoria
	 * @param categoria representa la categoria de una receta.
	 * @return la categoria de una receta.
	 */
	@Override
	public List<Receta> filtrarRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}
}