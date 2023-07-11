package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "ingredientes")
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingre_id")
	private Long id;
	
	@NotEmpty()
	@Size(min=6, max=60)
	@Column(name = "ingre_nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "rec_id")
	private Receta receta;

	@Column(name = "ingre_estado")
	private boolean estado;
	

	public Ingrediente() {
	}

	public Long getId() {
		return id;
	}

	public Ingrediente(Long id,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String nombre,
			Receta receta, boolean estado) {
		this.id = id;
		this.estado = estado;
		this.nombre = nombre;
		this.receta = receta;
		
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}