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

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IIngredienteService ingredienteService;
	
	@Autowired
	@Qualifier("recetaServiceMysqlImp")
	private IRecetaService recetaService;
	
	
	/**
	 * Peticion para ingresarar al listado de ingredientes
	 * @param model
	 * @return
	 */
	@GetMapping("/listado")
	public String getListadoIngredientePage(Model model) {		
		model.addAttribute("ingredientesLista",ingredienteService.getListaIngrediente());	
		
		return "ingredientes";
	}
	
	/**
	 * Peticion para guardar un nuevo ingrediten
	 * @param model: se agrega al modelo un objeto ingrediente y una variable edicion
	 * @return pagina nuevo_ingrediente 
	 */
	@GetMapping("/nuevo")
	public String getNuevoIngredientePage(Model model) {
		boolean edicion=false;
		model.addAttribute("ingrediente", ingredienteService.getIngrediente());		
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	@PostMapping("/guardar")
	public ModelAndView getGuardarIngredientePage(@Valid @ModelAttribute("ingrediente")Ingrediente ingrediente, BindingResult result) {		
		ModelAndView modelView = new ModelAndView("ingredientes");
		if(result.hasErrors()) {
			modelView.setViewName("nuevo_ingrediente");
			modelView.addObject("ingrediente", ingrediente);			
			return modelView;
		}
		ingredienteService.guardarIngrediente(ingrediente);
		modelView.addObject("ingredientesLista",ingredienteService.getListaIngrediente());
		return modelView;
	}
	
	
	/**
	 * Peticion para eliminar un ingrediente
	 * @param id
	 * @return
	 */
	@GetMapping("/eliminar/{id}")
	public String getEliminarIngrediente (@PathVariable(value="id") Long id){		
		Ingrediente ingredienteEncontrado = ingredienteService.findIngredienteById(id);		
		ingredienteService.eliminar(ingredienteEncontrado);
		return "redirect:/ingredientes/listado";		
	}
	
	/**
	 * Peticion para obtener el ingrediente a modificar por su id
	 * @param model 
	 * @param id del ingrediente a modificar
	 * @return la pagina nuevo_ingrediente
	 */
	@GetMapping ("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value="id") Long id) {
		Ingrediente ingredienteEncontrado = ingredienteService.findIngredienteById(id);	
		boolean edicion = true;
		model.addAttribute("ingrediente",ingredienteEncontrado);		
		model.addAttribute("edicion",edicion);
		return "nuevo_ingrediente";
	}
	
	/**
	 * Peticion para guardar el ingrediente modificado
	 * @param ingrediente	 
	 * @return se redirige a la pagina de listado de ingredientes
	 */
	@PostMapping("/modificar")
	public String getModificarIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult result, Model model){
		if(result.hasErrors()) {
			model.addAttribute("edicion", true);			
			return "nuevo_ingrediente";
		}		
		ingredienteService.modificar(ingrediente);
		return "redirect:/ingredientes/listado";
	}
}