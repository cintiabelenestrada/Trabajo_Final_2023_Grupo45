package src.main.java.ar.edu.unju.fi.tp_final_grupo_45.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.entity.Usuario;
import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.repository.IRegistroRepository;
import src.main.java.ar.edu.unju.fi.tp_final_grupo_45.service.IRegistroService;

/**
 * Implementación del servicio de productos que utiliza MySQL como base de datos.
 *
 * @author Federico Nicolas Burgos
 * @version 1.0.1 Date:30/06/2023
 */
@Service
public class RegistroServiceMysqlImp implements IRegistroService{
	@Autowired
	private
	IRegistroRepository regisRepo;
	
	@Override
	public void save(Usuario usuario) {
		
		usuario.setEstado(true);
		regisRepo.save(usuario);
		
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}
}
