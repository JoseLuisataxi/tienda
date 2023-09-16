package com.tienda.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.DetallePedido;

@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
	List<DetallePedido> findByIdPedido(Long idPedido);
	
}
