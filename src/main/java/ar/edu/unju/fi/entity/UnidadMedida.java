package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "unidadmedida")
public class UnidadMedida {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidadmedida")
	private Long id;

    @NotEmpty()
	@Size(min=2, max=15)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*")
	@Column(name = "nombre")
	private String nombre;

    @Column(name = "estado")
	private boolean estado;

    //Constructores
	public UnidadMedida() {
	}
    public UnidadMedida(Long id,
            @NotEmpty @Size(min = 2, max = 15) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String nombre,
            boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    // getters and setters
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
