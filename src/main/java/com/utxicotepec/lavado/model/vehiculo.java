package com.utxicotepec.lavado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   /*corresponde a una entidad dee la base de datos*/

@Table(name="vehiculo")  /*corresponde a una tabla de la base de datos*/

public class vehiculo {

	  @Id    /*indica que es una tipo de id*/
	  @GeneratedValue(strategy = GenerationType.AUTO)   /*Generacion id tipo automatico*/
public Long idvehiculo; 
  @Column(name="color")
public String color;
  @Column(name="idcliente")
public Long idcliente;
  @Column(name="marca")
public String marca;
  @Column(name="matricula")
public String matricula;
  @Column(name="modelo")
public String modelo;
  @Column(name="tipo")
public String tipo;
  @Column(name="fecha_registro")
public String fecha_registro;
  @Column(name="status")
public Boolean status;
  
public Long getIdvehiculo() {
	return idvehiculo;
}
/*public void setIdvehiculo(Long idvehiculo) {
	this.idvehiculo = idvehiculo;
}*/
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public Long getIdcliente() {
	return idcliente;
}
public void setIdcliente(Long idcliente) {
	this.idcliente = idcliente;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public String getMatricula() {
	return matricula;
}
public void setMatricula(String matricula) {
	this.matricula = matricula;
}
public String getModelo() {
	return modelo;
}
public void setModelo(String modelo) {
	this.modelo = modelo;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
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
public vehiculo(Long idvehiculo, String color, Long idcliente, String marca, String matricula, String modelo,
		String tipo, String fecha_registro, Boolean status) {
	
	this.idvehiculo = idvehiculo;
	this.color = color;
	this.idcliente = idcliente;
	this.marca = marca;
	this.matricula = matricula;
	this.modelo = modelo;
	this.tipo = tipo;
	this.fecha_registro = fecha_registro;
	this.status = status;
}
public vehiculo() {
	
}
	
  
}
