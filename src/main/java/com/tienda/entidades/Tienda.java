package com.tienda.entidades;

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
@Table(name = "tienda")
public class Tienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_tienda")
	private Long idTienda;

	private String nombre;
	
}
