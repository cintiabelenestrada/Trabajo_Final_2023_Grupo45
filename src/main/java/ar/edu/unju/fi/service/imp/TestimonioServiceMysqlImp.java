package ar.edu.unju.fi.service.imp;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Testimonio;

import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;

@Service("TestimonioServiceMysql")
public class TestimonioServiceMysqlImp implements ITestimonioService{

	
	@Autowired
	public ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	@Override
	public List<Testimonio> getListaTestimonio() {
		
		return testimonioRepository.findByEstado(true);
	}

	@Override
	public void guardar(Testimonio testimonio) {
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
		
	}

	

	@Override
	public void eliminar(Testimonio testimonioEncontrado) {
		testimonio.setEstado(false);
		testimonioRepository.delete(testimonioEncontrado);
		
	}

	@Override
	public Testimonio getTestimonio() {
		
		return testimonio;
	}

	

	@Override
	public void modificar(Testimonio testimonio, Long id) {
		testimonio.setId(id);
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
		
	}

	@Override
	public Testimonio getBy(Long id) {
		return testimonioRepository.findById(id).get();
	}
	
	public List<Testimonio> buscarTestimonioPorFecha(LocalDate fecha) {
        return testimonioRepository.findByFecha(fecha);
    }
}