package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.DetallePedidoDTO;
import com.example.ms_pedidos.dto.DetallePedidoRequestDTO;
import com.example.ms_pedidos.exception.ResourceNotFoundException;
import com.example.ms_pedidos.mapper.DetallePedidoMapper;
import com.example.ms_pedidos.model.DetallePedido;
import com.example.ms_pedidos.model.Pedido;
import com.example.ms_pedidos.repository.DetallePedidoRepository;
import com.example.ms_pedidos.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository detallePedidoRepository;
    @Mock
    private DetallePedidoMapper detallePedidoMapper;
    @Mock
    private PedidoRepository pedidoRepository;
    @InjectMocks
    private DetallePedidoService detallePedidoService;

    @Test
    void listarRetornaDetallesConvertidos() {
        DetallePedido detalle = new DetallePedido();
        DetallePedidoDTO dto = new DetallePedidoDTO();
        when(detallePedidoRepository.findAll()).thenReturn(List.of(detalle));
        when(detallePedidoMapper.toDTO(detalle)).thenReturn(dto);

        assertEquals(List.of(dto), detallePedidoService.listar());
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(detallePedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> detallePedidoService.obtenerPorId(99));
    }

    @Test
    void crearGuardaDetalleCuandoPedidoExiste() {
        DetallePedidoRequestDTO request = new DetallePedidoRequestDTO();
        request.setPedidoId(1);
        Pedido pedido = new Pedido();
        DetallePedido detalle = new DetallePedido();
        DetallePedidoDTO esperado = new DetallePedidoDTO();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(detallePedidoMapper.toEntity(request, pedido)).thenReturn(detalle);
        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);
        when(detallePedidoMapper.toDTO(detalle)).thenReturn(esperado);

        assertEquals(esperado, detallePedidoService.crear(request));
    }

    @Test
    void crearLanzaExcepcionCuandoPedidoNoExiste() {
        DetallePedidoRequestDTO request = new DetallePedidoRequestDTO();
        request.setPedidoId(99);
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> detallePedidoService.crear(request));
    }

    @Test
    void actualizarModificaDetalleExistente() {
        DetallePedidoRequestDTO request = new DetallePedidoRequestDTO();
        request.setPedidoId(1);
        Pedido pedido = new Pedido();
        DetallePedido detalle = new DetallePedido();
        DetallePedidoDTO esperado = new DetallePedidoDTO();
        when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalle));
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);
        when(detallePedidoMapper.toDTO(detalle)).thenReturn(esperado);

        assertEquals(esperado, detallePedidoService.actualizar(1, request));
        verify(detallePedidoMapper).updateEntity(detalle, request);
    }

    @Test
    void eliminarBorraDetalleExistente() {
        when(detallePedidoRepository.existsById(1)).thenReturn(true);

        assertTrue(detallePedidoService.eliminar(1));
        verify(detallePedidoRepository).deleteById(1);
    }
}
