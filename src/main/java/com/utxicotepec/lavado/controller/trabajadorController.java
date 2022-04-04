package com.utxicotepec.lavado.controller;

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
import com.utxicotepec.lavado.model.trabajador;
import com.utxicotepec.lavado.repository.trabajadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController                /*controlador que atiende una peticion*/
@RequestMapping("/api")             /*Mapeo patra el controlqador */

public class trabajadorController {

	@Autowired                /*permite usar el repositorio para buscar la informacion de la tabla cliente*/
	trabajadorRepository repTrabajadores;            /*mandamos a traer el repostorio y creamos una variable*/
	
	@GetMapping("/trabajadores")  /*ruta para que los agregue dentro de clientes*/
	
	public ResponseEntity<List<trabajador>> getAllTrabajadores (@RequestParam(required =false)String nombre){/*funcion publica que almacena en la lista un conjunto de clientes */
    
		try {              /*metodo para cachar errores*/ 
     List<trabajador> trabajadorr =new ArrayList<trabajador>();     /*Lista con variable de tipo cliente con un arreglo*/
	
     if(nombre==null)	 /*si el nombre es nulo*/
    	 repTrabajadores.findAll().forEach(trabajadorr::add);  /*busca en el repositorio todos los datos y los guarda en la lista antes creada clientee*/
	 else
		 repTrabajadores.findByNombre(nombre).forEach(trabajadorr::add); /*si no se cumple el if se cumple la segunda con el nombre*/
		
     if (trabajadorr.isEmpty()) {   /*si el cliente encuentra algo que es correcto*/
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  /*retorna un metodo de tipo rsponse de no content*/
		} 
     
     return new ResponseEntity<>(trabajadorr, HttpStatus.OK);  /*Retorna el valor ya sea llenbo o vacio*/
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);/*en caso de eeror nos retorna un mensaje*/
	} 
     
	}
	
	
	
	/*CREAMOS UN NUEVO MAPING CON UNA FUNCION RESPONSEENTITY CON LA VARIABLE ID PARA ALMACENAR LA RUTA CON EL ID DEL CLIENTEY LA VARIABLE DEL TIPO 
	 * OPCIONAL LOS ALMACENA EN EL CLIENTE DATA POR EL ID Y RETORNA POR MDEIO DE PETICIONES HTTP*/
	@GetMapping("/trabajadores/{idtrabajador}")
	public ResponseEntity<trabajador> gettrabajadorById (@PathVariable("idtrabajador") Long idtrabajador){
	Optional<trabajador> trabajadorData =repTrabajadores.findById(idtrabajador);
	if (trabajadorData.isPresent()) {
		return new ResponseEntity<>(trabajadorData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	/*PETCIONES POST */
	/*HACEMOS LAS PETIIONES POST CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL REGISTRO DE UN CLIENTE Y SE 
	 * PRUEBA EN POSTMAN  */
	
	@PostMapping("/trabajadores")
	 public ResponseEntity<trabajador> createtrabajador(@RequestBody trabajador trabajadorrr){
		 try {
			 trabajador  _trabajadorrr= repTrabajadores  /*CREAMOS UN CLIENTE Y CON EL TRY ES PARA CACHAR ERRORES*/
					 .save(new trabajador(  /*ALMACENAMOS EL LA VARIABLE REPCLIENTES CON LOS DATOS DE NUESTROS CLIENTES Y AHI LOS GUARDA*/
							 trabajadorrr.getIdtrabajador(),
							 trabajadorrr.getNombre(),
							 trabajadorrr.getAmaterno(),  /*ESTOS CAMPOS DEBEN CONTENER LOS DATOS QUE TENEMOS EN NUESTRO MODELO */
							 trabajadorrr.getApaterno(),
							 trabajadorrr.getDireccion(),
							 trabajadorrr.getTelefono(),
							 trabajadorrr.getCorreo(),
							 trabajadorrr.getFecha_registro(),
							 trabajadorrr.getStatus()
							 ));
			 return new ResponseEntity<>( _trabajadorrr, HttpStatus.CREATED); /*RETORNAMOS UN HTTSTATUS DE TIPO CREATED*/
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	     /*PETCIONES DELETE */
		 /*HACEMOS LAS PETIIONES DELETE CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL BORRADO DE UN CLIENTE Y SE 
		 * PRUEBA EN POSTMAN  */
	 @DeleteMapping("/trabajadores/{idtrabajador}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idtrabajador")Long idtrabajador){ /*ELIMINA POR ID*/
		 try {
			 repTrabajadores.deleteById(idtrabajador); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
}
