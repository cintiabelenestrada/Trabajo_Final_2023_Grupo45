package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta es la clase controladora de Consejo.
 * devuelve la pagina de consejo.
 * @author joel rojas araya Grupo 45
 * @version 1.0.2 date: 9/7/23
 */

@Controller
public class ConsejoController {
//Se llega a una solucion para que el header cambie de forma dinamica los titulos de la pagina seleccionada
	
	@GetMapping("/consejos")
	public String getInicioPage(Model model) {
    // Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
    String tituloPagina = "Consejos"; // Establece el valor por defecto que se vera en el header

    // Se realiza el cambio de valor de `tituloPagina` por Consejo

    model.addAttribute("tituloPagina", tituloPagina);
    return "consejos";
	}
}