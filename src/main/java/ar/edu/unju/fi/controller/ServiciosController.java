package ar.edu.unju.fi.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ServiciosController {
	

	
	 @GetMapping("/calculoimc")
	    public String getCalculadoraImcPage( Model model) {
	        
	        return "calcular_imc";
	    }
	 
	 
		
	 @GetMapping("/pesoideal")
	    public String getPesoIdealPage( Model model) {
	        
	        return "peso_ideal";
	    }
	 

}
