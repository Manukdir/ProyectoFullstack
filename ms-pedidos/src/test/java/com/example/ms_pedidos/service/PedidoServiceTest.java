package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.dto.PedidoRequestDTO;
import com.example.ms_pedidos.exception.ResourceNotFoundException;
import com.example.ms_pedidos.mapper.PedidoMapper;
import com.example.ms_pedidos.model.Pedido;
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
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private PedidoMapper pedidoMapper;
    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void listarRetornaPedidosConvertidos() {
        Pedido pedido = new Pedido();
        PedidoDTO dto = new PedidoDTO();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        when(pedidoMapper.toDTO(pedido)).thenReturn(dto);

        List<PedidoDTO> resultado = pedidoService.listar();

        assertEquals(List.of(dto), resultado);
    }

    @Test
    void obtenerPorIdRetornaPedidoCuandoExiste() {
        Pedido pedido = new Pedido();
        PedidoDTO esperado = new PedidoDTO();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoMapper.toDTO(pedido)).thenReturn(esperado);

        PedidoDTO resultado = pedidoService.obtenerPorId(1);

        assertEquals(esperado, resultado);
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pedidoService.obtenerPorId(99));
    }

    @Test
    void crearGuardaYRetornaPedido() {
        PedidoRequestDTO request = new PedidoRequestDTO();
        Pedido pedido = new Pedido();
        Pedido guardado = new Pedido();
        guardado.setId(1);
        PedidoDTO esperado = new PedidoDTO();
        when(pedidoMapper.toEntity(request)).thenReturn(pedido);
        when(pedidoRepository.save(pedido)).thenReturn(guardado);
        when(pedidoMapper.toDTO(guardado)).thenReturn(esperado);

        PedidoDTO resultado = pedidoService.crear(request);

        assertEquals(esperado, resultado);
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void actualizarModificaYRetornaPedido() {
        PedidoRequestDTO request = new PedidoRequestDTO();
        Pedido pedido = new Pedido();
        PedidoDTO esperado = new PedidoDTO();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(esperado);

        PedidoDTO resultado = pedidoService.actualizar(1, request);

        assertEquals(esperado, resultado);
        verify(pedidoMapper).updateEntity(pedido, request);
    }

    @Test
    void eliminarBorraPedidoExistente() {
        when(pedidoRepository.existsById(1)).thenReturn(true);

        boolean resultado = pedidoService.eliminar(1);

        assertTrue(resultado);
        verify(pedidoRepository).deleteById(1);
    }

    @Test
    void eliminarLanzaExcepcionCuandoNoExiste() {
        when(pedidoRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> pedidoService.eliminar(99));
    }

    @Test
    void buscarPedidosPagadosRetornaConsultaConvertida() {
        Pedido pedido = new Pedido();
        PedidoDTO dto = new PedidoDTO();
        when(pedidoRepository.buscarPedidosPagadosOrdenados()).thenReturn(List.of(pedido));
        when(pedidoMapper.toDTO(pedido)).thenReturn(dto);

        List<PedidoDTO> resultado = pedidoService.buscarPedidosPagados();

        assertEquals(List.of(dto), resultado);
    }
}
