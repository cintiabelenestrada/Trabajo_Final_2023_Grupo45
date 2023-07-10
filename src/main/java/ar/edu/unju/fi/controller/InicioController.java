package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta es la clase controladora de Inicio/Index.
 * devuelve la pagina de inicio
 * @author joel rojas araya Grupo 45
 * @version 1.0.2 date: 9/7/23
 */

@Controller
public class InicioController {

	@GetMapping("/inicio")
	public String getInicioPage (){
		return "index";
	}

}
