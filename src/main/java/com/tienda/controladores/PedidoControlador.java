package com.tienda.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.dtos.DetallePedidoDto;
import com.tienda.dtos.DetallePedidoResultDto;
import com.tienda.entidades.Pedido;
import com.tienda.servicios.PedidoServicio;
import com.tienda.servicios.ProductoServicio;
import com.tienda.utilitario.RespuestaTo;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("Pedido")
@RestController
@Slf4j
public class PedidoControlador {

	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private ProductoServicio servicio;
	@Autowired
	private PedidoServicio pedidoServicio;

	@ResponseBody
	@PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<Pedido>> crear(@RequestBody @Valid List<DetallePedidoDto> listaDetallePedidoDto) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, pedidoServicio.crear(listaDetallePedidoDto)),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@ResponseBody
	@PostMapping(path = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<String>> eliminar(@RequestBody @Valid Long idPedido) {
		try {
			pedidoServicio.eliminar(idPedido);
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, "Eliminación exitosa"), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@GetMapping("/listarPedidosPorFechasYIdCliente")
	public ResponseEntity<RespuestaTo<Iterable<DetallePedidoResultDto>>> listarPedidosPorFechasYIdCliente(
			LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, pedidoServicio
					.listarPedidosPorFechasYIdCliente(fechaInicio.minusDays(1), fechaFin.plusDays(1), idCliente)),
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
