package com.utxicotepec.lavado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.trabajador;

import java.util.List;



public interface trabajadorRepository extends JpaRepository<trabajador, Long>{
	
	/*variable de tipo lista donde se guardan los datos en forma temporal*/
	List <trabajador> findByNombre(String nombre);
	
}
