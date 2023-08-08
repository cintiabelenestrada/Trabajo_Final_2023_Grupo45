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

import ar.edu.unju.fi.entity.UnidadMedida;
import ar.edu.unju.fi.service.IUnidadMedidaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("unidadmedida")
public class UnidadMedidaController {

	@Autowired
	@Qualifier("unidadmedidaServiceMysqlImp")
	private IUnidadMedidaService unidadMedidaService;
	
	/**
	 * Obtiene la página de gestión de datos de unidadmedida.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista gestion_unidadmedida.html.
	 */
	@GetMapping("/gestion")
	public String obtenerPaginaGestionDatosUnidadMedidas(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
	    
		model.addAttribute("unidadmedidas", unidadMedidaService.obtenerUnidadMedidas());
		return "gestion_unidadmedida";
	}
	
	/**
	 * Obtiene la página para crear una nueva unidadmedida.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nuevo_unidadmedida.html.
	 */
	@GetMapping("/nuevo")
	public String obtenerPaginaNuevaUnidadMedida(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
	    
		boolean edicion=false;
		model.addAttribute("unidadmedida", unidadMedidaService.obtenerUnidadMedida());
		model.addAttribute("edicion", edicion);
		return "nuevo_unidadmedida";
	}
	
	/**
	 * Guarda una unidadmedida y su imagen asociada en la base de datos.
	 *
	 * @param unidadmedida La unidadmedida a guardar.
	 * @param result El objeto BindingResult que contiene los resultados de la validación.
	 * @return El objeto ModelAndView para la vista redirect:/unidadmedida/gestion.
	 */
	@PostMapping("/guardar")
	public ModelAndView postGuardarUnidadMedidaPage(@Valid @ModelAttribute("unidadmedida") UnidadMedida unidadmedida, BindingResult result,
			Model model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		ModelAndView mav = new ModelAndView("redirect:/unidadmedida/gestion");
		if (result.hasErrors()) {
			mav.setViewName("nuevo_unidadmedida");
			mav.addObject("edicion", false);
			return mav;
		}
		unidadMedidaService.guardarUnidadMedida(unidadmedida);
		return mav;
	}
	
	/**
	 * Obtiene la página para modificar una unidadmedida existente.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @param id El ID de la unidadmedida a modificar.
	 * @return El nombre de la vista nuevo_unidadmedida.html.
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarUnidadMedidaPage(Model model, @PathVariable(value = "id")Long id) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		boolean edicion=true;

		model.addAttribute("unidadmedida", unidadMedidaService.buscarUnidadMedidaId(id));

		model.addAttribute("edicion", edicion);
		return "nuevo_unidadmedida";
	}
	
	/**
	 * Modifica una unidadmedida existente y actualiza su imagen asociada si se proporciona una nueva imagen.
	 *
	 * @param unidadmedidaModificada La unidadmedida modificada.
	 * @param result El objeto BindingResult que contiene los resultados de la validación.
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nuevo_unidadmedida.html.
	 */
	@PostMapping("/modificar/{id}")
	public String modificarUnidadMedida(@Valid @ModelAttribute("unidadmedida")UnidadMedida unidadmedidaModificada, BindingResult result,Model  model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		if (result.hasErrors()) {
			model.addAttribute("unidadmedida", unidadmedidaModificada);
			return "nuevo_unidadmedida";
		}
		unidadMedidaService.modificarUnidadMedida(unidadmedidaModificada);
		return "redirect:/unidadmedida/gestion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarUnidadMedida(@PathVariable(value="id")Long id, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    UnidadMedida unidadMedida = unidadMedidaService.buscarUnidadMedidaId(id);
		if (unidadMedida != null) {
			unidadMedidaService.eliminarUnidadMedida(unidadMedida);
		}
		// unidadMedidaService.eliminarUnidadMedida(id);
		return "redirect:/unidadmedida/gestion";
	}

	@GetMapping("/ver/{id}")
	public ModelAndView mostrarUnidadMedida(@PathVariable(value = "id")Long id,  Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		
		ModelAndView modelAndView = new ModelAndView("unidadmedida");
		modelAndView.addObject("unidadmedida", unidadMedidaService.buscarUnidadMedidaId(id));
		modelAndView.addObject("gestion", true);
		modelAndView.addObject("listaUnidadMedida", false);

		return modelAndView;
	}
	
	@GetMapping("/lista")
	public String mostrarUnidadMedidas(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		model.addAttribute("unidadmedidas", unidadMedidaService.obtenerUnidadMedida());
		return "unidadmedidas";
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView verUnidadMedida(@PathVariable(value = "id")Long id,  Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Unidad de Medidas"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		
		ModelAndView modelAndView = new ModelAndView("unidadmedida");
		modelAndView.addObject("unidadmedida", unidadMedidaService.buscarUnidadMedidaId(id));
		modelAndView.addObject("gestion", false);
		modelAndView.addObject("listaUnidadMedida", true);
		return modelAndView;
	}
	
}