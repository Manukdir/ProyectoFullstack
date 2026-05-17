package cl.duoc.fullstack.msenvios.service;

import cl.duoc.fullstack.msenvios.client.PedidoClient;
import cl.duoc.fullstack.msenvios.client.UsuarioClient;
import cl.duoc.fullstack.msenvios.dto.EnvioDTO;
import cl.duoc.fullstack.msenvios.dto.EnvioRequestDTO;
import cl.duoc.fullstack.msenvios.exception.ResourceNotFoundException;
import cl.duoc.fullstack.msenvios.mapper.EnvioMapper;
import cl.duoc.fullstack.msenvios.model.Envio;
import cl.duoc.fullstack.msenvios.repository.EnvioRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    private static final Logger log = LoggerFactory.getLogger(EnvioService.class);

    private final EnvioRepository envioRepository;
    private final PedidoClient pedidoClient;
    private final UsuarioClient usuarioClient;

    @Value("${app.validar-servicios-externos:false}")
    private boolean validarServiciosExternos;

    public EnvioService(EnvioRepository envioRepository, PedidoClient pedidoClient, UsuarioClient usuarioClient) {
        this.envioRepository = envioRepository;
        this.pedidoClient = pedidoClient;
        this.usuarioClient = usuarioClient;
    }

    public List<EnvioDTO> findAll() {
        log.info("Listando Envio");
        return envioRepository.findAll().stream().map(EnvioMapper::toDTO).toList();
    }

    public EnvioDTO findById(Integer id) {
        log.info("Buscando Envio con id {}", id);
        return EnvioMapper.toDTO(buscarEntidad(id));
    }

    public EnvioDTO save(EnvioRequestDTO request) {
        try {
            log.info("Guardando Envio");
            if (validarServiciosExternos) {
                pedidoClient.findById(request.getPedidoId());
            }
            if (validarServiciosExternos) {
                usuarioClient.findById(request.getUsuarioId());
            }
            Envio envio = EnvioMapper.toEntity(request);

            return EnvioMapper.toDTO(envioRepository.save(envio));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al guardar Envio", ex);
            throw ex;
        }
    }

    public EnvioDTO update(Integer id, EnvioRequestDTO request) {
        try {
            log.info("Actualizando Envio con id {}", id);
            Envio existente = buscarEntidad(id);
            if (validarServiciosExternos) {
                pedidoClient.findById(request.getPedidoId());
            }
            if (validarServiciosExternos) {
                usuarioClient.findById(request.getUsuarioId());
            }
            existente.setPedidoId(request.getPedidoId());
            existente.setUsuarioId(request.getUsuarioId());
            existente.setCodigoEnvio(request.getCodigoEnvio());
            existente.setDireccionEntrega(request.getDireccionEntrega());
            existente.setCostoEnvio(request.getCostoEnvio());
            existente.setEntregado(request.isEntregado());
            existente.setFechaEnvio(request.getFechaEnvio());
            existente.setEstadoEnvio(request.getEstadoEnvio());

            return EnvioMapper.toDTO(envioRepository.save(existente));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al actualizar Envio", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Envio con id {}", id);
        Envio existente = buscarEntidad(id);
        envioRepository.delete(existente);
    }

    public List<EnvioDTO> buscarNoEntregadosPorRango(LocalDate desde, LocalDate hasta) {
        log.info("Ejecutando query personalizada de Envio");
        return envioRepository.buscarNoEntregadosPorRango(desde, hasta).stream().map(EnvioMapper::toDTO).toList();
    }

    private Envio buscarEntidad(Integer id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id " + id));
    }
}
