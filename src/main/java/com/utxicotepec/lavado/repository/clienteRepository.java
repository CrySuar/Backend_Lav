package com.utxicotepec.lavado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.cliente;

import java.util.List;



/*Importa nuestro id de la clase cliente*/
public interface clienteRepository extends JpaRepository< cliente, Long>{
	
	/*variable de tipo lista donde se guardan los datos en forma temporal*/
	List <cliente> findByNombre(String nombre);
	
}
