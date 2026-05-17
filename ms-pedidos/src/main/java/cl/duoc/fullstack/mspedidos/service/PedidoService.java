package cl.duoc.fullstack.mspedidos.service;

import cl.duoc.fullstack.mspedidos.client.ProductoClient;
import cl.duoc.fullstack.mspedidos.client.UsuarioClient;
import cl.duoc.fullstack.mspedidos.dto.PedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.PedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.exception.ResourceNotFoundException;
import cl.duoc.fullstack.mspedidos.mapper.PedidoMapper;
import cl.duoc.fullstack.mspedidos.model.Pedido;
import cl.duoc.fullstack.mspedidos.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    @Value("${app.validar-servicios-externos:false}")
    private boolean validarServiciosExternos;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioClient usuarioClient, ProductoClient productoClient) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    public List<PedidoDTO> findAll() {
        log.info("Listando Pedido");
        return pedidoRepository.findAll().stream().map(PedidoMapper::toDTO).toList();
    }

    public PedidoDTO findById(Integer id) {
        log.info("Buscando Pedido con id {}", id);
        return PedidoMapper.toDTO(buscarEntidad(id));
    }

    public PedidoDTO save(PedidoRequestDTO request) {
        try {
            log.info("Guardando Pedido");
            if (validarServiciosExternos) {
                usuarioClient.findById(request.getClienteId());
            }
            Pedido pedido = PedidoMapper.toEntity(request);

            return PedidoMapper.toDTO(pedidoRepository.save(pedido));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al guardar Pedido", ex);
            throw ex;
        }
    }

    public PedidoDTO update(Integer id, PedidoRequestDTO request) {
        try {
            log.info("Actualizando Pedido con id {}", id);
            Pedido existente = buscarEntidad(id);
            if (validarServiciosExternos) {
                usuarioClient.findById(request.getClienteId());
            }
            existente.setNumeroPedido(request.getNumeroPedido());
            existente.setClienteId(request.getClienteId());
            existente.setTotal(request.getTotal());
            existente.setPagado(request.isPagado());
            existente.setFechaPedido(request.getFechaPedido());
            existente.setEstado(request.getEstado());
            existente.setObservacion(request.getObservacion());

            return PedidoMapper.toDTO(pedidoRepository.save(existente));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al actualizar Pedido", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando Pedido con id {}", id);
        Pedido existente = buscarEntidad(id);
        pedidoRepository.delete(existente);
    }

    public List<PedidoDTO> buscarPagadosRecientes() {
        log.info("Ejecutando query personalizada de Pedido");
        return pedidoRepository.buscarPedidosPagadosRecientes().stream().map(PedidoMapper::toDTO).toList();
    }

    private Pedido buscarEntidad(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + id));
    }
}
