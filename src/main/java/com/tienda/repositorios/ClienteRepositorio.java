package com.tienda.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNombres(String nombres);
	List<Cliente> findByIdentificacion(String identificacion);
	List<Cliente> findByIdCliente(Long idCliente);
	boolean existsByIdentificacion(String identificacion);

	@Modifying
	@Query("UPDATE Cliente c SET c.nombres = :nombres, c.identificacion = :identificacion "
			+ " WHERE c.idCliente = :idCliente")
	int actualizar(Long idCliente, String nombres,  String identificacion);
	
}
