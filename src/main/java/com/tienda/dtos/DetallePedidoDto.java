package com.tienda.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tienda.entidades.DetallePedido;

import lombok.Data;
@Data
public class DetallePedidoDto {

	Long idCliente;
	Long idTienda;
	Long idProducto;
	BigDecimal cantidad;
}
