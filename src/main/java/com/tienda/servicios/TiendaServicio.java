package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.entidades.Tienda;
import com.tienda.repositorios.TiendaRepositorio;

import jakarta.persistence.EntityExistsException;

@Service
public class TiendaServicio {
	@Autowired
	private TiendaRepositorio repositorio;

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public Tienda crear(Tienda tienda) throws Exception {
		try {
			if (repositorio.existsByNombre(tienda.getNombre())) {
				throw new EntityExistsException("Ya se tiene registrado la tienda: " + tienda.getNombre());
			}
			repositorio.save(tienda);

			return tienda;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void actualizar(Long idTienda, String nombre)
			throws Exception {
		try {
			repositorio.actualizar(nombre, idTienda);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Tienda> listarTodos() throws Exception {
		return repositorio.findAll();
	}
	
}
