package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.IngredienteReceta;
import ar.edu.unju.fi.service.IIngredienteRecetaService;

@Controller
@RequestMapping("/ingredientereceta")
public class IngredienteRecetaController {

	@Autowired
	@Qualifier("ingredienterecetaServiceMysqlImp")
	private IIngredienteRecetaService ingredienteRecetaService;
	
	@GetMapping("/gestion")
	public String getIngredienteRecetaPage(Model model) {
		model.addAttribute("ingredienterecetas", ingredienteRecetaService.obtenerIngredienteRecetas());
		return "gestion_ingrediente-receta";
	}

}