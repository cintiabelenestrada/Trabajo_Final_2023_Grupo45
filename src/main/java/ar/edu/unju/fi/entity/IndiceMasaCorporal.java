package ar.edu.unju.fi.entity;

import java.time.LocalDate;


import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


/**
 * Hace referencia al calculo de IMC para un usuario
 * @author Jonathan R. Mascareño
 * @version 1.0 3/7/2023
 * */



@Component
@Entity
@Table(name = "indice_masa_corporal")
public class IndiceMasaCorporal {
	
	@Id
	@Column(name = "imc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "imc_fechaimc")
	private LocalDate fechaImc;
	
	
	private boolean estado;

	
	
	
	
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



public IndiceMasaCorporal(Long id, LocalDate fechaImc, boolean estado) {
		super();
		this.id = id;
		this.fechaImc = fechaImc;
		this.estado = estado;
	}

public void calcularIMC(float alt,float pes) {
	
	/**
	 * Metodo que calcula el indice de masa corporal, utilizando el peso y la altura
	 *
	 *@param imc es el indice de masa corporal de una persona
	 *@param pes es el peso de una persona
	 *@param alt es la altura de una persona
	 *
	 **/
	
    float imc =  (pes / (alt* alt));
    
    
    System.out.println("\nTu altura es: "+alt);
    System.out.println("\nTu peso peso es: "+pes);
    if (imc < 18.5) {
    	System.out.println("\nSu IMC es:" + imc + ".Estás debajo de tu peso ideal.");
    } else if (imc > 25) {
    	System.out.println("\nSu IMC es:" + imc + ".Puede tener sobrepeso.");
    } else {
    	
        System.out.println("\nSu IMC es:" + imc + ".Estás en tu peso ideal.");
    }
}






/**
 * Getters & Setters
 * */
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getFechaImc() {
		return fechaImc;
	}


	public void setFechaImc(LocalDate fechaImc) {
		this.fechaImc = fechaImc;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	

}
