package cl.duoc.fullstack.mspagos.service;

import cl.duoc.fullstack.mspagos.client.PedidoClient;
import cl.duoc.fullstack.mspagos.dto.PagoDTO;
import cl.duoc.fullstack.mspagos.dto.PagoRequestDTO;
import cl.duoc.fullstack.mspagos.exception.ResourceNotFoundException;
import cl.duoc.fullstack.mspagos.mapper.PagoMapper;
import cl.duoc.fullstack.mspagos.model.Pago;
import cl.duoc.fullstack.mspagos.repository.PagoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    private final PagoRepository pagoRepository;
    private final PedidoClient pedidoClient;

    @Value("${app.validar-servicios-externos:false}")
    private boolean validarServiciosExternos;

    public PagoService(PagoRepository pagoRepository, PedidoClient pedidoClient) {
        this.pagoRepository = pagoRepository;
        this.pedidoClient = pedidoClient;
    }

    public List<PagoDTO> findAll() {
        log.info("Listando Pago");
        return pagoRepository.findAll().stream().map(PagoMapper::toDTO).toList();
    }

    public PagoDTO findById(Integer id) {
        log.info("Buscando Pago con id {}", id);
        return PagoMapper.toDTO(buscarEntidad(id));
    }

    public PagoDTO save(PagoRequestDTO request) {
        try {
            log.info("Guardando Pago");
            if (validarServiciosExternos) {
                pedidoClient.findById(request.getPedidoId());
            }
            Pago pago = PagoMapper.toEntity(request);

            return PagoMapper.toDTO(pagoRepository.save(pago));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al guardar Pago", ex);
            throw ex;
        }
    }

    public PagoDTO update(Integer id, PagoRequestDTO request) {
        try {
            log.info("Actualizando Pago con id {}", id);
            Pago existente = buscarEntidad(id);
            if (validarServiciosExternos) {
                pedidoClient.findById(request.getPedidoId());
            }
            existente.setPedidoId(request.getPedidoId());
            existente.setCodigoTransaccion(request.getCodigoTransaccion());
            existente.setMonto(request.getMonto());
            existente.setMetodoPago(request.getMetodoPago());
            existente.setEstadoPago(request.getEstadoPago());
            existente.setConfirmado(request.isConfirmado());
            existente.setFechaPago(request.getFechaPago());

            return PagoMapper.toDTO(pagoRepository.save(existente));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al actualizar Pago", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Pago con id {}", id);
        Pago existente = buscarEntidad(id);
        pagoRepository.delete(existente);
    }

    public List<PagoDTO> buscarPorMontoYEstado(BigDecimal monto, String estadoPago) {
        log.info("Ejecutando query personalizada de Pago");
        return pagoRepository.buscarPorMontoYEstado(monto, estadoPago).stream().map(PagoMapper::toDTO).toList();
    }

    private Pago buscarEntidad(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id " + id));
    }
}
