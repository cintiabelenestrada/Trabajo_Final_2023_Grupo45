package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.entity.Usuario;
 
/**
 * Interfaz que define los métodos para el servicio de registro.
 * @author Federico Nicolas Burgos
 * @version 1.0.1 date=30/06/2023
 */
public interface IRegistroService {
	
	/**
     * Elimina un producto de la lista de productos utilizando el código.
     *
     * @param id     el ID del producto a eliminar
     * @param estado el estado del producto a eliminar
	 * @return 
     */

	public Usuario save(Usuario usuario);

	//List<Usuario> listar();

	public Optional<Usuario> findById(Long id);
	Optional<Usuario> listarId(Long id);
	public void eliminar(Long id);
	
}
