package com.tienda.repositorios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
	List<Pedido> findByFecha(Date fecha);
	List<Pedido> findByIdCliente(Long idCliente);
	List<Pedido> findByFechaBetweenAndIdCliente(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
	
}
