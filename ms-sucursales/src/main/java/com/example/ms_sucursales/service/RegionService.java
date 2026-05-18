package com.example.ms_sucursales.service;

import com.example.ms_sucursales.dto.RegionDTO;
import com.example.ms_sucursales.dto.RegionRequestDTO;
import com.example.ms_sucursales.mapper.RegionMapper;
import com.example.ms_sucursales.model.Region;
import com.example.ms_sucursales.repository.RegionRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    private static final Logger log = LoggerFactory.getLogger(RegionService.class);

        @Autowired
        private RegionRepository regionRepository;
    @Autowired
        private RegionMapper regionMapper;

    public List<RegionDTO> listar() {
        log.info("Listando regions");
        return regionRepository.findAll().stream().map(regionMapper::toDTO).collect(Collectors.toList());
    }

    public RegionDTO obtenerPorId(Integer id) {
        log.info("Buscando region con id {}", id);
        Optional<Region> optional = regionRepository.findById(id);
        return optional.map(regionMapper::toDTO).orElse(null);
    }

    public RegionDTO crear(RegionRequestDTO dto) {
        try {
            

            Region region = regionMapper.toEntity(dto);
            Region guardado = regionRepository.save(region);
            log.info("Region creado con id {}", guardado.getId());
            return regionMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear region", e);
            return null;
        }
    }

    public RegionDTO actualizar(Integer id, RegionRequestDTO dto) {
        try {
            Optional<Region> optional = regionRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Region region = optional.get();
            regionMapper.updateEntity(region, dto);
            
            Region guardado = regionRepository.save(region);
            log.info("Region actualizado con id {}", guardado.getId());
            return regionMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar region", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!regionRepository.existsById(id)) {
            return false;
        }
        regionRepository.deleteById(id);
        log.info("Region eliminado con id {}", id);
        return true;
    }


}
