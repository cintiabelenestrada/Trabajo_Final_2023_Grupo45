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

	@Id /* clave primaria */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* genera valores numericos secuenciales */
	@Column(name = "receta_id")
	private Long id;

	@NotEmpty(message = "El nombre no puede estar vacio")
	@Size(min = 10, message = "El nombre debe tener como minimo 10 caracteres")
	@Column(name = "receta_nombre", length = 55, nullable = false)
	private String nombre;

	@NotBlank(message = "Debe seleccionar una categoria")
	@Column(name = "receta_categ", nullable = false)
	private String categoria;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "recetas-ingredientes", joinColumns = @JoinColumn(name = "receta_id"), inverseJoinColumns = @JoinColumn(name = "ingre_id"))
	@NotEmpty(message = "Debe cargarse la lista de ingredientes")
	@Size(min = 1, message = "Debe seleccionar al menos un ingrediente")
	private List<Ingrediente> ingredientes;

	@Size(min = 20, message = "La preparación debe tener al menos 20 caracteres")
	@NotEmpty(message = "La preparacion no puede estar vacia")
	@Column(name = "receta_prep", nullable = false)
	private String preparacion;

//	@NotEmpty(message = "El campo imagen no puede estar vacio")
//	@Column(name = "receta_imagen", nullable = false)
//	private String imagen;

	@Column(name = "receta_estado")
	private boolean estado;

	/**
	 * Constructor por defecto
	 */
	public Receta() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param id
	 * @param nombre
	 * @param categoria
	 * @param ingredientes
	 * @param preparacion
	 * @param imagen
	 * @param estado
	 */
	public Receta(Long id, String nombre, String categoria, List<Ingrediente> ingredientes, String preparacion,
			 boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
//		this.imagen = imagen;
		this.estado = estado;
	}

	/**
	 * Metodo para obtener el id de la receta
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * MEtodo para guardar el id de la receta
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo para obtener el nombre de la receta
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para guardar el nombre de la receta
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener la categoria de la receta
	 * 
	 * @return categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Metodo para guardar la categoria de la receta
	 * 
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Metodo para obtener la lista de ingredientes
	 * 
	 * @return ingredientes
	 */
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	/**
	 * Metodo para guardar un ingrediente
	 * 
	 * @param ingredientes
	 */
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * Metodo para obteneer la preparacion de la receta
	 * 
	 * @return preparacion
	 */
	public String getPreparacion() {
		return preparacion;
	}

	/**
	 * Metodo para guardar la preparacion
	 * 
	 * @param preparacion
	 */
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	/**
	 * Metodo para obtener la imagen de la receta
	 * 
	 * @return
	 */
//	public String getImagen() {
//		return imagen;
//	}
//
//	/**
//	 * MEtodo para guardar una imagen
//	 * 
//	 * @param imagen
//	 */
//	public void setImagen(String imagen) {
//		this.imagen = imagen;
//	}

	/**
	 * Metodo para conocer el estado de la receta (true existe, false no existe)
	 * 
	 * @return estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * Metodo para guardar el estado de la receta
	 * 
	 * @param estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}