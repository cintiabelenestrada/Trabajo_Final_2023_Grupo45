package ar.edu.unju.fi.controller;



import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import ar.edu.unju.fi.entify.Articulo;
//import ar.edu.unju.fi.service.imp.ProductoServiceImp;
import jakarta.validation.Valid;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IRegistroService;

/**
 * 
 * 
 * 
 * @author Federico Nicolas Burgos
 * Esto es el controlador de registrar y posea todos los metodos que recibiran las peticiones que realice vista.
 * @version 1.0.1 date=30/06/2023
 *
 */
@Controller
@RequestMapping("/inicio")
public class RegistroController {
	@Autowired
	//@Qualifier("registroServiceMysql")
	//private IProductoRepository productoRepository;
	private IRegistroService iregisUs;
	
    
    // Muestra la página para agregar un nuevo producto
	 @GetMapping("/usuario")
	    public String getNuevoProductoPage(Model model) {
		// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
		    String tituloPagina = "Registro"; // Establece el valor por defecto que se vera en el header
		    // Se realiza el cambio de valor de `tituloPagina`
		    model.addAttribute("tituloPagina", tituloPagina);
		    
		 	
			model.addAttribute("tituloPagina", tituloPagina); //obtiene el titulo para el header
			
	    	boolean edicion = false;
	    	model.addAttribute("usuario", new Usuario());
	   // 	model.addAttribute("edicion", edicion);
	    	return "registro";
	    }
	    
	    /* Guarda un nuevo producto en la lista*/
	    @PostMapping("/guardar")
	    public String getguardarProductoPage(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
	    	
	    	// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
		    String tituloPagina = "Registro"; // Establece el valor por defecto que se vera en el header
		    // Se realiza el cambio de valor de `tituloPagina`
		    model.addAttribute("tituloPagina", tituloPagina);
	    	//return "redirect:/productos/listado";
	    	ModelAndView modelView = new ModelAndView("usuarios");
	   	if(result.hasErrors()) {
	    		modelView.setViewName("index");
	   		modelView.addObject("usuario", usuario);
	    		modelView=new ModelAndView("index");
	    		//return modelView;
	    		return "registro";
	    		
	    	}else {
	    		
	    		iregisUs.save(usuario);
	    		final Long CD = usuario.getId();
	    		return "redirect:/inicio/idusuario/"+CD;
	    	}
	    }   
    @GetMapping("/idusuario/{CD}")
    // muestra un usuario segun
     public String listar(@PathVariable("CD") Long CD,Model model) {
    	
    	// Si en Inicio se selecciona esta opcion, el header cambiara el titulo por la opcion seleccionada
	    String tituloPagina = "Registro"; // Establece el valor por defecto que se vera en el header
	    // Se realiza el cambio de valor de `tituloPagina`
	    model.addAttribute("tituloPagina", tituloPagina);
	    
    	Optional<Usuario>usuarios=iregisUs.findById(CD);
        
        if (usuarios.isPresent()) {
            model.addAttribute("usuarios", Arrays.asList(usuarios.get()));
        } else {
            model.addAttribute("usuarios", Collections.emptyList());
        }
        
        //return "codigodeusuario";
        // model.addAttribute("usuarios", usuarios);
         return "codigodeusuario";
     }
    
    
}