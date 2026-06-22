package com.example.ms_empleados.service;

import com.example.ms_empleados.client.SucursalClient;
import com.example.ms_empleados.client.SucursalClientDTO;
import com.example.ms_empleados.dto.EmpleadoDTO;
import com.example.ms_empleados.dto.EmpleadoRequestDTO;
import com.example.ms_empleados.mapper.EmpleadoMapper;
import com.example.ms_empleados.model.Empleado;
import com.example.ms_empleados.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoService.class);

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Autowired
    private SucursalClient sucursalClient;

    public List<EmpleadoDTO> listar() {
        log.info("Listando empleados");

        return empleadoRepository.findAll()
                .stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO obtenerPorId(Integer id) {
        log.info("Buscando empleado con id {}", id);

        Optional<Empleado> optional = empleadoRepository.findById(id);

        return optional.map(empleadoMapper::toDTO).orElse(null);
    }

    public EmpleadoDTO crear(EmpleadoRequestDTO dto) {
        validarSucursal(dto.getSucursalId());

        Empleado empleado = empleadoMapper.toEntity(dto);
        Empleado guardado = empleadoRepository.save(empleado);

        log.info("Empleado creado con id {}", guardado.getId());

        return empleadoMapper.toDTO(guardado);
    }

    public EmpleadoDTO actualizar(Integer id, EmpleadoRequestDTO dto) {
        Optional<Empleado> optional = empleadoRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        validarSucursal(dto.getSucursalId());

        Empleado empleado = optional.get();
        empleadoMapper.updateEntity(empleado, dto);

        Empleado guardado = empleadoRepository.save(empleado);

        log.info("Empleado actualizado con id {}", guardado.getId());

        return empleadoMapper.toDTO(guardado);
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
        log.info("Buscando empleados de sucursal {} ingresados en {}", sucursalId, anio);

        return empleadoRepository.buscarPorSucursalYAnio(sucursalId, anio)
                .stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void validarSucursal(Integer sucursalId) {
        SucursalClientDTO sucursal;

        try {
            sucursal = sucursalClient.obtenerSucursalPorId(sucursalId);

        } catch (WebClientResponseException.NotFound e) {
            log.warn("La sucursal {} no existe", sucursalId);

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La sucursal indicada no existe"
            );

        } catch (WebClientResponseException e) {
            log.error("ms-sucursales respondió con error {}", e.getStatusCode());

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "No fue posible validar la sucursal"
            );

        } catch (Exception e) {
            log.error("ms-sucursales no está disponible", e);

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "ms-sucursales no está disponible"
            );
        }

        if (sucursal == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La sucursal indicada no existe"
            );
        }

        if (Boolean.FALSE.equals(sucursal.getActiva())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede asociar un empleado a una sucursal inactiva"
            );
        }
    }
}