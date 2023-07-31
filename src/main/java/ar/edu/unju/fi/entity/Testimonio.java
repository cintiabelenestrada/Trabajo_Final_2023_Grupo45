package ar.edu.unju.fi.entity;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Noeli Mamaní; Sara Mercado; Alicia Roberts; Cristian Ortega; Nicolás Velazco
 * 
 */
@Component
@Entity
@Table(name="testimonios")

public class Testimonio {
	
	/**Atributos de la clase Testimonio*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_testimonio")
	public Long id;
	
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="La fecha no puede ser vacia")
	@Column(name="fecha")
	public LocalDate fecha;
	
	@NotBlank
	@Column(name="comentario")
	public String comentario;
	
	@Column(name="estado")
	public boolean estado;
	
	/**
	 * Constructor pot defecto	
	 */
	
	public Testimonio() {
		super();
		
	}

	
	/**
	 * Constructor parametrizado
	 * @param id de usuario
	 * @param usuario de la pagina
	 * @param fecha en que se realizo el comentario
	 * @param comentarios
	 */

	

	public Testimonio(Long id, @NotNull(message = "Debe seleccionar un Usuario") Usuario usuario,
			@NotBlank LocalDate fecha, @NotBlank String comentario, boolean estado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		this.comentario = comentario;
		this.estado = estado;
	}
	public Testimonio(Usuario usuario) {
		this.usuario = usuario;
		
	}

	/**
	 * Metodo que recupera el atributo id
	 * @return nombre
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Metodo para guardar el atribudo id
	 * @param id
	 */

	public void setId(Long id) {
		this.id = id;
	}
	
	/*public Usuario getUsuario() {
		return usuario;
	}
	
	
	/**
	 * Metodo que recupera el atributo fecha
	 * @return nombre
	 */

	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * Metodo que recupera el atributo usuario
	 * @return nombre
	 */

	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * Metodo para guardar el atributo usuario
	 * @param usuario
	 */

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	/**
	 * Metodo para guardar el atributo fecha
	 * @param fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**
	 * Metodo que recupera el atributo comentario
	 * @return nombre
	 */

	public String getComentario() {
		return comentario;
	}

	/**
	 * Metodo para guardar el atributo comentario
	 * @param comentarios
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Metodo que recupera el atributo estado
	 * @return estado 
	 */
	public boolean isEstado() {
		return estado;
	}
	/**
	 * Metodo para guardar el atributo estado de la sucursal
	 * @param estado si es true el objeto esta diponible, false el objeto esta eliminado
	 */

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}