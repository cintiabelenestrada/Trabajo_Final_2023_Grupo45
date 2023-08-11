package ar.edu.unju.fi.controller;


import java.io.IOException;
import java.net.MalformedURLException;

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
	private UploadFile uploadFile;
	
	/**
	 * Obtiene la página de gestión de datos de receta.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista gestion_datos_receta.html.
	 */
	@GetMapping("/gestion")
	public String obtenerPaginaGestionDatosReceta(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
	    
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		return "gestion_datos_receta";
	}
	
	/**
	 * Obtiene la página para crear una nueva receta.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@GetMapping("/nuevo")
	public String obtenerPaginaNuevaReceta(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
	    
		boolean edicion=false;
		model.addAttribute("receta", recetaService.obtenerReceta());
		// model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	/**
	 * Guarda una receta y su imagen asociada en la base de datos.
	 *
	 * @param receta La receta a guardar.
	 * @param result El objeto BindingResult que contiene los resultados de la validación.
	 * @param imagen La imagen asociada a la receta.
	 * @return El objeto ModelAndView para la vista redirect:/receta/gestion.
	 */
	@PostMapping("/guardar")
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("receta") Receta receta, BindingResult result,
			@RequestParam("file")MultipartFile imagen, Model model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		ModelAndView mav = new ModelAndView("redirect:/receta/gestion");
		if (result.hasErrors()) {
			mav.setViewName("nueva_receta");
			// mav.addObject("ingredientes", ingredienteService.obtenerIngredientes());
			mav.addObject("edicion", false);
			return mav;
		}
		recetaService.guardarReceta(receta,imagen);
		return mav;
	}
	
	/**
	 * Obtiene la página para modificar una receta existente.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @param id El ID de la receta a modificar.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id")Long id) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		boolean edicion=true;

		model.addAttribute("receta", recetaService.buscarReceta(id));
		// model.addAttribute("ingredientes",ingredienteService.obtenerIngredientes());

		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	/**
	 * Modifica una receta existente y actualiza su imagen asociada si se proporciona una nueva imagen.
	 *
	 * @param recetaModificada La receta modificada.
	 * @param result El objeto BindingResult que contiene los resultados de la validación.
	 * @param imagen La nueva imagen asociada a la receta (opcional).
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@PostMapping("/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("receta")Receta recetaModificada, BindingResult result,
			@RequestParam("file")MultipartFile imagen,Model  model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		if (result.hasErrors()) {
			model.addAttribute("receta", recetaModificada);
			// model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
			return "nueva_receta";
		}
		recetaService.modificarReceta(recetaModificada,imagen);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		recetaService.eliminarReceta(id);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/cargar/{imagen}")
	 public ResponseEntity<Resource> goImage(@PathVariable String imagen, Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		Resource resource = null;
		try {
			resource = uploadFile.load(imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagen=\""+resource.getFilename()+"\"")
				.body(resource);
		
	}
	
	@GetMapping("/ver/{id}")
	public ModelAndView mostrarReceta(@PathVariable(value = "id")Long id,  Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", true);
		modelAndView.addObject("listaReceta", false);

		return modelAndView;
	}
	
	@GetMapping("/lista")
	public String mostrarRecetas(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		return "recetas";
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView verReceta(@PathVariable(value = "id")Long id,  Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Recetas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", false);
		modelAndView.addObject("listaReceta", true);
		return modelAndView;
	}
	
}