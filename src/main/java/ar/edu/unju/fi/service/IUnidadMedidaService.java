package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.UnidadMedida;

public interface IUnidadMedidaService {
    public UnidadMedida obtenerUnidadMedida();
    public List<UnidadMedida> obtenerUnidadMedidas();
        // public UnidadMedida obtenerUnidadMedidaPorId(Long id);
    // public String buscarUnidadMedidaporNombre(UnidadMedida nombre);
    public void guardarUnidadMedida(UnidadMedida unidadmedida);
    // public void modificarUnidadMedida(UnidadMedida unidadmedidaModificado);
    // public void eliminarUnidadMedida(UnidadMedida unidadmedida);
}
