package ar.edu.unju.fi.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ServiciosController {
	
	 @GetMapping("/inicio")
	    public String getInicioPage( Model model) {
	        
	        return "index";
	    }
	
	
	
	 @GetMapping("/calculoimc")
	    public String getServiciosPage( Model model) {
	        
	        return "calcular_imc";
	    }
	 
//	 @GetMapping("/calculoimc2")
//	    public String getServicios2Page( Model model) {
//	        
//	        return "calcular_imc2";
//	    }

}
