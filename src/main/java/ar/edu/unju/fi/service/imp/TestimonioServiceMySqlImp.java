package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;

@Service
public class TestimonioServiceMySqlImp implements ITestimonioService {
	
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	

	@Override
	public List<Testimonio> getTestimonios() {
		 return testimonioRepository.findByEstado(true);
		
	}

	@Override
	public Testimonio getTestimonioById(Long id) {
		 return testimonioRepository.findById(id).get();
		
	}

	@Override
	public void guardarTestimonio(Testimonio testimonio) {
		testimonioRepository.save(testimonio);

	}

	@Override
	public void actualizarTestimonio(Testimonio testimonioActualizado) {
		testimonioRepository.save(testimonioActualizado);

	}

	@Override
	public void eliminarTestimonio(Testimonio testimonio) {
	testimonio.setEstado(false);
	testimonioRepository.save(testimonio);
	}

	@Override
	public Testimonio getTestimonio() {
		return testimonio;
		
	}

}
