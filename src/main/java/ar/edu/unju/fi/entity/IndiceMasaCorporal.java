package ar.edu.unju.fi.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;

/**
 * Hace referencia al calculo de IMC para un usuario determinado
 * @author Jonathan R. Mascareño
 * @version 1.0 3/7/2023
 * */



@Component
@Entity
@Table(name = "IndiceMasaCorporal")
public class IndiceMasaCorporal {
	
	@Id
	@Column(name = "imc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	  @Column(name = "imc_fecha")
	    private LocalDate fechaImc;
	  
	  @NotNull(message="El peso no puede quedar vacio")
	  @Positive(message = "El peso debe ser mayor a 0")
	  @Column(name = "imc_peso")
	  private Integer pesoActual;
	  
	  private double imcActual;
	
	@Column(name ="imc_estado")
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@Valid
	private Usuario usuario;
	
	
	
public IndiceMasaCorporal() {
		super();
	}


/**
 * Constructor parametrizado
 * @param id es el numero de id del resultado del calculo de IMC para una fecha determinada
 * @param fechaImc es una fecha determinada en que se quiere solicitar el calculo
 * @param estado es
 * 
 * */



public IndiceMasaCorporal(Long id, LocalDate fechaImc, Integer pesoActual,boolean estado, double imcActual,Usuario usuario) {
		super();
		this.id = id;
		this.fechaImc = fechaImc;
		this.estado = estado;
		this.pesoActual = pesoActual;
		this.imcActual = imcActual;
		this.usuario = usuario;
	}

//public void calcularIMC(float alt,float pes) {
	
	/**
	 * Metodo que calcula el indice de masa corporal, utilizando el peso y la altura
	 *
	 *@param imc es el indice de masa corporal de una persona
	 *@param pes es el peso de una persona
	 *@param alt es la altura de una persona
	 *
	 **/
	
//    float imc =  (pes / (alt* alt));
//    
//    
//    System.out.println("\nTu altura es: "+alt);
//    System.out.println("\nTu peso peso es: "+pes);
//    if (imc < 18.5) {
//    	System.out.println("\nSu IMC es:" + imc + ".Estás debajo de tu peso ideal.");
//    } else if (imc > 25) {
//    	System.out.println("\nSu IMC es:" + imc + ".Puede tener sobrepeso.");
//    } else {
//    	
//        System.out.println("\nSu IMC es:" + imc + ".Estás en tu peso ideal.");
//    }
//}
//





/**
 * Getters & Setters
 * */
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public double getImcActual() {
		return imcActual;
	}


	public void setImcActual(double imcActual) {
		this.imcActual = imcActual;
	}


	public LocalDate getFechaImc() {
		return fechaImc;
	}


	public void setFechaImc(LocalDate fechaImc) {
		this.fechaImc = fechaImc;
	}


	public Integer getPesoActual() {
		return pesoActual;
	}


	public void setPesoActual(Integer pesoActual) {
		this.pesoActual = pesoActual;
	}


	
	

}
