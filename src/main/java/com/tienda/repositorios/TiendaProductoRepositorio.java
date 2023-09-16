package com.tienda.repositorios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tienda.entidades.TiendaProducto;

import jakarta.persistence.Tuple;

@Repository
public interface TiendaProductoRepositorio extends JpaRepository<TiendaProducto, Long> {
	boolean existsByIdProductoAndIdTienda(Long idProducto, Long idTienda);

	List<TiendaProducto> findByIdProductoAndIdTienda(Long idProducto, Long idTienda);

	@Modifying
	@Query("UPDATE TiendaProducto t SET t.stock = t.stock + :stock " + " WHERE t.idTienda = :idTienda"
			+ " AND t.idProducto = :idProducto")
	int actualizarStock(Long idTienda, Long idProducto, BigDecimal stock);
	
	@Modifying
	@Query("UPDATE TiendaProducto t SET t.stock = t.stock + :stock " + " WHERE t.idTiendaProducto = :idTiendaProducto")
	int actualizarStockTiendaProducto(Long idTiendaProducto, BigDecimal stock);
	
	public static final String LISTAR_PRODUCTOS_POR_TIENDA = """
			select t.nombre nombre_tienda, p.nombre nombre_producto, tp.stock stock
			from tienda t inner join tienda_producto tp on t.id_tienda = tp.id_tienda 
			inner join producto p on p.id_producto = tp.id_producto 
			""";
	
	@Query(nativeQuery = true, value = LISTAR_PRODUCTOS_POR_TIENDA)
	List<Tuple> listarProductosPorTienda();
	
	public static final String LISTAR_PRODUCTOS_STOCK_POR_TIENDA = """
			select t.nombre nombre_tienda, p.nombre nombre_producto, tp.stock stock
			from tienda t inner join tienda_producto tp on t.id_tienda = tp.id_tienda 
			inner join producto p on p.id_producto = tp.id_producto 
			where t.id_tienda =:idTienda
			""";
	
	@Query(nativeQuery = true, value = LISTAR_PRODUCTOS_STOCK_POR_TIENDA)
	List<Tuple> listarStockProductosPorTienda(@Param("idTienda") Long idTienda);

}
