package com.tienda.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.dtos.DetallePedidoDto;
import com.tienda.dtos.DetallePedidoResultDto;
import com.tienda.dtos.TiendaProductosDto;
import com.tienda.entidades.Cliente;
import com.tienda.entidades.DetallePedido;
import com.tienda.entidades.Pedido;
import com.tienda.entidades.Tienda;
import com.tienda.entidades.TiendaProducto;
import com.tienda.repositorios.ClienteRepositorio;
import com.tienda.repositorios.DetallePedidoRepositorio;
import com.tienda.repositorios.PedidoRepositorio;
import com.tienda.repositorios.ProductoRepositorio;
import com.tienda.repositorios.TiendaProductoRepositorio;
import com.tienda.repositorios.TiendaRepositorio;

import jakarta.persistence.EntityExistsException;

@Service
public class PedidoServicio {
	@Autowired
	private ProductoRepositorio repositorio;
	@Autowired
	private TiendaRepositorio repositorioTienda;
	@Autowired
	private TiendaProductoRepositorio tiendaProductoRepositorio;
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private DetallePedidoRepositorio detallePedidoRepositorio;

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public Pedido crear(List<DetallePedidoDto> listaDetalleDto) throws Exception {
		try {
			Pedido pedido = null;
			for (DetallePedidoDto detallePedidoDto : listaDetalleDto) {
				Optional<Cliente> cliente = clienteRepositorio.findById(detallePedidoDto.getIdCliente());
				if (cliente.isEmpty()) {
					throw new EntityExistsException("No existe el cliente con id: " + detallePedidoDto.getIdCliente());
				}

				Optional<Tienda> tienda = repositorioTienda.findById(detallePedidoDto.getIdTienda());
				if (tienda.isEmpty()) {
					throw new EntityExistsException("No existe la tienda con id: " + detallePedidoDto.getIdTienda());
				} else {
					if (pedido == null) {
						pedido = new Pedido();
						pedido.setIdCliente(cliente.get().getIdCliente());
						pedido.setFecha(LocalDate.now());
						pedidoRepositorio.save(pedido);
					}
					List<TiendaProducto> productosTienda = tiendaProductoRepositorio.findByIdProductoAndIdTienda(
							detallePedidoDto.getIdProducto(), detallePedidoDto.getIdTienda());
					if (productosTienda.isEmpty()) {
						throw new EntityExistsException(
								"No existe el producto con id: " + detallePedidoDto.getIdProducto()
										+ " en la tienda con id: " + detallePedidoDto.getIdTienda());
					} else {
						if (productosTienda.get(0).getStock().compareTo(detallePedidoDto.getCantidad()) >= 0) {
							DetallePedido detallePedido = new DetallePedido();
							detallePedido.setIdPedido(pedido.getIdPedido());
							detallePedido.setIdTiendaProducto(productosTienda.get(0).getIdTiendaProducto());
							detallePedido.setCantidad(detallePedidoDto.getCantidad());
							detallePedidoRepositorio.save(detallePedido);
							tiendaProductoRepositorio.actualizarStock(detallePedidoDto.getIdTienda(),
									detallePedidoDto.getIdProducto(),
									(detallePedidoDto.getCantidad().multiply(new BigDecimal(-1))));
						} else {
							throw new EntityExistsException("La cantidad : " + detallePedidoDto.getCantidad()
									+ " sobrepasa el stock disponible del producto con id: "
									+ detallePedidoDto.getIdProducto() + " stock existente : "
									+ productosTienda.get(0).getStock());
						}
					}
				}

			}

			return pedido;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void eliminar(Long idPedido) throws Exception {
		try {
			
			Optional<Pedido> pedido = pedidoRepositorio.findById(idPedido);
			if (pedido.isEmpty()) {
				throw new EntityExistsException("No existe el pedido con id: " + idPedido);
			} else {
				for (DetallePedido detallePedido : detallePedidoRepositorio.findByIdPedido(idPedido)) {
					tiendaProductoRepositorio.actualizarStockTiendaProducto(detallePedido.getIdTiendaProducto(), detallePedido.getCantidad());
					detallePedidoRepositorio.delete(detallePedido);
				}
				pedidoRepositorio.deleteById(idPedido);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}


	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Pedido> listarTodos() throws Exception {
		return pedidoRepositorio.findAll();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<DetallePedidoResultDto> listarPedidosPorFechasYIdCliente(LocalDate fechaInicio, LocalDate fechaFin,
			Long idCliente) throws Exception {
		List<DetallePedidoResultDto> listaDetallePedidoDto = new ArrayList<>();
		for (Pedido pedido : pedidoRepositorio.findByFechaBetweenAndIdCliente(fechaInicio, fechaFin, idCliente)) {
			DetallePedidoResultDto detallePedidoDto = new DetallePedidoResultDto();
			detallePedidoDto.setIdCliente(pedido.getIdCliente());
			detallePedidoDto.setIdTienda(idCliente);
			detallePedidoDto.getListaDetallePedido()
					.addAll(detallePedidoRepositorio.findByIdPedido(pedido.getIdPedido()));
			listaDetallePedidoDto.add(detallePedidoDto);
		}
		return listaDetallePedidoDto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, List<TiendaProductosDto>> listarProductosPorTienda() {
		Map<String, List<TiendaProductosDto>> mProducto = new HashMap<>();
		tiendaProductoRepositorio.listarProductosPorTienda().stream().forEach(p -> {
			String nombreTienda = p.get("nombre_tienda", String.class);
			TiendaProductosDto tiendaProductoDto = new TiendaProductosDto();
			tiendaProductoDto.setNombreProducto(p.get("nombre_producto", String.class));
			tiendaProductoDto.setStock(p.get("stock", BigDecimal.class));
			if (mProducto.containsKey(nombreTienda)) {
				List<TiendaProductosDto> productosTienda = mProducto.get(nombreTienda);
				productosTienda.add(tiendaProductoDto);
			} else {
				mProducto.put(nombreTienda, List.of(tiendaProductoDto));
			}
		});

		return mProducto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, List<TiendaProductosDto>> listarStockProductosPorTienda(Long idTienda) {
		Map<String, List<TiendaProductosDto>> mProducto = new HashMap<>();
		tiendaProductoRepositorio.listarStockProductosPorTienda(idTienda).stream().forEach(p -> {
			String nombreTienda = p.get("nombre_tienda", String.class);
			TiendaProductosDto tiendaProductoDto = new TiendaProductosDto();
			tiendaProductoDto.setNombreProducto(p.get("nombre_producto", String.class));
			tiendaProductoDto.setStock(p.get("stock", BigDecimal.class));
			if (mProducto.containsKey(nombreTienda)) {
				List<TiendaProductosDto> productosTienda = mProducto.get(nombreTienda);
				productosTienda.add(tiendaProductoDto);
			} else {
				mProducto.put(nombreTienda, List.of(tiendaProductoDto));
			}
		});

		return mProducto;
	}
}
