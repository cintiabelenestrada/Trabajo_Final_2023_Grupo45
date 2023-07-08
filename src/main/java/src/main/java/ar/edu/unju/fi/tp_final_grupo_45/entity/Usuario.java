package src.main.java.ar.edu.unju.fi.tp_final_grupo_45.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
//import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * Representa un usurario en el sistema.
 * @author Federico Nicolas Burgos
 * @Version 1.0.1 date= 27/06/2023
 */
@Component
@Entity
@Table(name="usuarios")
public class Usuario {
	/**
     * El nombre del producto.
     *  Nombre*
 Apellido*
 E-mail
 Fecha nacimiento
 Teléfono
 Sexo
 Estatura
     */
	
	// Validación del campo nombre
	@NotEmpty(message="el nombre no puede estar vacio.")
	@Size(min=5, max=100,message="El nombre del producto no puede ser inferior a 50 caracteres y mayor a 100.")
    @Column(name="user_nombre", length=20, nullable=false)
	private String nombre;
	//private int codigo;
	@NotEmpty(message="el apellido no puede estar vacio.")
	@Size(min=5, max=100,message="El apellido no puede ser inferior a 4 caracteres y mayor a 100.")
    @Column(name="user_apellido", length=20, nullable=false)
	private String apellido;
	@Email(message="Debe ingresar un email con formato valido")
	@NotEmpty(message="el email no puede estar vacio.") 
	@Column(name="user_email", length=25, nullable=false)
	private String email;
	@NotNull(message = "La fecha no puede estar vacío.")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@Column(name = "user_fechanacimiento")
	private LocalDate fecha_nacimiento;//agregar sus getters y setter
	@NotEmpty(message="el telefono no puede estar vacio.")//buscar la forma de que alerte de formato invalido
	@Size(min=8, max=16,message="El telefono no puede ser inferior a 8 caracteres y mayor a 16.")
    @Column(name="user_telefono", length=16, nullable=false)
	private String telefono;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="produ_id")
    private Long id;
    
    
    // Validación del campo estatura
	@Positive(message="El estatura debe ser un valor positivo y no puede ser cero.")
	//@NotEmpty(message="el nombre no puede estar vacio.")
    @Column(name="user_estatura")
	private double estatura;
	
	// Validación del campo categoría
    @NotBlank(message="Debe seleccion un sexo.")
    @Column(name="user_sexo", length=9, nullable=false)
    /**
     * La categoría del producto.
     */
    private String sexo;
    
   
    
    @Column(name="produ_estado")
    /**
     * El estado del producto. Esta variable es la que definira si se el producto sera visible o no en la
     * interfaz web.
     */
    private boolean estado;
    
    //@NotBlank(message="el estado no puede esta vacio")
    @Column(name="usuario_admin")
    private Boolean userAdmin;
    
    /**
     * La categoría a la cual pertenece el producto. Esta es una clase en la cual se intento con el material
     * brindado que tenga una relacion manytoone con producto.
     */
  //  private Categoria categori;
 //   @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	//private List<Categoria> catDisponible;
    
    /**
     * Constructor parametrizado.
     *
     * @param nombre       el nombre del producto
     * @param codigo       el código del producto
     * @param id           el ID del producto
     * @param precio       el precio del producto
     * @param categoria    la categoría del producto
     * @param descuento    el descuento del producto
     * @param nombreImagen el nombre de la imagen del producto
     * @param estado       el estado del producto
     */
   

    public Usuario() {
    	super();
	}

	

	 public Usuario(String nombre, String apellido, String email, LocalDate fechaArticulo, String telefono,
Long id,  double estatura,String sexo, boolean estado,Boolean userAdmin) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fecha_nacimiento = fecha_nacimiento;
		this.telefono = telefono;
		this.id = id;
		this.estatura = estatura;
		this.sexo = sexo;
		this.estado = estado;
		this.userAdmin = userAdmin;
	}



	/**
     * Obtiene el ID del producto.
     *
     * @return el ID del producto
     */
	public Long getId() {
		return id;
	}
	/**
     * Establece el ID del producto.
     *
     * @param id el ID del producto
     */
	public void setId(Long id) {
		this.id = id;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// Métodos accesores (getters y setters)
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getProdu_id() {
		return id;
	}
	
	public void setProdu_id(long id) {
		this.id = id;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public double getEstatura() {
		return estatura;
	}



	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}



	public String getSexo() {
		return sexo;
	}



	public void setSexo(String sexo) {
		this.sexo = sexo;
	}



	public Boolean isUserAdmin() {
		return userAdmin;
	}



	public void setUserAdmin(Boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	
	
	
	/**
     * Obtiene el precio del producto.
     *
     * @return el precio del producto
     */
	

}


