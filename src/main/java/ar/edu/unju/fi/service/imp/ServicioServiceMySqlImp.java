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
    
 

    @Override
    public Usuario getUsuarioPorCodigo(int codigoUsuario) {
        // Implementación para obtener el usuario por código utilizando el repositorio
        return registroRepository.findByCodigoUsuario(codigoUsuario);
    }

//    @Override
//    public IndiceMasaCorporal calcularIMC(Usuario usuario, double peso) {
//        // Cálculo del IMC y creación de la entidad IndiceMasaCorporal
////        double estaturaEnMetros = usuario.getEstatura() / 100.0; // Convertir estatura a metros
//        double estaturaEnMetros = registroRepository.findEstaturaByCodigoUsuario(usuario.getCodigoUsuario());
//
////        double estaturaMetros = registroService
//        double imc = peso / (estaturaEnMetros * estaturaEnMetros);
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

//    @Override
//    public void guardarIMC(IndiceMasaCorporal imc) {
//        // Implementación para guardar el IMC en la base de datos utilizando el repositorio
//        servicioRepository.guardarIMC(imc);
//    }
//	
//	
	
//	@Autowired
//	private IServicioRepository servicioRepository;
//	
//	 @Override
//	    public double calcularIMC(double peso, double estatura) {
//	        double imc = peso / (estatura * estatura);
//	        return imc;
//	    }
//	
//	 @Override
//	    public void agregarIndiceMasaCorporal(IndiceMasaCorporal imcCalculado) {
//	        servicioRepository.save(imcCalculado);
//	    }

}

