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
import com.utxicotepec.lavado.repository.clienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController                /*CONTROLADOR QUE ATIENDE UNA PETICION*/
@RequestMapping("/api")             /*MAPEO PARA EL CONTROLADOR*/

public class clienteController {
   
	@Autowired                /*PERMITE USAR EL REPOSRITORIO PARA BUSVCAR LA INFROMACION DE LA TABLA CLIENTE*/
	clienteRepository repClientes;            /*TRAEMOS EL REPOSITORIO Y CREAMOS UNA VARIABLE*/
	
	@GetMapping("/clientes")  /*RUTA PARA AGREGAR DENTRO DEL CLIENTE*/
	
	public ResponseEntity<List<cliente>> getAllClientes (@RequestParam(required =false)String nombre){/*funcion publica que almacena en la lista un conjunto de clientes */
    
		try {              /*METODO PARA CACHAR ERRORES*/ 
     List<cliente> clientee =new ArrayList<cliente>();     /*LISTA VARIABLE TIPO CLIENTE CON UN ARREGLO*/
     if(nombre==null)	 /*CONDICIONAL SI EL NOMBRE ES NULO*/
		 repClientes.findAll().forEach(clientee::add);  /*BUSCA EL TREPOSITORIO DE LOS DATOS Y LOS GUARDA EN LOS CLIENTES*/
	 else
	 repClientes.findByNombre(nombre).forEach(clientee::add); /*CONDICONAL SI NO SE CUMPLE EL IF*/
		
     if (clientee.isEmpty()) {   /*SI EL CLIENTE ENCUENTRA ALGO CORRECTO*/
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  /*RETORNA UN TIPO DE  RESPONSE NO CONTENT*/
		} 
     
     return new ResponseEntity<>(clientee, HttpStatus.OK);  /*RETORNA EL VALOR LLENO O VACIO*/
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);/*SI HAY ERROR MANDA MENSAJE*/
	} 
     
	}
	
	
	
	
	
	/*CREAMOS UN NUEVO MAPING CON UNA FUNCION RESPONSEENTITY CON LA VARIABLE ID PARA ALMACENAR LA RUTA CON EL ID DEL CLIENTEY LA VARIABLE DEL TIPO 
	 * OPCIONAL LOS ALMACENA EN EL CLIENTE DATA POR EL ID Y RETORNA POR MDEIO DE PETICIONES HTTP*/
	@GetMapping("/clientes/{idcliente}")
	public ResponseEntity<cliente> getclienteById (@PathVariable("idcliente") Long idcliente){
	Optional<cliente> clienteData =repClientes.findById(idcliente);
	if (clienteData.isPresent()) {
		return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	
	
	/*PETCIONES POST */
	/*HACEMOS LAS PETIIONES POST CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL REGISTRO DE UN CLIENTE Y SE 
	 * PRUEBA EN POSTMAN  */
	
	@PostMapping("/clientes")
	 public ResponseEntity<cliente> createcliente(@RequestBody cliente clientees){
		 try {
			 cliente  _clientees= repClientes  /*CREAMOS UN CLIENTE Y CON EL TRY ES PARA CACHAR ERRORES*/
					 .save(new cliente(  /*ALMACENAMOS EL LA VARIABLE REPCLIENTES CON LOS DATOS DE NUESTROS CLIENTES Y AHI LOS GUARDA*/
							 
							 clientees.getNombre(),
							 clientees.getAmaterno(),  /*ESTOS CAMPOS DEBEN CONTENER LOS DATOS QUE TENEMOS EN NUESTRO MODELO */
							 clientees.getApaterno(),
							 clientees.getDireccion(),
							 clientees.getTelefono(),
							 clientees.getCorreo(),
							 clientees.getFecha_registro(),
							 clientees.getStatus()
							 ));
			 return new ResponseEntity<>( _clientees, HttpStatus.CREATED); /*RETORNAMOS UN HTTSTATUS DE TIPO CREATED*/
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	     /*PETCIONES DELETE */
		 /*HACEMOS LAS PETIIONES DELETE CON UN MAPPING Y LA RUTA QUE ESTAAS TIENEN ESTAS ENVIAN EL BORRADO DE UN CLIENTE Y SE 
		 * PRUEBA EN POSTMAN  */
	 @DeleteMapping("/clientes/{idcliente}")
	 public ResponseEntity<HttpStatus> deletecliente(@PathVariable("idcliente")Long idcliente){ /*ELIMINA POR ID*/
		 try {
			 repClientes.deleteById(idcliente); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 
}
