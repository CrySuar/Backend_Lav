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
import com.utxicotepec.lavado.model.servicio;
import com.utxicotepec.lavado.repository.servicioRepository;

@RestController                
@RequestMapping("/api")            


public class servicioController {

	@Autowired                
	servicioRepository repServicios;        
	
	@GetMapping("/servicios") 
	
	public ResponseEntity<List<servicio>> getAllServicios (@RequestParam(required =false)String descripcion){
    
		try {              
     List<servicio> servicioo =new ArrayList<servicio>();     
	
     if(descripcion==null)	 
    	 repServicios.findAll().forEach(servicioo::add);  
	 else
		 repServicios.findByDescripcion(descripcion).forEach(servicioo::add); 
		
     if (servicioo.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  
		} 
     
     return new ResponseEntity<>(servicioo, HttpStatus.OK);  
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
     
	}
	
	
	
	/*CREAMOS UN NUEVO MAPING CON UNA FUNCION RESPONSEENTITY CON LA VARIABLE ID PARA ALMACENAR LA RUTA CON EL ID DEL CLIENTEY LA VARIABLE DEL TIPO 
	 * OPCIONAL LOS ALMACENA EN EL CLIENTE DATA POR EL ID Y RETORNA POR MDEIO DE PETICIONES HTTP*/
	@GetMapping("/servicios/{idservicio}")
	public ResponseEntity<servicio> getservicioById (@PathVariable("idservicio") Long idservicio){
	Optional<servicio> servicioData =repServicios.findById(idservicio);
	if (servicioData.isPresent()) {
		return new ResponseEntity<>(servicioData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	/*PETCIONES POST */
	/*HACEMOS LAS PETIIONES POST CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL REGISTRO DE UN CLIENTE Y SE 
	 * PRUEBA EN POSTMAN  */
	
	@PostMapping("/servicios")
	 public ResponseEntity<servicio> createservicio(@RequestBody servicio servicioo){
		 try {
			 servicio  _servicioo= repServicios  /*CREAMOS UN CLIENTE Y CON EL TRY ES PARA CACHAR ERRORES*/
					 .save(new servicio(  /*ALMACENAMOS EL LA VARIABLE REPCLIENTES CON LOS DATOS DE NUESTROS CLIENTES Y AHI LOS GUARDA*/
							 servicioo.getIdservicio(),
							 servicioo.getCosto(),
							 servicioo.getDescripcion(),
							 servicioo.getFecha_registro(),
							 servicioo.getStatus()
							 ));
			 return new ResponseEntity<>( _servicioo, HttpStatus.CREATED); /*RETORNAMOS UN HTTSTATUS DE TIPO CREATED*/
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	     /*PETCIONES DELETE */
		 /*HACEMOS LAS PETIIONES DELETE CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL BORRADO DE UN CLIENTE Y SE 
		 * PRUEBA EN POSTMAN  */
	 @DeleteMapping("/servicos/{idservicio}")
	 public ResponseEntity<HttpStatus> deleteservicio(@PathVariable("idservicio")Long idservicio){ /*ELIMINA POR ID*/
		 try {
			 repServicios.deleteById(idservicio); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	 
	
}
