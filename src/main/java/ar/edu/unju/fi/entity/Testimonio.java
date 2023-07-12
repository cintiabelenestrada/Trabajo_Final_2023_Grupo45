package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Component
@Entity
@Table(name = "testimonios")
public class Testimonio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tes_id")
	private Long id;

	@Column(name = "tes_fecha")
	private LocalDate fecha;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	@NotBlank(message = "El comentario no puede estar vacío")
	@Size(min = 30, max = 5000, message = "Debe escribir un texto con entre 30 y 5000 caracteres")
	@Column(name = "tes_comentario", columnDefinition = "LONGTEXT")
	private String comentario;
	

	@Column(name ="tes_estado")
	private boolean estado;
	
	public Testimonio() {
		super();
	}

	/**
	 * 
	 * @param fecha			fecha perteneciente al dia del comentario
	 * @param usuario		representa a un usuario
	 * @param comentario	comentario escrito por el usuario
	 */
	public Testimonio(LocalDate fecha, Usuario usuario, String comentario, boolean estado) {
        super();
        this.fecha = fecha;
        this.usuario = usuario;
        this.comentario = comentario;
        this.estado = estado;
    }

	// Métodos accesores (getters y setters)
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
