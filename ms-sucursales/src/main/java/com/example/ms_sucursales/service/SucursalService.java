package com.example.ms_sucursales.service;

import com.example.ms_sucursales.dto.SucursalDTO;
import com.example.ms_sucursales.dto.SucursalRequestDTO;
import com.example.ms_sucursales.mapper.SucursalMapper;
import com.example.ms_sucursales.model.Region;
import com.example.ms_sucursales.model.Sucursal;
import com.example.ms_sucursales.repository.RegionRepository;
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
    private RegionRepository regionRepository;

    @Autowired
    private SucursalMapper sucursalMapper;

    // Busca todas las sucursales y las transforma a DTO.
    public List<SucursalDTO> listar() {
        log.info("Listando sucursales");

        return sucursalRepository.findAll()
                .stream()
                .map(sucursalMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Busca una sucursal por su ID.
    public SucursalDTO obtenerPorId(Integer id) {
        log.info("Buscando sucursal con id {}", id);

        Optional<Sucursal> optional = sucursalRepository.findById(id);

        return optional.map(sucursalMapper::toDTO).orElse(null);
    }

    // Crea una sucursal solo si la región indicada existe.
    public SucursalDTO crear(SucursalRequestDTO dto) {
        try {
            Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());

            if (regionOptional.isEmpty()) {
                log.warn("No se puede crear sucursal: región {} no existe", dto.getRegionId());
                return null;
            }

            Region region = regionOptional.get();

            Sucursal sucursal = sucursalMapper.toEntity(dto, region);
            Sucursal guardado = sucursalRepository.save(sucursal);

            log.info("Sucursal creada con id {}", guardado.getId());

            return sucursalMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al crear sucursal", e);
            return null;
        }
    }

    // Actualiza una sucursal existente y valida que la nueva región exista.
    public SucursalDTO actualizar(Integer id, SucursalRequestDTO dto) {
        try {
            Optional<Sucursal> sucursalOptional = sucursalRepository.findById(id);

            if (sucursalOptional.isEmpty()) {
                return null;
            }

            Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());

            if (regionOptional.isEmpty()) {
                log.warn("No se puede actualizar sucursal: región {} no existe", dto.getRegionId());
                return null;
            }

            Sucursal sucursal = sucursalOptional.get();
            Region region = regionOptional.get();

            sucursalMapper.updateEntity(sucursal, dto, region);

            Sucursal guardado = sucursalRepository.save(sucursal);

            log.info("Sucursal actualizada con id {}", guardado.getId());

            return sucursalMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al actualizar sucursal", e);
            return null;
        }
    }

    // Elimina una sucursal si existe.
    public boolean eliminar(Integer id) {
        if (!sucursalRepository.existsById(id)) {
            return false;
        }

        sucursalRepository.deleteById(id);

        log.info("Sucursal eliminada con id {}", id);

        return true;
    }

    // Ejecuta la consulta nativa que filtra sucursales por nombre de región.
    public List<SucursalDTO> buscarPorNombreRegion(String nombreRegion) {
        log.info("Buscando sucursales de la región {}", nombreRegion);

        return sucursalRepository.buscarPorNombreRegion(nombreRegion)
                .stream()
                .map(sucursalMapper::toDTO)
                .collect(Collectors.toList());
    }
}
