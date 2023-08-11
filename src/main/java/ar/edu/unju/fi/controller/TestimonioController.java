package ar.edu.unju.fi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.service.ITestimonioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/testimonios")
public class TestimonioController {
	
	
	@Autowired 
	@Qualifier("TestimonioServiceMysql")
	private ITestimonioService testimonioService;
	
	
	/**
	 * Peticion para dirigirse a la pagina testimonio
	 * @param model usado para agregar a la vista la lista de testimonios
	 * @return pagina testimonio
	 */
	@GetMapping("/listado")
	public String getTestimonioPage(Model model){
		// Si en Inicio se selecciona contacto, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Testimonios"; // Establece el valor por defecto que se vera en el header
	    model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
		model.addAttribute("testimonio", testimonioService.getListaTestimonio());
		model.addAttribute("edicion", false);
		return "testimonios";
		
	}
	
	@GetMapping("/edicion")
	public String getTestimonioPage2(Model model){
		model.addAttribute("testimonio", testimonioService.getListaTestimonio());
		model.addAttribute("edicion", true);
		return "testimonios";
		
	}
	/**
	 * Peticion para dirigirse a la pagina nuevo_testimonio
	 * @param model vincula la variable testimonio de tipo Testimonio al formulario
	 * la variable edicion identifica la accion dentro de la pagina 
	 * si es false se va a ingresar un nuevo testimonio
	 * si es true se va a modificar un testimonio
	 * * @return pagina nuevo_testimonio
	 */
	@GetMapping("/nuevo")
	public String getNuevoTestimonio(Model model) {
		boolean edicion = false;
		model.addAttribute("testi", testimonioService.getTestimonio());
		model.addAttribute("edicion", edicion);
		return "nuevo_testimonio";
	}
	/**
	 * Peticion para guardar el objeto testimonio dentro de la Base de datos
	 * @param testimonio es el objeto a guardar
	 * @param result activa las validaciones dentro del modelo
	 * @return pagina nuevo_testimonio
	 */
	@PostMapping("/guardar")
	public ModelAndView getGuardarTestimonioPage(@Valid @ModelAttribute("testi") Testimonio testimonio, BindingResult result ) {
		ModelAndView modelView = new ModelAndView("testimonios");
		if(result.hasErrors()) {
			modelView.setViewName("nuevo_testimonio");
			modelView.addObject("testi", testimonio);
		}
		testimonioService.guardar(testimonio);
		modelView.addObject("testimonio", testimonioService.getListaTestimonio());
		return modelView;
	}
	/**
	 * Peticion para modificar un testimonio
	 * @param model usado para enviar el objeto encontrado a la pagina
	 * @param id contiene el id del testimonio a modificar
	 * @return pagina nuevo_testimonio
	 */
	
	
	@GetMapping("/modificar/{id}")
	private String getModificarTestimonioPage(Model model, @PathVariable(value="id") Long id) {
		Testimonio testimonioEncontrado = testimonioService.getBy(id);
		boolean edicion = true;
		
		model.addAttribute("testi", testimonioEncontrado);
		model.addAttribute("edicion", edicion);
		
		return "nuevo_testimonio";
			}
		
	/**
	 *  Peticion para guardar el objeto modificado testimonio tiene los valores cambiados
	 * @param testimonio objeto que contiene a los testimonios
	 * @param result activa las validaciones
	 * @param model vincula la variable testimonio al formulario
	 * @return pagina nuevo_testimonio
	 */
	
	@PostMapping("/modificar")
	public String modificarTestimonio(@Valid @ModelAttribute("testi") Testimonio testimonio, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("edicion", true); 
			return "nuevo_testimonio";
		} 
		testimonioService.modificar(testimonio, testimonio.getId());
		
		return "redirect:/testimonios/edicion";
	}

	/**
	 * Petición para eliminar un servicio según el id
	 * @param id a eliminar
	 * @return testimonio
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id")Long id) {
		Testimonio testimonioEncontrado = testimonioService.getBy(id);
		testimonioService.eliminar(testimonioEncontrado);
		return "redirect:/testimonios/edicion";
	}
	/**
	 * 
	 * @param fecha
	 * @param model
	 * @return
	 */
	@PostMapping("/buscar")
	public String buscarTestimonioPorFecha(@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha, Model model) {
		List<Testimonio> testimonios = testimonioService.buscarTestimonioPorFecha(fecha);
	   
		model.addAttribute("fecha", fecha);
		model.addAttribute("testimonio", testimonios);
	    return "testimonios";
	}
}
