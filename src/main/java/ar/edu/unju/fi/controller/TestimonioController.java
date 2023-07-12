package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.service.ITestimonioService;

@Controller
@RequestMapping
public class TestimonioController {
	
	@Autowired
	private ITestimonioService testimonioService;
	
	@GetMapping("/testimonio/listar")
	public String listarTestimonios(Model model) {
		model.addAttribute("testimonios", testimonioService.getTestimonios());
		return "listar_testimonios";
	}
	
	@GetMapping("/testimonio/ver/{id}")
	public String verTestimonio(@PathVariable Long id, Model model) {
		Testimonio testimonio = testimonioService.getTestimonioById(id);
		model.addAttribute("testimonio", testimonio);
		return "ver_testimonio";
	}
	
	@GetMapping("/testimonio/nuevo")
	public String crearTestimonio(Model model) {
		boolean editando = false;
		Testimonio testimonio = testimonioService.getTestimonio();
		model.addAttribute("editando", editando);
		model.addAttribute("testimonio", testimonio);
		return "crear_testimonio";
	}
	
	@PostMapping("/testimonio/guardar")
	public String guardarTestimonio(Testimonio testimonio) {
		testimonioService.guardarTestimonio(testimonio);
		return "listar_testimonios";
	}
	
//	@GetMapping("/{id}/editar")
//	public String editarTestimonio(@PathVariable Long id, Model model) {
//		Testimonio testimonio = testimonioService.getTestimonioById(id);
//		model.addAttribute("testimonio", testimonio);
//		return "editar_testimonio";
//	}
	
	@GetMapping("/modificar/{id}")
	public String actualizarTestimonio(@PathVariable Long id, Testimonio testimonioActualizado) {
		testimonioActualizado.setId(id);
		testimonioService.actualizarTestimonio(testimonioActualizado);
		return "listar_testimonios";
	}
	
	@PostMapping("/modificar/")
	public String eliminarTestimonio(@PathVariable Long id) {
		Testimonio testimonio = testimonioService.getTestimonioById(id);
		testimonioService.eliminarTestimonio(testimonio);
		return "listar_testimonios";
	}
}
