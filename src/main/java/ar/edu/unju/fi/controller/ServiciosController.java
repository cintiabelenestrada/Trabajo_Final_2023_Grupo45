package ar.edu.unju.fi.controller;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.service.IServicioService;



@Controller
public class ServiciosController {
	

	@Autowired
	private IRegistroRepository registroRepository;
	
	
	@Autowired IServicioService servicioService;
	
	
	@GetMapping("/datosusuarioimc")
    public String getCalculoImcPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "calcular_imc";
    }

	 
	 
		
	 @GetMapping("/pesoideal")
	    public String getPesoIdealPage( Model model) {
		  model.addAttribute("usuario", new Usuario());
	            return "peso_ideal";
	        }
	  
	 @PostMapping("/pesoideal")
	    public String getPesoIdealPage(@RequestParam("codigoUsuario") int codigoUsuario, Model model) {
	        Usuario usuario = registroRepository.findByCodigoUsuario(codigoUsuario);
	        if (usuario != null) {
	            model.addAttribute("usuario", usuario);
	            // Agrega aquí la lógica para obtener los cálculos de IMC asociados al usuario
	            // y agregarlos al modelo como "calculosIMC"

	            return "peso_ideal";
	        } else {
	            // Código de usuario no válido, manejar el caso según tus necesidades
	            return "error";
	        }
	    }
	 
	 
	 
	 @PostMapping("/datosusuarioimc")
	    public String getCalcuImcPage(@RequestParam("codigoUsuario") int codigoUsuario, Model model) {
	        Usuario usuario = registroRepository.findByCodigoUsuario(codigoUsuario);
	        if (usuario != null) {
	            model.addAttribute("usuario", usuario);
	            // Agrega aquí la lógica para obtener los cálculos de IMC asociados al usuario
	            // y agregarlos al modelo como "calculosIMC"

	            return "calcular_imc";
	        } else {
	            // Código de usuario no válido, manejar el caso según tus necesidades
	            return "error";
	        }
	    }
	 
//	 @PostMapping("/calcularimc")
//	    public String calcularIMC(@RequestParam("codigoUsuario") int codigoUsuario, @RequestParam("pesoActual") double pesoActual, Model model) {
//	        Usuario usuario = servicioService.getUsuarioPorCodigo(codigoUsuario);
//	        if (usuario != null) {
//	            String estatura = registroRepository.findEstaturaByCodigoUsuario(codigoUsuario);
//	            double estaturaNumerica = Double.parseDouble(estatura);
//	             servicioService.calcularIMC(estaturaNumerica, pesoActual);
////	            model.addAttribute("usuario", usuario);
////	            model.addAttribute("imcCalculado", imcCalculado);
//	            return "calcular_imc";
//	        } else {
//	            // Código de usuario no válido, manejar el caso según tus necesidades
//	            return "error";
//	        }
//	    }

}
