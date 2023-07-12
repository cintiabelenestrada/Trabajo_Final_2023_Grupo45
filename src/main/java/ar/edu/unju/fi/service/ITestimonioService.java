package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {

	List<Testimonio> getTestimonios();

	Testimonio getTestimonioById(Long id);

	void guardarTestimonio(Testimonio testimonio);

	void actualizarTestimonio(Testimonio testimonioActualizado);

	void eliminarTestimonio(Testimonio testimonio);

	Testimonio getTestimonio();

}
