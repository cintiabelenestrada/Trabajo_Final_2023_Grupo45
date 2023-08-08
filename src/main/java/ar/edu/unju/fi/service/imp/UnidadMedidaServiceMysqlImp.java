package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.UnidadMedida;
import ar.edu.unju.fi.repository.IUnidadMedidaRepository;
import ar.edu.unju.fi.service.IUnidadMedidaService;

@Service("unidadmedidaServiceMysqlImp")
public class UnidadMedidaServiceMysqlImp implements IUnidadMedidaService {

    @Autowired
    private UnidadMedida unidadmedida;

    @Autowired
    private IUnidadMedidaRepository unidadmedidaRepository;

    @Override
    public UnidadMedida obtenerUnidadMedida() {
        return unidadmedida;
    }

    @Override
    public List<UnidadMedida> obtenerUnidadMedidas() {
        // Aquí obtienes todas las unidades de medida
        return unidadmedidaRepository.findByEstado(true);
    }

    @Override
    public UnidadMedida buscarUnidadMedidaId(Long id) {
        Optional<UnidadMedida> unidadMedidaOptional = unidadmedidaRepository.findById(id);
        
        if (unidadMedidaOptional.isPresent()) {
            return unidadMedidaOptional.get();
        } else {
            throw new RuntimeException("Unidad de medida no encontrada");
        }
    }

    @Override
    public void guardarUnidadMedida(UnidadMedida unidadmedida) {
        // Guardar la unidad de medida en la base de datos
        unidadmedida.setEstado(true);
        unidadmedidaRepository.save(unidadmedida);
    }

    @Override
    public void modificarUnidadMedida(UnidadMedida unidadmedidaModificada) {
        // Modificar la unidad de medida en la base de datos
        unidadmedidaModificada.setEstado(true);
        unidadmedidaRepository.save(unidadmedidaModificada);
    }

    @Override
    public void eliminarUnidadMedida(UnidadMedida unidadmedida) {
    // Cambiar el estado de la unidad de medida a false en lugar de eliminarla
    // físicamente
    unidadmedida.setEstado(false);
    unidadmedidaRepository.save(unidadmedida);
    }

    @Override
    public String buscarUnidadMedidaporNombre(UnidadMedida nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnidadMedida obtenerUnidadMedidaId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
