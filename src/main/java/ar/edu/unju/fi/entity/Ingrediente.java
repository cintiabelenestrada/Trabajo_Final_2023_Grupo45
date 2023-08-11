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

	//Atributos de la clase Ingrediente
	@Id/*clave primaria*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ingre_id")	
	private Long id; //id de ingrediente
	
	@NotEmpty(message="El nombre no puede estar vacio")
	@Size(min=3, message="El nombre debe tener minimo 3 caracteres")
	@Column(name="ingre_nombre", nullable = false)
	private String nombre; //nombre de ingrediente
	
	@Column(name="ingre_estado")
	private boolean estado; //estado que sirve para la eliminación lógica
	
	/**
	 * Constructor por defecto
	 */
	public Ingrediente() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Constructor parametrizado
	 * @param id del ingrediente
	 * @param nombre del ingrediente
	 * @param estado true indica que el ingrediente existe, false no existe
	 */
	public Ingrediente(Long id, String nombre, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	/**
	 * Metodo para obtener el id
	 * @return id del ingrediente
	 */

	public Long getId() {
		return id;
	}

	/**
	 * Metodo para cargar el id del ingrediente
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * MEtodo para obtern el nombre del ingrediente
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para guardar el nombre del ingrediente
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para conocer el estado del ingrediente
	 * @return estado
	 */

	public boolean isEstado() {
		return estado;
	}


	/**
	 * Metodo para guardar el estado del ingrediente
	 * @param estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}