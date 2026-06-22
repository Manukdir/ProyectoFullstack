package com.example.ms_proveedores.runner;

import com.example.ms_proveedores.model.Contrato;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ContratoRepository;
import com.example.ms_proveedores.repository.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatosIniciales(
            ProveedorRepository proveedorRepository,
            ContratoRepository contratoRepository
    ) {
        return args -> {

            if (proveedorRepository.count() > 0 || contratoRepository.count() > 0) {
                return;
            }

            Proveedor proveedorTecnologia = new Proveedor();
            proveedorTecnologia.setNombre("Tecnologia Integral SPA");
            proveedorTecnologia.setRut("76.123.456-7");
            proveedorTecnologia.setEmail("contacto@tecnologiaintegral.cl");
            proveedorTecnologia.setCalificacion(5);
            proveedorTecnologia.setMontoCompras(new BigDecimal("25000000.00"));
            proveedorTecnologia.setActivo(true);
            proveedorTecnologia.setFechaRegistro(LocalDate.of(2026, 1, 10));

            Proveedor proveedorLogistica = new Proveedor();
            proveedorLogistica.setNombre("Logistica del Pacifico Ltda");
            proveedorLogistica.setRut("77.234.567-8");
            proveedorLogistica.setEmail("ventas@logisticapacifico.cl");
            proveedorLogistica.setCalificacion(4);
            proveedorLogistica.setMontoCompras(new BigDecimal("18000000.00"));
            proveedorLogistica.setActivo(true);
            proveedorLogistica.setFechaRegistro(LocalDate.of(2026, 1, 15));

            Proveedor proveedorInactivo = new Proveedor();
            proveedorInactivo.setNombre("Servicios Industriales Sur SPA");
            proveedorInactivo.setRut("78.345.678-9");
            proveedorInactivo.setEmail("contacto@serviciossur.cl");
            proveedorInactivo.setCalificacion(3);
            proveedorInactivo.setMontoCompras(new BigDecimal("9500000.00"));
            proveedorInactivo.setActivo(false);
            proveedorInactivo.setFechaRegistro(LocalDate.of(2026, 1, 20));

            proveedorRepository.saveAll(List.of(
                    proveedorTecnologia,
                    proveedorLogistica,
                    proveedorInactivo
            ));

            Contrato contratoTecnologia = new Contrato();
            contratoTecnologia.setProveedor(proveedorTecnologia);
            contratoTecnologia.setCodigoContrato("CT-TEC-001");
            contratoTecnologia.setDescripcion("Soporte y mantenimiento tecnologico");
            contratoTecnologia.setDuracionMeses(12);
            contratoTecnologia.setMontoMensual(new BigDecimal("1500000.00"));
            contratoTecnologia.setActivo(true);
            contratoTecnologia.setFechaInicio(LocalDate.of(2026, 1, 1));
            contratoTecnologia.setFechaTermino(LocalDate.of(2026, 12, 31));

            Contrato contratoLogistica = new Contrato();
            contratoLogistica.setProveedor(proveedorLogistica);
            contratoLogistica.setCodigoContrato("CT-LOG-001");
            contratoLogistica.setDescripcion("Servicio de distribucion nacional");
            contratoLogistica.setDuracionMeses(10);
            contratoLogistica.setMontoMensual(new BigDecimal("1200000.00"));
            contratoLogistica.setActivo(true);
            contratoLogistica.setFechaInicio(LocalDate.of(2026, 2, 1));
            contratoLogistica.setFechaTermino(LocalDate.of(2026, 11, 30));

            Contrato contratoInactivo = new Contrato();
            contratoInactivo.setProveedor(proveedorInactivo);
            contratoInactivo.setCodigoContrato("CT-SUR-001");
            contratoInactivo.setDescripcion("Servicio de mantencion industrial");
            contratoInactivo.setDuracionMeses(6);
            contratoInactivo.setMontoMensual(new BigDecimal("800000.00"));
            contratoInactivo.setActivo(false);
            contratoInactivo.setFechaInicio(LocalDate.of(2026, 1, 1));
            contratoInactivo.setFechaTermino(LocalDate.of(2026, 6, 30));

            contratoRepository.saveAll(List.of(
                    contratoTecnologia,
                    contratoLogistica,
                    contratoInactivo
            ));
        };
    }
}
