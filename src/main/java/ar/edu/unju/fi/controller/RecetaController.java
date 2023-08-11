package ar.edu.unju.fi.controller;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.util.UploadFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/receta")
public class RecetaController {

	@Autowired
	@Qualifier("recetaServiceMysqlImp")
	private IRecetaService recetaService;
	
	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IIngredienteService ingredienteService;
	
	/**
	 * Inyecta e instancia el objeto ingrediente
	 */
	@Autowired
	private Ingrediente unIngrediente;
	
	/**
	 * Peticion para dirigirse a la pagina recetas
	 * @return pagina recetas
	 */
	@GetMapping("/listado")
	public String getListaRecetasPage() {
		
		return "recetas";
	}	

	/**
	 * Peticion que redirige a la pagina receta categoria
	 * @param tipo es la categoria de la receta que se mostrar
	 * @param model usado para inyectar objetos
	 * @return recetascategoria
	 */
	@GetMapping("/listado/{tipo}")
	public String getListaRecetaPorCategoriaPage(@PathVariable("tipo") String tipo, Model model) {
		model.addAttribute("recetas",recetaService.getListaRecetaFiltrada(tipo));
		model.addAttribute("tipo",tipo);
		return "recetascategoria";
	}
	
	/**
	 * Peticion para ingresar al listado de recetas
	 * @param model para agregar atributos a la pagina
	 * @return pagina recetascategoria
	 */
	@GetMapping("/ediciones")
	public String getListaRecetasPage(Model model) {
		boolean edicion=true;
		model.addAttribute("recetas", recetaService.getListaReceta());
		model.addAttribute("edicion", edicion);
		return "recetascategoria";
	}	
	
	/**
	 * Peticion para guardar una nueva receta
	 * @param model para agregar atributos a la pagina
	 * @return pagina nueva receta
	 */
	@GetMapping("/nuevo")
	public String getNuevaRecetalPage(Model model) {
		boolean edicion=false;
		model.addAttribute("receta", recetaService.getReceta());
		model.addAttribute("ingredientes", ingredienteService.getListaIngrediente());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	/**
	 * Peticion para guardar una receta
	 * @param receta es el objeto a guardar
	 * @param result para control de errores
	 * @return pagina de recetas general
	 */	
	@PostMapping("/guardar")
		public ModelAndView guardarReceta(@Valid @ModelAttribute("receta") Receta receta, BindingResult result,
	        @RequestParam("ingredientes") List<Long> ingredientesIds) {
			ModelAndView modelView = new ModelAndView("recetas");
		    if (result.hasErrors()) {
		        modelView.setViewName("nueva_receta");
		        modelView.addObject("ingredientes", ingredienteService.getListaIngrediente());
		        modelView.addObject("receta", receta);
		        return modelView;
		    }		    
		    List<Ingrediente> ingredientesSeleccionados = ingredienteService.getIngredientesByIds(ingredientesIds);		    
		    receta.setIngredientes(ingredientesSeleccionados);	
		    recetaService.guardar(receta);

	    return modelView;
	}

	
	/**
	 * Peticion para modificar una receta
	 * @param model para agregar atributos a la pagina
	 * @param id receta
	 * @return pagina nueva_receta con la receta a modificar
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarRecetaPage(Model model, @PathVariable(value="id")Long id) {
		Receta recetaEncontrada = recetaService.getBy(id);
		boolean edicion=true;		
		model.addAttribute("receta", recetaEncontrada);	
		model.addAttribute("ingredientes", ingredienteService.getListaIngrediente());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	
	/**
	 * Peticion para guardar una receta modificada
	 * @param receta a guardar
	 * @param result para comprobar errores
	 * @param model para agregar atributos a la pagina
	 * @return listado general
	 */
	@PostMapping("/modificar")
	public String modificarReceta(@Valid @ModelAttribute("receta")Receta receta,BindingResult result,Model model,
			@RequestParam("ingredientes") List<Long> ingredientesIds) {
		if(result.hasErrors()) {
			model.addAttribute("edicion",true);	
			model.addAttribute("ingredientes", ingredienteService.getListaIngrediente());
			return "nueva_receta";
		}
		List<Ingrediente> ingredientesSeleccionados = ingredienteService.getIngredientesByIds(ingredientesIds);		    
	    receta.setIngredientes(ingredientesSeleccionados);
		recetaService.modificar(receta);
		model.addAttribute("edicion",true);
		return "redirect:/recetas/ediciones";
	}
	
	/**
	 * Peticion para eliminar una receta
	 * @param id de la receta
	 * @return pagina general de receta
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarReceta(@PathVariable(value="id")Long id) {
		Receta recetaEncontrada = recetaService.getBy(id); 
		recetaService.eliminar(recetaEncontrada);
		return "redirect:/recetas/ediciones";
	}
	
}