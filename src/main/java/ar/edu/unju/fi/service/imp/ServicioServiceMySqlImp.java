package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.service.IServicioService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.repository.IServicioRepository;

@Service
public class ServicioServiceMySqlImp implements IServicioService {
	
	

    @Autowired
    private IServicioRepository servicioRepository;
    
    @Autowired
    private IRegistroRepository registroRepository;
    
 

//    @Override
//    public Usuario getUsuarioPorCodigo(int codigoUsuario) {
//        // Implementación para obtener el usuario por código utilizando el repositorio
//        return registroRepository.findByCodigoUsuario(codigoUsuario);
//    }

//    @Override
//    public void calcularIMC(double estatura, double peso) {
//
//        double imc = peso / (estatura * estatura);
//
//        IndiceMasaCorporal imcEntity = new IndiceMasaCorporal();
//        imcEntity.setFechaImc(LocalDate.now());
//        imcEntity.setImc(imc);
//
//        // Guardar el IMC en el repositorio
//        servicioRepository.guardarIMC(imcEntity);
//
//        return imcEntity;
//    }
//


}

