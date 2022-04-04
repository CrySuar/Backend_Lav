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
import com.utxicotepec.lavado.model.vehiculo;
import com.utxicotepec.lavado.repository.vehiculoRepository;

@RestController               
@RequestMapping("/api")            


public class vehiculoController {

	@Autowired                
	vehiculoRepository repVehiculos;           
	
	@GetMapping("/vehiculos")  
	
	public ResponseEntity<List<vehiculo>> getAllServicios (@RequestParam(required =false)String descripcion){
    
		try {             
     List<vehiculo> vehiculoo =new ArrayList<vehiculo>();    
	
     if(descripcion==null)	
    	 repVehiculos.findAll().forEach(vehiculoo::add);  
	 else
		 repVehiculos.findByMatricula(descripcion).forEach(vehiculoo::add); 
		
     if (vehiculoo.isEmpty()) {  
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  
		} 
     
     return new ResponseEntity<>(vehiculoo, HttpStatus.OK); 
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	}
	
	
	
	/*CREAMOS UN NUEVO MAPING CON UNA FUNCION RESPONSEENTITY CON LA VARIABLE ID PARA ALMACENAR LA RUTA CON EL ID DEL CLIENTEY LA VARIABLE DEL TIPO 
	 * OPCIONAL LOS ALMACENA EN EL CLIENTE DATA POR EL ID Y RETORNA POR MDEIO DE PETICIONES HTTP*/
	@GetMapping("/vehiculos/{idvehiculo}")
	public ResponseEntity<vehiculo> getvehiculoById (@PathVariable("idvehiculo") Long idvehiculo){
	Optional<vehiculo> vehiculoData =repVehiculos.findById(idvehiculo);
	if (vehiculoData.isPresent()) {
		return new ResponseEntity<>(vehiculoData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	/*PETCIONES POST */
	/*HACEMOS LAS PETIIONES POST CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL REGISTRO DE UN CLIENTE Y SE 
	 * PRUEBA EN POSTMAN  */
	
	@PostMapping("/vehiculos")
	 public ResponseEntity<vehiculo> createtrabajador(@RequestBody vehiculo vehiculoo){
		 try {
			 vehiculo  _vehiculoo= repVehiculos  /*CREAMOS UN CLIENTE Y CON EL TRY ES PARA CACHAR ERRORES*/
					 .save(new vehiculo(  /*ALMACENAMOS EL LA VARIABLE REPCLIENTES CON LOS DATOS DE NUESTROS CLIENTES Y AHI LOS GUARDA*/
							 vehiculoo.getIdvehiculo(),
							 vehiculoo.getColor(),
							 vehiculoo.getIdcliente(),
							 vehiculoo.getMarca(),
							 vehiculoo.getMatricula(),
							 vehiculoo.getModelo(),
							 vehiculoo.getTipo(),
							 vehiculoo.getFecha_registro(),
							 vehiculoo.getStatus()								
							 ));
			 return new ResponseEntity<>( _vehiculoo, HttpStatus.CREATED); /*RETORNAMOS UN HTTSTATUS DE TIPO CREATED*/
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	     /*PETCIONES DELETE */
		 /*HACEMOS LAS PETIIONES DELETE CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL BORRADO DE UN CLIENTE Y SE 
		 * PRUEBA EN POSTMAN  */
	 @DeleteMapping("/vehiculos/{idvehiculo}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idvehiculo")Long idvehiculo){ /*ELIMINA POR ID*/
		 try {
			 repVehiculos.deleteById(idvehiculo); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
	
}
