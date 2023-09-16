package com.tienda.dtos;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductoDto {
	String nombreProducto;
	String nombreTienda;
	BigDecimal stock;
}
