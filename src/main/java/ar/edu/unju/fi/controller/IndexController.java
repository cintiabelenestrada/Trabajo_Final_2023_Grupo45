package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Usuario;

@Controller
@RequestMapping("/")
public class IndexController {
	@GetMapping("/index")
    public String getNuevoProductoPage(Model model) {
    	model.addAttribute("usuario", new Usuario());
    	return "index";
    }
}
