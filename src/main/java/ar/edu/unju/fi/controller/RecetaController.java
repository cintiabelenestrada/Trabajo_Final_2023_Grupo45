package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecetaController {
    // @GetMapping("/recetas")
    // List<Receta> recetas = recetaService.obtenerTodasLasRecetas();
    // model.addAttribute("recetas", recetas);
    // return "recetas";
	@GetMapping("/recetas")
	public String getInicioPage(Model model) {
    // Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
    model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
    return "recetas";
	}
}
