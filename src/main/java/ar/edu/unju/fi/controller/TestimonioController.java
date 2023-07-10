package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestimonioController {
	@GetMapping("/testimonios")
	public String getTestimonioPage(Model model) {
    // Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
    String tituloPagina = "Testimonios"; // Establece el valor por defecto que se vera en el header
    model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
    return "testimonios";
	}
}
