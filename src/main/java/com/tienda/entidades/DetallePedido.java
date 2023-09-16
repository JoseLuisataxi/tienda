package com.tienda.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_detalle_pedido")
	private Long idDetallePedido;
	
	@Column (name="id_pedido")
	private Long idPedido;
	
	@Column (name="id_tienda_producto")
	private Long idTiendaProducto;
	
	private BigDecimal cantidad;
	
}
