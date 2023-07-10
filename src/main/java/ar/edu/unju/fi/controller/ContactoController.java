package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta es la clase controladora de Contacto.
 * devuelve la pagina de contacto.
 * @author joel rojas araya Grupo 45
 * @version 1.0.2 date: 9/7/23
 */

@Controller
public class ContactoController {

//	@GetMapping("/contacto")
//	public String getContactoPage (){
//		return "contacto";
//	}
//Se llega a una solucion para que el header cambie de forma dinamica los titulos de la pagina seleccionada
	
	@GetMapping("/contacto")
	public String getInicioPage(Model model) {
    // Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
    String tituloPagina = "Contacto"; // Establece el valor por defecto que se vera en el header

    // Se realiza el cambio de valor de `tituloPagina` por Contacto

    model.addAttribute("tituloPagina", tituloPagina);
    return "contacto";
}
}
	

