package com.tienda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.Tienda;
import java.util.List;


@Repository
public interface TiendaRepositorio extends JpaRepository<Tienda, Long> {
	boolean existsByNombre(String nombre);
	List<Tienda> findByNombre(String nombre);
	List<Tienda> findByIdTienda(Long idTienda);

	@Modifying
	@Query("UPDATE Tienda t SET t.nombre = :nombre " + " WHERE t.idTienda = :idTienda")
	int actualizar(String nombre, Long idTienda);

}
