package src.main.java.ar.edu.unju.fi.tp_final_grupo_45.service;

import java.util.List;

import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.entity.Usuario;
 
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
     */

	public void save(Usuario usuario);

	List<Usuario> listar();
	
	
	
}
