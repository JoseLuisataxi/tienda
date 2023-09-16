package com.tienda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.Producto;
import java.util.List;


@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
	boolean existsByNombre(String nombre);
	List<Producto> findByNombre(String nombre);
	
	@Modifying
	@Query("UPDATE Producto p SET p.nombre = :nombre " + " WHERE p.idProducto = :idProducto")
	int actualizar(String nombre, Long idProducto);

}
