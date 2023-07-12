package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.service.IServiciosService;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.repository.IServiciosRepository;

@Service
public class ServiciosServiceMySqlImp implements IServiciosService {
	
	

    @Autowired
    private IServiciosRepository imcRepository;
    
//    @Autowired
//    private IRegistroRepository registroRepository;
    
 

    @Override
    public String calcularIMC(Usuario usuario, Integer pesoActual, LocalDate fechaActual) {
        double estaturaMetros = Double.parseDouble(usuario.getEstatura()); 

        /**
         * Realiza el calculo para el indice de masa corporal
         * @param pesoActual es el peso actual de un usuario
         * @param estaturaMetros es la estatura en metros, y se utilizo parseDouble para convertir el string
         * 
         * */
        double imcActual = pesoActual / (estaturaMetros * estaturaMetros);

        IndiceMasaCorporal imc = new IndiceMasaCorporal();
        imc.setFechaImc(fechaActual);
        imc.setPesoActual(pesoActual);
        imc.setImcActual(imcActual);
        imc.setUsuario(usuario); // Establecer la relación con el usuario

        
        /*
         * Utilizado para guardar el imc en la base de datos
         * */
        imcRepository.save(imc);



        System.out.println("Estatura: " + estaturaMetros); 
        System.out.println("Peso: " + pesoActual); 
        System.out.println("Imc: " + imcActual); 
        
        String mensaje;
        if (imcActual < 18.5) {
            mensaje = String.format("Su IMC es %.1f - Está por debajo de su peso ideal", imcActual);
        } else if (imcActual >= 18.5 && imcActual <= 25) {
            mensaje = String.format("Su IMC es %.1f - Está en su peso normal", imcActual);
        } else {
            mensaje = String.format("Su IMC es %.1f - Tiene sobrepeso", imcActual);
        }
        
        


        return mensaje;
        
    }
    
    @Override
    public double calcularPesoIdeal(Usuario usuario) {
        int edad = Period.between(usuario.getFecha_nacimiento(), LocalDate.now()).getYears(); // Calcular la edad en años
        double estatura = Double.parseDouble(usuario.getEstatura()) *100;
       
        // Aplicar la fórmula de Perrault para calcular el peso ideal
        double pesoIdeal = estatura - 100 + ((double) edad / 10 * 0.9);
//        System.out.println("estatura cm"+estaturaCm);
        System.out.println("estatura cm: "+estatura);
        System.out.println("Tu peso ideal: " + pesoIdeal);
        System.out.println("Tu edad: " + edad);

        return pesoIdeal;
    }

}

