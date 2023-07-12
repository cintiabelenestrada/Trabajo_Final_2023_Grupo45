package ar.edu.unju.fi.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


//import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IServiciosRepository;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.service.IServiciosService;
import jakarta.validation.Valid;

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
		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Cálculo IMC"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("imc", new IndiceMasaCorporal());

		return "calcular_imc";
	}


	
	
	@PostMapping("/datosusuarioimc")
	public String getCalcuImcPage(@Valid @ModelAttribute("usuario") Usuario usuario,BindingResult usuarioResult, Model model) {

		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Cálculo IMC"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    if (usuario.getId() == null) {
			model.addAttribute("tituloPagina", tituloPagina);
			
	        model.addAttribute("usuario", new Usuario());
	        model.addAttribute("imc", new IndiceMasaCorporal());
	        model.addAttribute("idNulo", "No puedes dejar este campo vacio");
	        return "calcular_imc";
	    }

	   
	    Usuario usuarioEncontrado = registroRepository.findById(usuario.getId()).orElse(null);
	    if (usuarioEncontrado != null) {
			model.addAttribute("tituloPagina", tituloPagina); 
	        model.addAttribute("usuario", usuarioEncontrado);
	        model.addAttribute("imc", new IndiceMasaCorporal());
	        return "calcular_imc";
	    } else {
			model.addAttribute("tituloPagina", tituloPagina); 
	        model.addAttribute("usuario", new Usuario());
	        model.addAttribute("imc", new IndiceMasaCorporal());
	        model.addAttribute("idNoExiste", "No se encontró un usuario con este ID");
	        return "calcular_imc";
	    }
	}

	@PostMapping("/calcular_imc")
	public String calcularIMC(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "pesoActual", required = false) Integer pesoActual, Model model) {
		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Cálculo IMC"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
		
		LocalDate fechaActual = LocalDate.now();
		if(pesoActual == null ||pesoActual<0 || id==null) {
			model.addAttribute("tituloPagina", tituloPagina);
			 model.addAttribute("usuario", new Usuario());
			 model.addAttribute("imc", new IndiceMasaCorporal());
			model.addAttribute("errorPeso", "No puedes dejar este campo vacio. El valor debe ser positivo.");
			return "calcular_imc";
		
		}else {
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
	
				return "error";
			}
		}
	}


	@GetMapping("/pesoideal")
	public String getPesoIdealPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
				String tituloPagina = "Peso Ideal"; // Establece el valor por defecto que se vera en el header
			    // Se realiza el cambio de valor de `tituloPagina`
			    model.addAttribute("tituloPagina", tituloPagina);
		model.addAttribute("tituloPagina", tituloPagina);
		
		model.addAttribute("usuario", new Usuario());
		return "peso_ideal";
	}

	
	
	@PostMapping("/pesoideal")
	public String getPesoIPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
				String tituloPagina = "Peso Ideal"; // Establece el valor por defecto que se vera en el header
			    // Se realiza el cambio de valor de `tituloPagina`
			    model.addAttribute("tituloPagina", tituloPagina);

	    if (usuario.getId() == null) {
			model.addAttribute("tituloPagina", tituloPagina); 
	        model.addAttribute("usuario", new Usuario());

	        model.addAttribute("idNulo2", "No puedes dejar este campo vacio");
	        return "peso_ideal";
	    }

	   /**
	    * Busca el usuario en el repositorio a traves del id
	    * evalua si es distinto de nulo
	    * */
	    Usuario usuarioEncontrado = registroRepository.findById(usuario.getId()).orElse(null);
	    if (usuarioEncontrado != null) {
			model.addAttribute("tituloPagina", tituloPagina); 
			model.addAttribute("usuario", usuarioEncontrado);
			 int edad = Period.between(usuarioEncontrado.getFecha_nacimiento(), LocalDate.now()).getYears();
			   model.addAttribute("edad", edad);
			double pesoIdeal = imcPesoService.calcularPesoIdeal(usuarioEncontrado);

			/**
			 * Agrega el resultado al modelo
			 * */
			model.addAttribute("pesoIdeal", pesoIdeal);

			return "peso_ideal";
	    } else {
			model.addAttribute("tituloPagina", tituloPagina);
	        model.addAttribute("usuario", new Usuario());
	    
	        model.addAttribute("idNoExiste2", "No se encontró un usuario con este ID");
	        return "peso_ideal";
	    }
	}
	
	


}