package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.entidades.Cliente;
import com.tienda.entidades.Tienda;
import com.tienda.repositorios.ClienteRepositorio;

import jakarta.persistence.EntityExistsException;

@Service
public class ClienteServicio {
	@Autowired
	private ClienteRepositorio repositorio;

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public Cliente crear(Cliente cliente) throws Exception {
		try {
			if (repositorio.existsByIdentificacion(cliente.getIdentificacion())) {
				throw new EntityExistsException("Ya existe el cliente con identificación: " + cliente.getIdentificacion());
			}
			repositorio.save(cliente);

			return cliente;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void actualizar(Long idCliente, String nombre, String identificacion) throws Exception {
		try {
			repositorio.actualizar(idCliente, nombre, identificacion);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Cliente> listarTodos() throws Exception {
		return repositorio.findAll();
	}

}
