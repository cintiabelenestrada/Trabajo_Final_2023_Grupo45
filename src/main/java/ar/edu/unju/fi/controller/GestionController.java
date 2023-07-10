package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.service.IRegistroService;
import ar.edu.unju.fi.service.IServiciosService;


@Controller
public class GestionController {
	@Autowired
	private IServiciosService imcPesoService;
	
	
	@GetMapping("/gestion")
	public String getGestionPage (Model model) {
		
		
		return "gestion";
	}

}
