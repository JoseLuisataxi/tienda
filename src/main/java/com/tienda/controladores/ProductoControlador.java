package com.tienda.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.dtos.ProductoDto;
import com.tienda.dtos.TiendaProductosDto;
import com.tienda.entidades.Producto;
import com.tienda.servicios.ProductoServicio;
import com.tienda.utilitario.RespuestaTo;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("Producto")
@RestController
@Slf4j
public class ProductoControlador {

	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private ProductoServicio servicio;

	@ResponseBody
	@PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<Producto>> crear(@RequestBody @Valid ProductoDto productoDto) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.crear(productoDto)), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@ResponseBody
	@PutMapping(path = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<String>> actualizar(@RequestBody @Valid Producto producto) {
		try {
			servicio.actualizar(producto.getIdProducto(), producto.getNombre());
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, "Actualización correcta"), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@GetMapping("/listarProductos")
	public ResponseEntity<RespuestaTo<Iterable<Producto>>> listar() {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.listarTodos()), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@GetMapping("/listarProductosPorTienda")
	public ResponseEntity<RespuestaTo<Map<String, List<TiendaProductosDto>>>> listarProductosPorTienda() {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.listarProductosPorTienda()),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}
	
	@GetMapping("/listarStockProductosPorTienda")
	public ResponseEntity<RespuestaTo<Map<String, List<TiendaProductosDto>>>> listarStockProductosPorTienda(Long idTienda) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.listarStockProductosPorTienda(idTienda)),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	public <X> ResponseEntity<RespuestaTo<X>> responderError(Exception ex) {
		return new ResponseEntity<>(new RespuestaTo<>("ERROR", ex.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
