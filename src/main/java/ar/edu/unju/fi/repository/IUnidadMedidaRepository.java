package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.UnidadMedida;
import java.util.List;


public interface IUnidadMedidaRepository extends CrudRepository<UnidadMedida, Long>{
    /** Metodo para obtener todas las unidades de medida por el estado
     * @param estado 
     * @return
     */
    public List<UnidadMedida> findByEstado(boolean estado);

    // Método personalizado para buscar una Unidad de Medida por su nombre
    public UnidadMedida findByNombre(String nombre);

    // public UnidadMedida findFirstByNombre(String nombre);
}
