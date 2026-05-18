package com.example.ms_sucursales.service;

import com.example.ms_sucursales.dto.SucursalDTO;
import com.example.ms_sucursales.dto.SucursalRequestDTO;
import com.example.ms_sucursales.mapper.SucursalMapper;
import com.example.ms_sucursales.model.Sucursal;
import com.example.ms_sucursales.repository.SucursalRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    private static final Logger log = LoggerFactory.getLogger(SucursalService.class);

        @Autowired
        private SucursalRepository sucursalRepository;
    @Autowired
        private SucursalMapper sucursalMapper;

    public List<SucursalDTO> listar() {
        log.info("Listando sucursals");
        return sucursalRepository.findAll().stream().map(sucursalMapper::toDTO).collect(Collectors.toList());
    }

    public SucursalDTO obtenerPorId(Integer id) {
        log.info("Buscando sucursal con id {}", id);
        Optional<Sucursal> optional = sucursalRepository.findById(id);
        return optional.map(sucursalMapper::toDTO).orElse(null);
    }

    public SucursalDTO crear(SucursalRequestDTO dto) {
        try {
            

            Sucursal sucursal = sucursalMapper.toEntity(dto);
            Sucursal guardado = sucursalRepository.save(sucursal);
            log.info("Sucursal creado con id {}", guardado.getId());
            return sucursalMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear sucursal", e);
            return null;
        }
    }

    public SucursalDTO actualizar(Integer id, SucursalRequestDTO dto) {
        try {
            Optional<Sucursal> optional = sucursalRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Sucursal sucursal = optional.get();
            sucursalMapper.updateEntity(sucursal, dto);
            
            Sucursal guardado = sucursalRepository.save(sucursal);
            log.info("Sucursal actualizado con id {}", guardado.getId());
            return sucursalMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar sucursal", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!sucursalRepository.existsById(id)) {
            return false;
        }
        sucursalRepository.deleteById(id);
        log.info("Sucursal eliminado con id {}", id);
        return true;
    }

    public List<SucursalDTO> buscarPorNombreRegion(String nombreRegion) {
        log.info("Ejecutando consulta personalizada de sucursals");
        return sucursalRepository.buscarPorNombreRegion(nombreRegion).stream().map(sucursalMapper::toDTO).collect(Collectors.toList());
    }


}
