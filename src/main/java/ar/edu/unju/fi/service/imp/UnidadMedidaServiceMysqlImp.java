package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.UnidadMedida;
import ar.edu.unju.fi.repository.IUnidadMedidaRepository;
import ar.edu.unju.fi.service.IUnidadMedidaService;

@Service("unidadmedidaServiceMysqlImp")
public class UnidadMedidaServiceMysqlImp implements IUnidadMedidaService{

    @Autowired
	private UnidadMedida unidadmedida;

    @Autowired
    private IUnidadMedidaRepository unidadmedidaRepository;

    @Override
    public UnidadMedida obtenerUnidadMedida(){
        return unidadmedida;
    }

    @Override
    public List<UnidadMedida> obtenerUnidadMedidas(){
        // Aquí obtienes todas las unidades de medida
        return unidadmedidaRepository.findByEstado(true);
    }

    // @Override
    // public String buscarUnidadMedidaporNombre(String nombre) {
    //     // Aquí buscas la unidad de medida por su nombre
    //     return unidadmedidaRepository.findByNombre(nombre);
    // }
    
    // @Override
    // public UnidadMedida buscarUnidadMedidaPorNombre(String nombre) {
    //     return unidadmedidaRepository.findFirstByNombre(nombre);
    // }
    // @Override
	// public UnidadMedida buscarUnidadMedida(Long id) {
	// 	return unidadmedidaRepository.findById(id).get();
	// }

    @Override
    public void guardarUnidadMedida(UnidadMedida unidadmedida) {
        // Guardar la unidad de medida en la base de datos
        unidadmedida.setEstado(true);
        unidadmedidaRepository.save(unidadmedida);
    }

    @Override
    public void modificarUnidadMedida(UnidadMedida unidadmedidaModificada) {
        // Modificar la unidad de medida en la base de datos
        unidadmedidaRepository.save(unidadmedidaModificada);
    }

    // @Override
    // public void eliminarUnidadMedida(UnidadMedida unidadmedida) {
    //     // Cambiar el estado de la unidad de medida a false en lugar de eliminarla físicamente
    //     unidadmedida.setEstado(false);
    //     unidadmedidaRepository.save(unidadmedida);
    // }
}
