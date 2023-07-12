package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IRegistroRepository;
import ar.edu.unju.fi.service.IRegistroService;

@Service
public class RegistroServiceMysqlImp implements IRegistroService{
	@Autowired
	private
	IRegistroRepository regisRepo;
	
	@Override
	public Usuario save(Usuario usuario) {
		
		usuario.setEstado(true);
		return regisRepo.save(usuario);
		
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return regisRepo.findById(id);
	
	}
	@Override
	public Optional<Usuario> listarId(Long id) {
		return regisRepo.findById(id);
		
	}
	
	@Override
	public void eliminar(Long id, boolean esta){
		
		Optional<Usuario> objetoOptional = regisRepo.findById(id);

        if (objetoOptional.isPresent()) {
        	Usuario objeto = objetoOptional.get();
            objeto.setEstado(esta);
            regisRepo.save(objeto);
        } else {
            throw new IllegalArgumentException("El objeto con el ID " + id + " no existe.");
        }
    }
}
