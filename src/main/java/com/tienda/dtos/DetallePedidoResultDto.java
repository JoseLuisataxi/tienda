package com.tienda.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tienda.entidades.DetallePedido;

import lombok.Data;
@Data
public class DetallePedidoResultDto {

	Long idCliente;
	Long idTienda;
	List<DetallePedido> listaDetallePedido = new ArrayList<>();
}
