package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Usuario;

public interface IRegistroRepository extends CrudRepository<Usuario,Long> {
	/**
     * Busca y devuelve una lista de productos por estado.
     *
     * @param estado el estado del producto
     * @return una lista de productos que coinciden con el estado especificado
     */
	public List<Usuario> findByEstado (boolean estado);
	public Optional<Usuario> findById (Long id);
}
