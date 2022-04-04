package com.utxicotepec.lavado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.vehiculo;



public interface vehiculoRepository extends JpaRepository< vehiculo, Long>{

	/*variable de tipo lista donde se guardan los datos en forma temporal*/
	List <vehiculo> findByMatricula(String matricula);
}
