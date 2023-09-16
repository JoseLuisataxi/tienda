package com.tienda.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "tienda_producto")
public class TiendaProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_tienda_producto")
	private Long idTiendaProducto;
	
	@Column (name="id_tienda")
	private Long idTienda;

	@Column (name="id_producto")
	private Long idProducto;
	
	private BigDecimal stock;
	
	@Transient
	private BigDecimal cantidad;
	
}
