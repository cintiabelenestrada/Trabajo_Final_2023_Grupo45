package ar.edu.unju.fi.service;

import java.time.LocalDate;
import java.util.List;
import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	List<Testimonio> getListaTestimonio();
	
	void guardar(Testimonio testimonio);
	
	
	
	void eliminar (Testimonio testimonioEncontrado);
	
	Testimonio getTestimonio();
	
	Testimonio getBy(Long id);

	void modificar(Testimonio testimonio, Long id);
	
	public List<Testimonio> buscarTestimonioPorFecha(LocalDate fecha);
}