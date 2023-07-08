package src.main.java.ar.edu.unju.fi.tp_final_grupo_45.controller;



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
import org.springframework.web.servlet.ModelAndView;

//import ar.edu.unju.fi.entify.Articulo;
//import ar.edu.unju.fi.service.imp.ProductoServiceImp;
import jakarta.validation.Valid;
import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.entity.Usuario;
import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.service.IRegistroService;

/**
 * 
 * 
 * 
 * @author freinicks
 * Esto es el controlador de registrar y posea todos los metodos que recibiran las peticiones que realice vista.
 * @version 1.0.1 date=30/06/2023
 *
 */
@Controller
@RequestMapping("/index")
public class RegistroController {
	@Autowired
	//@Qualifier("registroServiceMysql")
	//private IProductoRepository productoRepository;
	private IRegistroService iregisUs;
	
    
    // Muestra la página para agregar un nuevo producto
    @GetMapping("/usuario")
    public String getNuevoProductoPage(Model model) {
    //	boolean edicion = false;
    	model.addAttribute("usuario", new Usuario());
   // 	model.addAttribute("edicion", edicion);
    	return "registro";
    }
    
    // Guarda un nuevo producto en la lista
    @PostMapping("/guardar")
    public String getguardarProductoPage(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
    	
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
    		return "redirect:/index/idusuar/"+CD;
    	}
    }   
    @GetMapping("/idusuario/{CD}")
    // public String getListaProductoPage(Model model) {
     public String listar(Model model, @PathVariable("CD") long CD) {
    	Optional<Usuario>usuarios=iregisUs.findById(CD);
    	
         model.addAttribute("usuarios", usuarios);
         return "codigodeusuario";
     }
    
}