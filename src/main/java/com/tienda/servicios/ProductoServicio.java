package com.tienda.servicios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.dtos.ProductoDto;
import com.tienda.dtos.TiendaProductosDto;
import com.tienda.entidades.Producto;
import com.tienda.entidades.Tienda;
import com.tienda.entidades.TiendaProducto;
import com.tienda.repositorios.ProductoRepositorio;
import com.tienda.repositorios.TiendaProductoRepositorio;
import com.tienda.repositorios.TiendaRepositorio;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepositorio repositorio;
	@Autowired
	private TiendaRepositorio repositorioTienda;
	@Autowired
	private TiendaProductoRepositorio tiendaProductoRepositorio;

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public Producto crear(ProductoDto productoDto) throws Exception {
		try {
			List<Tienda> tienda = repositorioTienda.findByNombre(productoDto.getNombreTienda());
			if (tienda.isEmpty()) {
				throw new Exception("La tienda " + productoDto.getNombreTienda() + " no existe");
			} else {
				Producto producto = new Producto();
				if (!repositorio.existsByNombre(productoDto.getNombreProducto())) {
					producto.setNombre(productoDto.getNombreProducto());
					repositorio.save(producto);
				} else {
					producto = repositorio.findByNombre(productoDto.getNombreProducto()).get(0);
				}
				List<TiendaProducto> tiendasProducto = tiendaProductoRepositorio.findByIdProductoAndIdTienda(producto.getIdProducto(), tienda.get(0).getIdTienda());
				if (tiendasProducto.isEmpty()) {
					TiendaProducto tiendaProducto = new TiendaProducto();
					tiendaProducto.setIdProducto(producto.getIdProducto());
					tiendaProducto.setIdTienda(tienda.get(0).getIdTienda());
					tiendaProducto.setStock(productoDto.getStock());
					tiendaProductoRepositorio.save(tiendaProducto);
				} else {
					tiendaProductoRepositorio.actualizarStock(tienda.get(0).getIdTienda(), producto.getIdProducto(), productoDto.getStock());
				}

				return producto;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void actualizar(Long idProducto, String nombre) throws Exception {
		try {
			repositorio.actualizar(nombre, idProducto);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Producto> listarTodos() throws Exception {
		return repositorio.findAll();
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, List<TiendaProductosDto>> listarProductosPorTienda() {
		Map<String, List<TiendaProductosDto>> mProducto = new HashMap<>();
		tiendaProductoRepositorio.listarProductosPorTienda().stream()
				.forEach(p -> {
					String nombreTienda  = p.get("nombre_tienda", String.class);
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
		tiendaProductoRepositorio.listarStockProductosPorTienda(idTienda).stream()
				.forEach(p -> {
					String nombreTienda  = p.get("nombre_tienda", String.class);
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
