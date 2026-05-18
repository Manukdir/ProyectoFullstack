package com.example.ms_inventario.service;

import com.example.ms_inventario.client.ProductoClient;
import com.example.ms_inventario.dto.request.MovimientoStockRequestDTO;
import com.example.ms_inventario.dto.response.MovimientoStockResponseDTO;
import com.example.ms_inventario.exception.ResourceNotFoundException;
import com.example.ms_inventario.mapper.MovimientoStockMapper;
import com.example.ms_inventario.model.Inventario;
import com.example.ms_inventario.model.MovimientoStock;
import com.example.ms_inventario.repository.InventarioRepository;
import com.example.ms_inventario.repository.MovimientoStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoStockService {

    private final MovimientoStockRepository movimientoStockRepository;
    private final InventarioRepository inventarioRepository;
    private final MovimientoStockMapper movimientoStockMapper;
    private final ProductoClient productoClient;

    public List<MovimientoStockResponseDTO> listarTodos() {
        log.info("Listando todos los movimientos de stock");
        return movimientoStockRepository.findAll().stream()
                .map(movimientoStockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MovimientoStockResponseDTO buscarPorId(Integer id) {
        log.info("Buscando movimiento con ID: {}", id);
        MovimientoStock movimiento = movimientoStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));
        return movimientoStockMapper.toResponseDTO(movimiento);
    }

    public MovimientoStockResponseDTO guardar(MovimientoStockRequestDTO dto) {
        try {
            log.info("Validando existencia de producto en ms-productos");
            productoClient.buscarPorId(dto.getProductoId());

            log.info("Guardando nuevo movimiento");
            Inventario inventario = inventarioRepository.findById(dto.getInventarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + dto.getInventarioId()));

            MovimientoStock movimiento = movimientoStockMapper.toEntity(dto);
            movimiento.setInventario(inventario);
            return movimientoStockMapper.toResponseDTO(movimientoStockRepository.save(movimiento));
        } catch (ResourceNotFoundException e) {
            log.error("Error de validacion al guardar movimiento: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error al comunicarse con ms-productos o guardar: {}", e.getMessage());
            throw new ResourceNotFoundException("Producto no encontrado o error en el servidor externo");
        }
    }

    public MovimientoStockResponseDTO actualizar(Integer id, MovimientoStockRequestDTO dto) {
        log.info("Actualizando movimiento con ID: {}", id);
        MovimientoStock movimiento = movimientoStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));

        Inventario inventario = inventarioRepository.findById(dto.getInventarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + dto.getInventarioId()));

        try {
            productoClient.buscarPorId(dto.getProductoId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Producto no encontrado en ms-productos con ID: " + dto.getProductoId());
        }

        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setMotivo(dto.getMotivo());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setAprobado(dto.isAprobado());
        movimiento.setFechaMovimiento(dto.getFechaMovimiento());
        movimiento.setProductoId(dto.getProductoId());
        movimiento.setInventario(inventario);

        return movimientoStockMapper.toResponseDTO(movimientoStockRepository.save(movimiento));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando movimiento con ID: {}", id);
        MovimientoStock movimiento = movimientoStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));
        movimientoStockRepository.delete(movimiento);
    }

    public List<MovimientoStockResponseDTO> buscarPorTipoYAprobado(String tipoMovimiento) {
        log.info("Buscando movimientos aprobados de tipo: {}", tipoMovimiento);
        return movimientoStockRepository.findByTipoMovimientoAndAprobadoTrue(tipoMovimiento).stream()
                .map(movimientoStockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}