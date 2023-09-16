package com.tienda.controladores;

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

import com.tienda.entidades.Tienda;
import com.tienda.servicios.TiendaServicio;
import com.tienda.utilitario.RespuestaTo;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("Tienda")
@RestController
@Slf4j
public class TiendaControlador {

	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private TiendaServicio servicio;


	@ResponseBody
	@PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<Tienda>> crear(@RequestBody @Valid Tienda tienda) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.crear(tienda)),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@ResponseBody
	@PutMapping(path = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<String>> actualizar(@RequestBody @Valid Tienda tienda) {
		try {
			servicio.actualizar(tienda.getIdTienda(), tienda.getNombre());
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, "Actualización correcta"), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@GetMapping("/listarTiendas")
	public ResponseEntity<RespuestaTo<Iterable<Tienda>>> listar() {
		try {
			return new ResponseEntity<>(new RespuestaTo<>("OK", null, servicio.listarTodos()),
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
