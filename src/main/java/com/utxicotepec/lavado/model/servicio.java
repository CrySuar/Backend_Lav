package com.utxicotepec.lavado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   /*corresponde a una entidad dee la base de datos*/

@Table(name="servicio")  /*corresponde a una tabla de la base de datos*/

public class servicio {
	
	  @Id    /*indica que es una tipo de id*/
	  @GeneratedValue(strategy = GenerationType.AUTO)   /*Generacion id tipo automatico*/
public Long idservicio; 
      @Column(name="costo")
public Double costo;
      @Column(name="descripcion")
public String descripcion;
      @Column(name="fecha_registro")
public String fecha_registro;
      @Column(name="status")
public Boolean status;
      
      
	public Long getIdservicio() {
		return idservicio;
	}
	/*public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}*/
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public servicio(Long idservicio, Double costo, String descripcion, String fecha_registro, Boolean status) {		
		this.idservicio = idservicio;
		this.costo = costo;
		this.descripcion = descripcion;
		this.fecha_registro = fecha_registro;
		this.status = status;
	}
	public servicio() {
		
	}
      
      
      
}
