package cl.duoc.fullstack.mspedidos.service;

import cl.duoc.fullstack.mspedidos.client.ProductoClient;
import cl.duoc.fullstack.mspedidos.client.UsuarioClient;
import cl.duoc.fullstack.mspedidos.dto.DetallePedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.DetallePedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.exception.ResourceNotFoundException;
import cl.duoc.fullstack.mspedidos.mapper.DetallePedidoMapper;
import cl.duoc.fullstack.mspedidos.model.DetallePedido;
import cl.duoc.fullstack.mspedidos.model.Pedido;
import cl.duoc.fullstack.mspedidos.repository.DetallePedidoRepository;
import cl.duoc.fullstack.mspedidos.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {

    private static final Logger log = LoggerFactory.getLogger(DetallePedidoService.class);

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    @Value("${app.validar-servicios-externos:false}")
    private boolean validarServiciosExternos;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository, PedidoRepository pedidoRepository, UsuarioClient usuarioClient, ProductoClient productoClient) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    public List<DetallePedidoDTO> findAll() {
        log.info("Listando DetallePedido");
        return detallePedidoRepository.findAll().stream().map(DetallePedidoMapper::toDTO).toList();
    }

    public DetallePedidoDTO findById(Integer id) {
        log.info("Buscando DetallePedido con id {}", id);
        return DetallePedidoMapper.toDTO(buscarEntidad(id));
    }

    public DetallePedidoDTO save(DetallePedidoRequestDTO request) {
        try {
            log.info("Guardando DetallePedido");
            if (validarServiciosExternos) {
                productoClient.findById(request.getProductoId());
            }
            DetallePedido detallePedido = DetallePedidoMapper.toEntity(request);
            Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + request.getPedidoId()));
            detallePedido.setPedido(pedido);
            return DetallePedidoMapper.toDTO(detallePedidoRepository.save(detallePedido));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al guardar DetallePedido", ex);
            throw ex;
        }
    }

    public DetallePedidoDTO update(Integer id, DetallePedidoRequestDTO request) {
        try {
            log.info("Actualizando DetallePedido con id {}", id);
            DetallePedido existente = buscarEntidad(id);
            if (validarServiciosExternos) {
                productoClient.findById(request.getProductoId());
            }
            existente.setProductoId(request.getProductoId());
            existente.setNombreProducto(request.getNombreProducto());
            existente.setCantidad(request.getCantidad());
            existente.setPrecioUnitario(request.getPrecioUnitario());
            existente.setSubtotal(request.getSubtotal());
            existente.setActivo(request.isActivo());
            existente.setFechaRegistro(request.getFechaRegistro());
            Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + request.getPedidoId()));
            existente.setPedido(pedido);
            return DetallePedidoMapper.toDTO(detallePedidoRepository.save(existente));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error al actualizar DetallePedido", ex);
            throw ex;
        }
    }

    public void delete(Integer id) {
        log.info("Eliminando DetallePedido con id {}", id);
        DetallePedido existente = buscarEntidad(id);
        detallePedidoRepository.delete(existente);
    }

    private DetallePedido buscarEntidad(Integer id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetallePedido no encontrado con id " + id));
    }
}
