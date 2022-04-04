package com.utxicotepec.lavado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.servicio;
import java.util.List;

/*Importa nuestro id de la clase servicio*/
public interface servicioRepository extends JpaRepository< servicio, Long>{

	/*variable de tipo lista donde se guardan los datos en forma temporal*/
	List <servicio> findByDescripcion(String descripcion);
	
	
}
