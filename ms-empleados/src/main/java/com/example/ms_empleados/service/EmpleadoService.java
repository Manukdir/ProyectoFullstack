package com.example.ms_empleados.service;

import com.example.ms_empleados.dto.EmpleadoDTO;
import com.example.ms_empleados.dto.EmpleadoRequestDTO;
import com.example.ms_empleados.mapper.EmpleadoMapper;
import com.example.ms_empleados.model.Empleado;
import com.example.ms_empleados.repository.EmpleadoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoService.class);

        @Autowired
        private EmpleadoRepository empleadoRepository;
    @Autowired
        private EmpleadoMapper empleadoMapper;

    public List<EmpleadoDTO> listar() {
        log.info("Listando empleados");
        return empleadoRepository.findAll().stream().map(empleadoMapper::toDTO).collect(Collectors.toList());
    }

    public EmpleadoDTO obtenerPorId(Integer id) {
        log.info("Buscando empleado con id {}", id);
        Optional<Empleado> optional = empleadoRepository.findById(id);
        return optional.map(empleadoMapper::toDTO).orElse(null);
    }

    public EmpleadoDTO crear(EmpleadoRequestDTO dto) {
        try {

            Empleado empleado = empleadoMapper.toEntity(dto);
            Empleado guardado = empleadoRepository.save(empleado);
            log.info("Empleado creado con id {}", guardado.getId());
            return empleadoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear empleado", e);
            return null;
        }
    }

    public EmpleadoDTO actualizar(Integer id, EmpleadoRequestDTO dto) {
        try {
            Optional<Empleado> optional = empleadoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }

            Empleado empleado = optional.get();
            empleadoMapper.updateEntity(empleado, dto);
            
            Empleado guardado = empleadoRepository.save(empleado);
            log.info("Empleado actualizado con id {}", guardado.getId());
            return empleadoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar empleado", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            return false;
        }
        empleadoRepository.deleteById(id);
        log.info("Empleado eliminado con id {}", id);
        return true;
    }

    public List<EmpleadoDTO> buscarPorSucursalYAnio(Integer sucursalId, Integer anio) {
        log.info("Ejecutando consulta personalizada de empleados");
        return empleadoRepository.buscarPorSucursalYAnio(sucursalId, anio).stream().map(empleadoMapper::toDTO).collect(Collectors.toList());
    }


}
