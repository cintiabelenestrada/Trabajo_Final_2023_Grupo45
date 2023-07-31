package ar.edu.unju.fi.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.util.UploadFile;
import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.entity.UnidadMedida;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.repository.IServiciosRepository;
import ar.edu.unju.fi.repository.IUnidadMedidaRepository;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.service.IRegistroService;
import ar.edu.unju.fi.service.IUnidadMedidaService;
import jakarta.validation.Valid;

@Controller
public class GestionController {
	
	@Autowired
	private IServiciosRepository imcPesoRepository;

	@Autowired
	private IRecetaRepository recetaRepository;

	@Autowired
	private IRegistroRepository registroRepository;
	
	@Autowired
	private IRegistroService iregisUs;

	@Autowired
	private IRecetaService recetaService;

	@Autowired
	private IIngredienteService ingredienteService;

	@Autowired
	private IUnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private IUnidadMedidaService unidadMedidaService;

	@Autowired
	private UploadFile uploadFile;

	
	@GetMapping("/gestionar")
	public String getGestionPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		model.addAttribute("unidadmedidas", unidadMedidaService.obtenerUnidadMedidas());
		return "gestion_datos";
	}

	/* #######################SECCION IMC########################### */
	@GetMapping("/gestion/imc")
	public String listarUsuarios(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		Iterable<Usuario> usuarios = registroRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "listar_usuarios";
	}	
	
	@GetMapping("/gestion/usuarios/{id}/imc")
	public String listarIMCUsuario(@PathVariable Long id, Model model) {
		
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
	    Usuario usuario = registroRepository.findById(id).orElse(null);
	    if (usuario != null) {
	        List<IndiceMasaCorporal> imcList = imcPesoRepository.findByUsuarioOrderByFechaImcDesc(usuario);
	        model.addAttribute("usuario", usuario);
	        model.addAttribute("imcList", imcList);
	        return "listar_imc_usuario";
	    } else {
	        return "error";
	    }
	}
	
	@GetMapping("/gestion/usuarios/{userId}/imc/{imcId}/eliminar")
	public String eliminarIMC(@PathVariable Long userId, @PathVariable Long imcId, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    imcPesoRepository.deleteById(imcId);
	    return "redirect:/gestion/usuarios/" + userId + "/imc";
	}
	
	/* #######################SECCION RECETAS########################### */

	@PostMapping("/gestionar")
	public String getCalcuImcPage(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult usuarioResult,
			Model model) {

		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		if (usuario.getId() == null) {

			model.addAttribute("usuario", new Usuario());
			model.addAttribute("imc", new IndiceMasaCorporal());
			model.addAttribute("idNulo", "No puedes dejar este campo vacio");
			return "gestion_datos";
		}

		Usuario usuarioEncontrado = registroRepository.findById(usuario.getId()).orElse(null);
		if (usuarioEncontrado != null) {

			model.addAttribute("usuario", usuarioEncontrado);
			model.addAttribute("imc", new IndiceMasaCorporal());
			return "gestion_datos";
		} else {

			model.addAttribute("usuario", new Usuario());
			model.addAttribute("imc", new IndiceMasaCorporal());
			model.addAttribute("idNoExiste", "No se encontró un usuario con este ID");
			return "gestion_datos";
		}
	}

	/**
	 * Obtiene la página para crear una nueva receta.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@GetMapping("/gestionar/nuevo")
	public String obtenerPaginaNuevaReceta(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		boolean edicion = false;
		model.addAttribute("receta", recetaService.obtenerReceta());
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}

	/**
	 * Guarda una receta y su imagen asociada en la base de datos.
	 *
	 * @param receta La receta a guardar.
	 * @param result El objeto BindingResult que contiene los resultados de la
	 *               validación.
	 * @param imagen La imagen asociada a la receta.
	 * @return El objeto ModelAndView para la vista redirect:/receta/gestion.
	 */
	@PostMapping("/gestionar/guardar")
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("receta") Receta receta, BindingResult result,
			
			@RequestParam("file") MultipartFile imagen, Model model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		ModelAndView mav = new ModelAndView("redirect:/receta/gestion");
		if (result.hasErrors()) {
			mav.setViewName("nueva_receta");
			mav.addObject("ingredientes", ingredienteService.obtenerIngredientes());
			mav.addObject("edicion", false);
			return mav;
		}
		recetaService.guardarReceta(receta, imagen);
		return mav;
	}

	/**
	 * Obtiene la página para modificar una receta existente.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @param id    El ID de la receta a modificar.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@GetMapping("/gestionar/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id") Long id) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		boolean edicion = true;

		model.addAttribute("receta", recetaService.buscarReceta(id));
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());

		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}

	/**
	 * Modifica una receta existente y actualiza su imagen asociada si se
	 * proporciona una nueva imagen.
	 *
	 * @param recetaModificada La receta modificada.
	 * @param result           El objeto BindingResult que contiene los resultados
	 *                         de la validación.
	 * @param imagen           La nueva imagen asociada a la receta (opcional).
	 * @param model            El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista nueva_receta.html.
	 */
	@PostMapping("/gestionar/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("receta") Receta recetaModificada, BindingResult result,
			@RequestParam("file") MultipartFile imagen, Model model) throws IOException {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);

		if (result.hasErrors()) {
			model.addAttribute("receta", recetaModificada);
			model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
			return "nueva_receta";
		}
		recetaService.modificarReceta(recetaModificada, imagen);
		return "redirect:/receta/gestion";
	}

	@GetMapping("/gestionar/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value = "id") Long id, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		recetaService.eliminarReceta(id);
		return "redirect:/receta/gestion";
	}

	@GetMapping("/gestionar/cargar/{imagen}")
	public ResponseEntity<Resource> goImage(@PathVariable String imagen, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		Resource resource = null;
		try {
			resource = uploadFile.load(imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagen=\"" + resource.getFilename() + "\"")
				.body(resource);

	}

	@GetMapping("/gestionar/ver/{id}")
	public ModelAndView mostrarReceta(@PathVariable(value = "id") Long id, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", true);
		modelAndView.addObject("listaReceta", false);

		return modelAndView;
	}
	// GGG
	// Muestra un listado de las recetas, 
	// por lo tanto se deberia llamar receta/lista o algo asi
	@GetMapping("/lista")
	public String mostrarRecetas(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		return "recetas";
	}

	@GetMapping("/visualizar/{id}")
	public ModelAndView verReceta(@PathVariable(value = "id") Long id, Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", false);
		modelAndView.addObject("listaReceta", true);
		return modelAndView;
	}
	
	@GetMapping("/gestion/usuarios")
	public String listarUsuariosParamodificar(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		Iterable<Usuario> usuarios = registroRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "listar_usuarioscrud";
	}
	//Metoo ("/gestion/usuarios/{id}/imc")
	@GetMapping("/gestion/modificar/usuarios/{id}")
	public String getModificarUsuairo(Model model, @PathVariable(value = "id") Long id) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Gestión de Datos"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		    	Optional<Usuario>usuario=iregisUs.listarId(id);
		    	model.addAttribute("usuario", usuario);
		    	//model.addAttribute("categorias_productos", icateSer.getCategorias());
		    	return"registro";
		    
	}

	//UNIDAD MEDIDA
	@GetMapping("gestion/unidadmedida")
	public String mostrarUnidadMedida(Model model){
		String tituloPagina = "Gestión de Datos"; 
		model.addAttribute("tituloPagina", tituloPagina);
		model.addAttribute("unidadmedidas", unidadMedidaService.obtenerUnidadMedidas());
		return "unidadmedidas";
	}

	@GetMapping("/gestionar/nueva/unidadmedida")
	public String obtenerPaginaNuevaUnidadMedida(Model model) {
		// Aquí puedes establecer algún valor para el título de la página si lo necesitas
		String tituloPagina = "Gestión de Datos";
		model.addAttribute("tituloPagina", tituloPagina);
	
		boolean edicion = false;
		model.addAttribute("unidadmedida", unidadMedidaService.obtenerUnidadMedida());
		model.addAttribute("edicion", edicion);
		return "nuevo_unidadmedida";
	}

	@PostMapping("/gestionar/guardar/unidadmedida")
	public ModelAndView postGuardarUnidadMedida(@Valid @ModelAttribute("unidadMedida") UnidadMedida unidadMedida, BindingResult result, Model model) {
		// Aquí puedes establecer algún valor para el título de la página si lo necesitas
		String tituloPagina = "Gestión de Datos";
		model.addAttribute("tituloPagina", tituloPagina);
	
		// ModelAndView mav = new ModelAndView("redirect:/receta/gestion");
		ModelAndView mav = new ModelAndView("redirect:/unidadmedida/gestion");

		if (result.hasErrors()) {
			mav.setViewName("nuevo_unidadmedida");
			mav.addObject("edicion", false);
			return mav;
		}
		unidadMedidaService.guardarUnidadMedida(unidadMedida);
		return mav;
	}

}
