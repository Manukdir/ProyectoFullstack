package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.mapper.PedidoMapper;
import com.example.ms_pedidos.model.Pedido;
import com.example.ms_pedidos.repository.PedidoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void obtenerPorIdRetornaPedidoCuandoExiste() {
        Pedido pedido = new Pedido();
        PedidoDTO esperado = new PedidoDTO();

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoMapper.toDTO(pedido)).thenReturn(esperado);

        PedidoDTO resultado = pedidoService.obtenerPorId(1);

        assertEquals(esperado, resultado);
    }
}
