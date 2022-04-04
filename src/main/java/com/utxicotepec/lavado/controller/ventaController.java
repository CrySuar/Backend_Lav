package com.utxicotepec.lavado.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utxicotepec.lavado.model.cliente;
import com.utxicotepec.lavado.model.venta;
import com.utxicotepec.lavado.repository.ventaRepository;


@RestController                
@RequestMapping("/api")             

public class ventaController {

	@Autowired               
	ventaRepository repVentas;            
	
	@GetMapping("/ventas") 
	
	public ResponseEntity<List<venta>> getAllServicios (@RequestParam(required =false)String fecha){
    
		try {            
     List<venta> ventaa =new ArrayList<venta>();     
	
     if(fecha==null)	 
    	 repVentas.findAll().forEach(ventaa::add);  
	 else
		 repVentas.findByFecha(fecha).forEach(ventaa::add); 
		
     if (ventaa.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
		} 
     
     return new ResponseEntity<>(ventaa, HttpStatus.OK); 
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	}
	
	
	
	/*CREAMOS UN NUEVO MAPING CON UNA FUNCION RESPONSEENTITY CON LA VARIABLE ID PARA ALMACENAR LA RUTA CON EL ID DEL CLIENTEY LA VARIABLE DEL TIPO 
	 * OPCIONAL LOS ALMACENA EN EL CLIENTE DATA POR EL ID Y RETORNA POR MDEIO DE PETICIONES HTTP*/
	@GetMapping("/ventas/{idventa}")
	public ResponseEntity<venta> getventaById (@PathVariable("idventa") Long idventa){
	Optional<venta> ventaData =repVentas.findById(idventa);
	if (ventaData.isPresent()) {
		return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	/*PETCIONES POST */
	/*HACEMOS LAS PETIIONES POST CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL REGISTRO DE UN CLIENTE Y SE 
	 * PRUEBA EN POSTMAN  */
	
	@PostMapping("/ventas")
	 public ResponseEntity<venta> createvenyta(@RequestBody venta ventaa){
		 try {
			 venta  _ventaa= repVentas  /*CREAMOS UN CLIENTE Y CON EL TRY ES PARA CACHAR ERRORES*/
					 .save(new venta(  /*ALMACENAMOS EL LA VARIABLE REPCLIENTES CON LOS DATOS DE NUESTROS CLIENTES Y AHI LOS GUARDA*/
							 ventaa.getIdventa(),
							 ventaa.getFecha(),
							 ventaa.getIdcajero(),
							 ventaa.getIdservicio(),
							 ventaa.getIdtrabajador(),
							 ventaa.getIdvehiculo()
							 ));
			 return new ResponseEntity<>( _ventaa, HttpStatus.CREATED); /*RETORNAMOS UN HTTSTATUS DE TIPO CREATED*/
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	     /*PETCIONES DELETE */
		 /*HACEMOS LAS PETIIONES DELETE CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL BORRADO DE UN CLIENTE Y SE 
		 * PRUEBA EN POSTMAN  */
	 @DeleteMapping("/ventas/{idventa}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idventa")Long idventa){ /*ELIMINA POR ID*/
		 try {
			 repVentas.deleteById(idventa); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
	
	
}
