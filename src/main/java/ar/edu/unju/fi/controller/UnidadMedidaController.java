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

import ar.edu.unju.fi.entity.UnidadMedida;
import ar.edu.unju.fi.service.IUnidadMedidaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("unidadmedida")
public class UnidadMedidaController {

    @Autowired
    @Qualifier("unidadmedidaServiceMysqlImp")
    private IUnidadMedidaService unidadMedidaService;

    @GetMapping("/gestion")
	public ModelAndView obtenerPaginaGestionUnidadMedida(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "UnidadMedida"; // Establece el valor por defecto que se vera en el header
		// Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    //en esta parte llamo a los htmls
		ModelAndView modelAndView = new ModelAndView("gestion_unidadmedida");
		modelAndView.addObject("unidadmedidas", unidadMedidaService.obtenerUnidadMedidas());
		return modelAndView;
	}

	@GetMapping("/lista")
	public String mostrarUnidadMedida(Model model) {
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "UnidadMedidas"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
		model.addAttribute("unidadmedidas", unidadMedidaService.obtenerUnidadMedida());
		return "unidadmedidas";
	}
	
    // /**
    //  * @param model
    //  * @return
    //  */
    // @GetMapping("/nuevo")
	// public ModelAndView obtenerPaginaGestionUnidadMedida(Model model) {
	// 	// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	//     String tituloPagina = "UnidadMedida"; // Establece el valor por defecto que se vera en el header
	// 	// Se realiza el cambio de valor de `tituloPagina`
	//     model.addAttribute("tituloPagina", tituloPagina);


    // boolean edicion=false;
	//     //en esta parte llamo a los htmls
    //     model.addAttribute("unidadmedidas",unidadMedidaService.obtenerUnidadMedida());
	// 	model.addAttribute("edicion",edicion);
    //     return "nuevo_unidadmedida";
	// }

    // @PostMapping("/guardar")
    // public ModelAndView postGuardarUnidadMedidaPage(@Valid @ModelAttribute("unidadmedida") UnidadMedida unidadmedida, BindingResult bindingResult, Model model){ 
    //     // BindingResult bindingResult: Es un objeto que almacena los resultados de la validación. Si hay errores de validación, se almacenarán aquí.
    //     String tituloPagina = "UnidadMedida";
    //     model.addAttribute("tituloPagina", tituloPagina);
	    
	// 	ModelAndView mav = new ModelAndView("redirect:/unidadmedida/gestion");
		
	// 	if (bindingResult.hasErrors()) {
	// 		mav.setViewName("nuevo_unidadmedida");
	// 		mav.addObject("unidadmedidas", unidadMedidaService.obtenerUnidadMedida());
	// 		mav.addObject("edicion", false);
	// 		return mav;
	// 	}
		
	// 	unidadMedidaService.guardarUnidadMedida(unidadmedida);
	// 	return mav;
	// }
    
    // @GetMapping("/modificar/{id}")
	// public String getModificarUnidadMedidaPage(Model model, @PathVariable(value = "id")Long id) {
	//     String tituloPagina = "UnidadMedida";
    //     model.addAttribute("tituloPagina", tituloPagina);
	    
	// 	boolean edicion=true;
	// 	// UnidadMedida unidadmedidaEncontrado = unidadMedidaService.buscarUnidadMedida(id);
	// 	model.addAttribute("recetas", unidadMedidaService.obtenerUnidadMedida());

	// 	model.addAttribute("ingrediente", ingredienteEncontrado);
	// 	model.addAttribute("edicion", edicion);
	// 	return "nuevo_ingrediente";
	// }

    @GetMapping("/listar")
    public String listarUnidadMedidas(Model model){
        model.addAttribute("unidadMedidas", unidadMedidaService.obtenerUnidadMedidas());
        return "unidadmedidas";
    }
}
