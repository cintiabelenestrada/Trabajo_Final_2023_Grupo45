package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.UnidadMedida;
import java.util.List;
import java.util.Optional;


public interface IUnidadMedidaRepository extends CrudRepository<UnidadMedida, Long>{
    /** Metodo para obtener todas las unidades de medida por el estado
     * @param estado 
     * @return
     */
    public List<UnidadMedida> findByEstado(boolean estado);

    // Método personalizado para buscar una Unidad de Medida por su nombre
    public UnidadMedida findByNombre(String nombre);

    // Método para buscar una unidad de medida por su ID
    // El tipo Optional se utiliza para indicar que el resultado de la búsqueda de
    // una unidad de medida por su ID puede o no contener un valor que pueden ser nulos o vacíos
    // como en el caso de la búsqueda de una entidad por su ID en el repositorio. 
    // public Optional<UnidadMedida> findById(Long id);

}
