package com.utxicotepec.lavado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   /*corresponde a una entidad dee la base de datos*/

@Table(name="cliente")  /*corresponde a una tabla de la base de datos*/

public class cliente {
	  @Id    /*indica que es una tipo de id*/
	  @GeneratedValue(strategy = GenerationType.AUTO)   /*Generacion id tipo automatico*/
public Long idcliente; 
      @Column(name="nombre")
public String nombre;
      @Column(name="amaterno")
public String amaterno;
      @Column(name="apaterno")
public String apaterno;
      @Column(name="direccion")
public String direccion;
      @Column(name="correo")
public String correo;
      @Column(name="telefono")
public String telefono;
      @Column(name="fecha_registro")
public String fecha_registro;
      @Column(name="status")
public Boolean status;
      


public Long getIdcliente() {
	return idcliente;
}
/*public void setIdcliente(Long idcliente) {
	this.idcliente = idcliente;
}*/

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getAmaterno() {
	return amaterno;
}
public void setAmaterno(String amaterno) {
	this.amaterno = amaterno;
}
public String getApaterno() {
	return apaterno;
}
public void setApaterno(String apaterno) {
	this.apaterno = apaterno;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getFecha_registro() {
	return fecha_registro;
}
public void setFecha_registro(String fecha_registro) {
	this.fecha_registro = fecha_registro;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}

public cliente(String nombre, String amaterno, String apaterno, String direccion, String correo, String telefono,
		String fecha_registro, Boolean status) {
	this.nombre = nombre;
	this.amaterno = amaterno;
	this.apaterno = apaterno;
	this.direccion = direccion;
	this.correo = correo;
	this.telefono = telefono;
	this.fecha_registro = fecha_registro;
	this.status = status;
}

public cliente() {
	
}



}
