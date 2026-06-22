package com.example.ms_reportes.service;

import com.example.ms_reportes.client.EnvioClient;
import com.example.ms_reportes.client.EnvioRemoteDTO;
import com.example.ms_reportes.client.PagoClient;
import com.example.ms_reportes.client.PagoRemoteDTO;
import com.example.ms_reportes.client.PedidoClient;
import com.example.ms_reportes.client.PedidoRemoteDTO;
import com.example.ms_reportes.dto.ReporteDTO;
import com.example.ms_reportes.dto.ReporteRequestDTO;
import com.example.ms_reportes.mapper.ReporteMapper;
import com.example.ms_reportes.model.Reporte;
import com.example.ms_reportes.repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ReporteMapper reporteMapper;

    @Autowired
    private PedidoClient pedidoClient;

    @Autowired
    private PagoClient pagoClient;

    @Autowired
    private EnvioClient envioClient;

    public List<ReporteDTO> listar() {
        log.info("Listando reportes");

        return reporteRepository.findAll()
                .stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO obtenerPorId(Integer id) {
        log.info("Buscando reporte con id {}", id);

        Optional<Reporte> optional = reporteRepository.findById(id);

        return optional.map(reporteMapper::toDTO).orElse(null);
    }

    public ReporteDTO crear(ReporteRequestDTO dto) {
        try {
            Reporte reporte = reporteMapper.toEntity(dto);
            Reporte guardado = reporteRepository.save(reporte);

            log.info("Reporte creado con id {}", guardado.getId());

            return reporteMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al crear reporte", e);
            return null;
        }
    }

    public ReporteDTO actualizar(Integer id, ReporteRequestDTO dto) {
        try {
            Optional<Reporte> optional = reporteRepository.findById(id);

            if (optional.isEmpty()) {
                return null;
            }

            Reporte reporte = optional.get();
            reporteMapper.updateEntity(reporte, dto);

            Reporte guardado = reporteRepository.save(reporte);

            log.info("Reporte actualizado con id {}", guardado.getId());

            return reporteMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al actualizar reporte", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!reporteRepository.existsById(id)) {
            return false;
        }

        reporteRepository.deleteById(id);

        log.info("Reporte eliminado con id {}", id);

        return true;
    }

    public ReporteDTO generarReporteConsolidado() {
        try {
            List<PedidoRemoteDTO> pedidos = pedidoClient.listarPedidos();
            List<PagoRemoteDTO> pagos = pagoClient.listarPagos();
            List<EnvioRemoteDTO> envios = envioClient.listarEnvios();

            int cantidadPedidos = pedidos != null ? pedidos.size() : 0;

            int pagosConfirmados = pagos != null
                    ? (int) pagos.stream()
                            .filter(pago -> Boolean.TRUE.equals(pago.getPagado()))
                            .count()
                    : 0;

            int enviosEntregados = envios != null
                    ? (int) envios.stream()
                            .filter(envio -> Boolean.TRUE.equals(envio.getEntregado()))
                            .count()
                    : 0;

            BigDecimal totalVentas = pagos != null
                    ? pagos.stream()
                      .filter(pago -> Boolean.TRUE.equals(pago.getPagado()))
                      .map(PagoRemoteDTO::getMonto)
                      .filter(monto -> monto != null)
                      .reduce(BigDecimal.ZERO, BigDecimal::add)
                    : BigDecimal.ZERO;

            Reporte reporte = new Reporte();
            reporte.setNombre("Reporte consolidado de operaciones");
            reporte.setTipoReporte("CONSOLIDADO");
            reporte.setResumen(
                    "Pedidos: " + cantidadPedidos
                            + " | Pagos confirmados: " + pagosConfirmados
                            + " | Envios entregados: " + enviosEntregados
            );
            reporte.setCantidadPedidos(cantidadPedidos);
            reporte.setTotalVentas(totalVentas);
            reporte.setActivo(true);
            reporte.setFechaGeneracion(LocalDateTime.now());

            Reporte guardado = reporteRepository.save(reporte);

            log.info(
                    "Reporte consolidado generado. Pedidos: {}, pagos: {}, envios: {}",
                    cantidadPedidos,
                    pagosConfirmados,
                    enviosEntregados
            );

            return reporteMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("No fue posible generar el reporte consolidado", e);

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "No fue posible obtener información de pedidos, pagos o envios"
            );
        }
    }
}