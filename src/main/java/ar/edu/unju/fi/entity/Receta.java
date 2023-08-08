package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "recetas")
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id")
	private Long id;

	@NotEmpty()
	@Size(min=6)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*")
	@Column(name = "rec_nombre")
	private String nombre;
	
	@NotBlank(message="Debe seleccionar una categoria")
	@Column(name = "rec_categoria", nullable = false)
	private String categoria;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="recetas-ingredientes", joinColumns=@JoinColumn(name="receta_id"),
	inverseJoinColumns=@JoinColumn(name="ingre_id"))
	@NotEmpty(message="Debe cargarse la lista de ingredientes")
	@Size(min = 1, message = "Debe seleccionar al menos un ingrediente")
	private List<Ingrediente> ingredientes;
	
	@NotEmpty()
	@Size(min=10)
	@Column(name = "rec_preparacion", columnDefinition = "TEXT")
	private String preparacion;
	

	@Column(name = "rec_imagen")
	private String imagen;

	@Column(name = "rec_estado")
	private boolean estado;
	
	public Receta() {
	}

	public Receta(Long id,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String nombre,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String categoria,
			@NotNull List<Ingrediente> ingredientes, @NotEmpty @Size(min = 60, max = 200) String preparacion,
			@NotEmpty @Size(min = 20, max = 100) @NotEmpty String imagen, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
		this.imagen = imagen;
		this.estado = estado;
	}


	public Long getId() {
		return id;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}


	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", ingredientes=" + ingredientes
				+ ", preparacion=" + preparacion + ", imagen=" + imagen + ", estado=" + estado
				+ "]";
	}
}