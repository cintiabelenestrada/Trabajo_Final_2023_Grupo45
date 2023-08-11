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

@Component
@Entity
@Table(name = "ingredientereceta")
public class IngredienteReceta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "rec_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingre_id")
    private Ingrediente ingrediente;
    
    @ManyToOne
    @JoinColumn(name = "id_unidadmedida")
    private UnidadMedida unidadMedida;

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "estado")
	private boolean estado;

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public IngredienteReceta(boolean estado) {
        this.estado = estado;
    }
    //constructores	
	public IngredienteReceta() {
	}
    public IngredienteReceta(Long id, Receta receta, Ingrediente ingrediente, UnidadMedida unidadMedida, String cantidad,boolean estado) {
        this.id = id;
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }
    public void setReceta(Receta receta) {
        this.receta = receta;
    }
    
    public Ingrediente getIngrediente() {
        return ingrediente;
    }
    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
