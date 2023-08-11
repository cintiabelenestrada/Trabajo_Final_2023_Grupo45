package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.IngredienteReceta;
import ar.edu.unju.fi.service.IIngredienteRecetaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredientereceta")
public class IngredienteRecetaController {

	@Autowired
	@Qualifier("ingredienterecetaServiceMysqlImp")
	private IIngredienteRecetaService ingredienteRecetaService;
	

	@GetMapping("/gestion")
	public ModelAndView obtenerPaginaGestionIngredienteReceta(Model model) {
	    String tituloPagina = "Ingrediente/Receta"; // Establece el valor por defecto que se vera en el header
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		ModelAndView modelAndView = new ModelAndView("gestion_ingrediente-receta");
		modelAndView.addObject("ingredienterecetas", ingredienteRecetaService.obtenerIngredienteRecetas());
		return modelAndView;
	}
	@GetMapping("/listado")
	public String getIngredienteRecetaPage(Model model) {
		// List<IngredienteReceta> ingredientesReceta = ingredienteRecetaService.obtenerTodosLosIngredientesReceta();
		model.addAttribute("ingredienterecetas", ingredienteRecetaService.obtenerIngredienteRecetas());
		return "gestion_ingrediente-receta";
	}

}