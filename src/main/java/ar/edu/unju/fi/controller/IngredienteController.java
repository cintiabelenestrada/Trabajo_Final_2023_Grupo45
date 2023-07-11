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
	 * Metodo que retorna la pagina de gestion de datos de ingredientes
	 * @return la pagina gestion_datos_ingrediente.html
	 */
	@GetMapping("/gestion")
	public ModelAndView obtenerPaginaGestionIngrediente() {
		ModelAndView modelAndView = new ModelAndView("gestion_datos_ingrediente");
		modelAndView.addObject("ingredientes", ingredienteService.obtenerIngredientes());
		return modelAndView;
	}
	
	/**
	 * Metodo que retorna la pagina del formulario de para guardar ingrediente
	 * @param model representa la clase model para enviar las varibles ingrediente,recetas,edicion
	 * @return la pagina nuevo_ingrediente.html
	 */
	@GetMapping("/nuevo")
	public String obtenerPaginaNuevoIngrediente(Model model) {
		boolean edicion=false;
		model.addAttribute("ingrediente", ingredienteService.obtenerIngrediente());
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	/**
	 * Metodo que guarda el ingrediente en la base de datos
	 * @param ingrediente representa el ingrediente que se envio para guardar los datos
	 * @param bindingResult representa la clase que recibe los errores del formulario
	 * @return la pagina gestion en caso de no tener errores al cargar el formulario 
	 *  el formularion en caso de capturar errores
	 */
	@PostMapping("/guardar")
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult ) {
		ModelAndView mav = new ModelAndView("redirect:/ingrediente/gestion");
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("nuevo_ingrediente");
			mav.addObject("recetas", recetaService.obtenerRecetas());
			mav.addObject("edicion", false);
			return mav;
		}
		
		ingredienteService.guardarIngrediente(ingrediente);
		return mav;
	}
	
	/**
	 * Metodo que retorna el formulario de ingrediente para modificar los datos
	 * @param model representa la clase model para enviar recetas, ingrediente, edicion
	 * @param id representa el id de ingrediente que se va a modificar
	 * @return la pagina nuevo_ingrediente.html
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id")Long id) {
		boolean edicion=true;
		Ingrediente ingredienteEncontrado = ingredienteService.buscarIngrediente(id);
		model.addAttribute("recetas", recetaService.obtenerRecetas());

		model.addAttribute("ingrediente", ingredienteEncontrado);
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	/**
	 * Metodo que modifica los datos de ingrediente
	 * @param ingredienteModificado representa el ingrediente modificado
	 * @param resultado representa la clase que recibe los errores del formulario
	 * @param model representa la clase que envia ingrediente, recetas en caso que no se cumpla las validaciones
	 * @return el formularion en caso que no se cumplan las validaciones, en caso contrario vuelve a la pagina gestion de ingredientes
	 */
	@PostMapping("/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingredienteModificado, BindingResult resultado,Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("ingrediente",  ingredienteModificado);
			model.addAttribute("recetas", recetaService.obtenerRecetas());

			return "nuevo_ingrediente";
		}
		ingredienteService.modificarIngrediente(ingredienteModificado);
		return "redirect:/ingrediente/gestion";
		
	}
	
	/**
	 * Metodo que elimina el ingrediente de la base de datos
	 * @param id representa el id del ingrediente
	 * @return la pagina gestion de datos de ingrediente
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		Ingrediente ingredienteEncontrado= ingredienteService.buscarIngrediente(id);
		ingredienteService.eliminarIngrediente(ingredienteEncontrado);
		return "redirect:/ingrediente/gestion";
	}
	
	/**
	 * Metodo que devuelve la pagina de gestion de los ingredientes
	 * @param model representa la clase que envia los ingredientes
	 * @return la pagina ingredientes.html
	 */
	@GetMapping("/listado")
	public String getIngredientePage(Model model) {
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
		return "ingredientes";
	}

}