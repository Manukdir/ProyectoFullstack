package com.example.ms_inventario.service;

import com.example.ms_inventario.dto.MovimientoStockDTO;
import com.example.ms_inventario.dto.MovimientoStockRequestDTO;
import com.example.ms_inventario.mapper.MovimientoStockMapper;
import com.example.ms_inventario.model.Inventario;
import com.example.ms_inventario.model.MovimientoStock;
import com.example.ms_inventario.repository.InventarioRepository;
import com.example.ms_inventario.repository.MovimientoStockRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoStockService {

    private static final Logger log = LoggerFactory.getLogger(MovimientoStockService.class);

        @Autowired
        private MovimientoStockRepository movimientoStockRepository;
    @Autowired
        private MovimientoStockMapper movimientoStockMapper;
    @Autowired
        private InventarioRepository inventarioRepository;

    public List<MovimientoStockDTO> listar() {
        log.info("Listando movimientoStocks");
        return movimientoStockRepository.findAll().stream().map(movimientoStockMapper::toDTO).collect(Collectors.toList());
    }

    public MovimientoStockDTO obtenerPorId(Integer id) {
        log.info("Buscando movimientoStock con id {}", id);
        Optional<MovimientoStock> optional = movimientoStockRepository.findById(id);
        return optional.map(movimientoStockMapper::toDTO).orElse(null);
    }

    public MovimientoStockDTO crear(MovimientoStockRequestDTO dto) {
        try {
                        Inventario inventario = inventarioRepository.findById(dto.getInventarioId()).orElse(null);
                        if (inventario == null) {
                            return null;
                        }

            MovimientoStock movimientoStock = movimientoStockMapper.toEntity(dto, inventario);
            MovimientoStock guardado = movimientoStockRepository.save(movimientoStock);
            log.info("MovimientoStock creado con id {}", guardado.getId());
            return movimientoStockMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear movimientoStock", e);
            return null;
        }
    }

    public MovimientoStockDTO actualizar(Integer id, MovimientoStockRequestDTO dto) {
        try {
            Optional<MovimientoStock> optional = movimientoStockRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
                        Inventario inventario = inventarioRepository.findById(dto.getInventarioId()).orElse(null);
                        if (inventario == null) {
                            return null;
                        }
            MovimientoStock movimientoStock = optional.get();
            movimientoStockMapper.updateEntity(movimientoStock, dto);
            movimientoStock.setInventario(inventario);
            MovimientoStock guardado = movimientoStockRepository.save(movimientoStock);
            log.info("MovimientoStock actualizado con id {}", guardado.getId());
            return movimientoStockMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar movimientoStock", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!movimientoStockRepository.existsById(id)) {
            return false;
        }
        movimientoStockRepository.deleteById(id);
        log.info("MovimientoStock eliminado con id {}", id);
        return true;
    }

}
