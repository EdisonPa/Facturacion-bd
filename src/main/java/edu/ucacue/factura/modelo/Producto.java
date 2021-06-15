package edu.ucacue.factura.modelo;

import javax.persistence.Entity;
import  javax.persistence.Column;
import  javax.persistence.GeneratedValue;
import  javax.persistence.GenerationType;
import  javax.persistence.Id;
import  javax.persistence.Table;


  // ORM es mapeo objetyo relacional

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 30)
	private String nombre;
	private String descripcion;
	private String precio;
	private String stock;
		
	//constructor
	public Producto( String nombre, String descripcion, String precio, String stock) {
		super();
		//this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	
	
	//get and set
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getPrecio() {
		return precio;
	}



	public void setPrecio(String precio) {
		this.precio = precio;
	}



	public String getStock() {
		return stock;
	}



	public void setStock(String stock) {
		this.stock = stock;
	}
	
	






	/*@Override
	public String toString() {
		return "producto [id=\" + id + \", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", stock=" + stock + "]";
	}*/



	


	
	
	}
	


    

	
	
	
	
	


