package ar.edu.unju.fi.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ServiciosController {
	
	
	 @GetMapping("/calculoimc")
	    public String getServiciosPage( Model model) {
	        
	        return "calcular_imc";
	    }

}
