package ar.edu.unju.fi.lists;

import java.util.List;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import ar.edu.unju.fi.entity.Testimonio;

@Component
public class ListaTestimonio {

	private List<Testimonio> testimonio;

	
	public ListaTestimonio() {
		testimonio = new ArrayList<Testimonio>();
		
	}
	

	public List<Testimonio> getTestimonio() {
		return testimonio;
	}

	public void setTestimonio(List<Testimonio> testimonio) {
		this.testimonio = testimonio;
	}
	
	
}
