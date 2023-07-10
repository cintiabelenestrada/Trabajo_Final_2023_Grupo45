package ar.edu.unju.fi.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IServiciosRepository;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.service.IServiciosService;

@Controller
public class ServiciosController {

	@Autowired
	private IRegistroRepository registroRepository;

//	@Autowired IImcService servicioService;
	@Autowired
	private IServiciosService imcPesoService;

	@Autowired
	private IServiciosRepository imcPesoRepository;
	
	@GetMapping("/datosusuarioimc")
	public String getCalculoImcPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		String tituloPagina = "Calculo IMC";
		model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("imc", new IndiceMasaCorporal());

		return "calcular_imc";
	}

	@PostMapping("/datosusuarioimc")
	public String getCalcuImcPage(@RequestParam("id") Long id, Model model) {
		Usuario usuario = registroRepository.findById(id).orElse(null);
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("imc", new IndiceMasaCorporal());

			return "calcular_imc";
		} else {
			 model.addAttribute("usuario", new Usuario());
			 model.addAttribute("imc", new IndiceMasaCorporal());
			model.addAttribute("error", "No se encontró un usuario con este ID");
			return "calcular_imc";
		}

	}

	@PostMapping("/calcular_imc")
	public String calcularIMC(@RequestParam("id") Long id, @RequestParam("pesoActual") int pesoActual, Model model) {
		LocalDate fechaActual = LocalDate.now(); // Establecer la fecha actual

		Usuario usuario = registroRepository.findById(id).orElse(null);
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("imc", new IndiceMasaCorporal());
			String mensaje = imcPesoService.calcularIMC(usuario, pesoActual, fechaActual);
			model.addAttribute("mensaje", mensaje);

			  List<IndiceMasaCorporal> imcList = imcPesoRepository.findAllByOrderByFechaImcDesc();
			    model.addAttribute("imcList", imcList);
			return "calcular_imc";
		} else {
			// Código de usuario no válido, manejar el caso según tus necesidades
			return "error";
		}
	}

	@GetMapping("/pesoideal")
	public String getPesoIdealPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		String tituloPagina = "Peso Ideal";
		model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
		
		model.addAttribute("usuario", new Usuario());
		return "peso_ideal";
	}

	@PostMapping("/pesoideal")
	public String calcularPesoIdeal(@RequestParam("id") Long id, Model model) {
		Usuario usuario = registroRepository.findById(id).orElse(null);
		
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
			 int edad = Period.between(usuario.getFecha_nacimiento(), LocalDate.now()).getYears();
			   model.addAttribute("edad", edad);
			double pesoIdeal = imcPesoService.calcularPesoIdeal(usuario);

			// Agregar el resultado al modelo
			model.addAttribute("pesoIdeal", pesoIdeal);

			return "peso_ideal";
		} else {
			 model.addAttribute("usuario", new Usuario());
			 model.addAttribute("imc", new IndiceMasaCorporal());
			model.addAttribute("error", "No se encontró un usuario con este ID");
			return "peso_ideal";
		}
	}
}
