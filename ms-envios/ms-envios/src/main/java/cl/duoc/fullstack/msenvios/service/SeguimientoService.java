package cl.duoc.fullstack.msenvios.service;

import cl.duoc.fullstack.msenvios.client.PedidoClient;
import cl.duoc.fullstack.msenvios.client.UsuarioClient;
import cl.duoc.fullstack.msenvios.dto.SeguimientoDTO;
import cl.duoc.fullstack.msenvios.dto.SeguimientoRequestDTO;
import cl.duoc.fullstack.msenvios.exception.ResourceNotFoundException;
import cl.duoc.fullstack.msenvios.mapper.SeguimientoMapper;
import cl.duoc.fullstack.msenvios.model.Envio;
import cl.duoc.fullstack.msenvios.model.Seguimiento;
import cl.duoc.fullstack.msenvios.repository.EnvioRepository;
import cl.duoc.fullstack.msenvios.repository.SeguimientoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SeguimientoService {

    private static final Logger log = LoggerFactory.getLogger(SeguimientoService.class);

    private final SeguimientoRepository seguimientoRepository;
    private final EnvioRepository envioRepository;
    private final PedidoClient pedidoClient;
    private final UsuarioClient usuarioClient;

    @Value("${app.validar-servicios-externos:false}")
    private boolean validarServiciosExternos;

    public SeguimientoService(SeguimientoRepository seguimientoRepository, EnvioRepository envioRepository, PedidoClient pedidoClient, UsuarioClient usuarioClient) {
        this.seguimientoRepository = seguimientoRepository;
        this.envioRepository = envioRepository;
        this.pedidoClient = pedidoClient;
        this.usuarioClient = usuarioClient;
    }

    public List<SeguimientoDTO> findAll() {
        log.info("Listando Seguimiento");
        return seguimientoRepository.findAll().stream().map(SeguimientoMapper::toDTO).toList();
    }

    public SeguimientoDTO findById(Integer id) {
        log.info("Buscando Seguimiento con id {}", id);
        return SeguimientoMapper.toDTO(buscarEntidad(id));
    }

    public SeguimientoDTO save(SeguimientoRequestDTO request) {
        try {
            log.info("Guardando Seguimiento");
            
            Seguimiento seguimiento = SeguimientoMapper.toEntity(request);
            Envio envio = envioRepository.findById(request.getEnvioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id " + request.getEnvioId()));
            seguimiento.setEnvio(envio);
            return SeguimientoMapper.toDTO(seguimientoRepository.save(seguimiento));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al guardar Seguimiento", ex);
            throw ex;
        }
    }

    public SeguimientoDTO update(Integer id, SeguimientoRequestDTO request) {
        try {
            log.info("Actualizando Seguimiento con id {}", id);
            Seguimiento existente = buscarEntidad(id);
            
            existente.setUbicacionActual(request.getUbicacionActual());
            existente.setEstado(request.getEstado());
            existente.setOrdenEvento(request.getOrdenEvento());
            existente.setVisibleCliente(request.isVisibleCliente());
            existente.setFechaEvento(request.getFechaEvento());
            existente.setComentario(request.getComentario());
            Envio envio = envioRepository.findById(request.getEnvioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id " + request.getEnvioId()));
            existente.setEnvio(envio);
            return SeguimientoMapper.toDTO(seguimientoRepository.save(existente));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al actualizar Seguimiento", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Seguimiento con id {}", id);
        Seguimiento existente = buscarEntidad(id);
        seguimientoRepository.delete(existente);
    }

    private Seguimiento buscarEntidad(Integer id) {
        return seguimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguimiento no encontrado con id " + id));
    }
}
