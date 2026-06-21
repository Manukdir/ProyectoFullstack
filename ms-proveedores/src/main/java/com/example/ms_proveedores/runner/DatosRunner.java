package com.example.ms_proveedores.runner;

import com.example.ms_proveedores.model.Contrato;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ContratoRepository;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inserta datos iniciales solamente cuando las tablas están vacías.
 */
@Component
public class DatosRunner implements CommandLineRunner {

        @Autowired
        private ProveedorRepository proveedorRepository;
    @Autowired
        private ContratoRepository contratoRepository;

    @Override
    public void run(String... args) {
                if (proveedorRepository.count() == 0) {
            Proveedor proveedor1 = new Proveedor();
            proveedor1.setNombre("nombre 1");
            proveedor1.setRut("rut 1");
            proveedor1.setEmail("email1@correo.cl");
            proveedor1.setCalificacion(10);
            proveedor1.setMontoCompras(new BigDecimal("1000.00"));
            proveedor1.setActivo(true);
            proveedor1.setFechaRegistro(LocalDate.of(2026, 1, 10));
            proveedor1 = proveedorRepository.save(proveedor1);
        
            Proveedor proveedor2 = new Proveedor();
            proveedor2.setNombre("nombre 2");
            proveedor2.setRut("rut 2");
            proveedor2.setEmail("email2@correo.cl");
            proveedor2.setCalificacion(20);
            proveedor2.setMontoCompras(new BigDecimal("2000.00"));
            proveedor2.setActivo(false);
            proveedor2.setFechaRegistro(LocalDate.of(2026, 2, 10));
            proveedor2 = proveedorRepository.save(proveedor2);
        
            Proveedor proveedor3 = new Proveedor();
            proveedor3.setNombre("nombre 3");
            proveedor3.setRut("rut 3");
            proveedor3.setEmail("email3@correo.cl");
            proveedor3.setCalificacion(30);
            proveedor3.setMontoCompras(new BigDecimal("3000.00"));
            proveedor3.setActivo(true);
            proveedor3.setFechaRegistro(LocalDate.of(2026, 3, 10));
            proveedor3 = proveedorRepository.save(proveedor3);
        
        }
        
        List<Proveedor> proveedorsBase = proveedorRepository.findAll();
        if (contratoRepository.count() == 0) {
            Contrato contrato1 = new Contrato();
            contrato1.setCodigoContrato("CODIGOCONTRATO-001");
            contrato1.setDescripcion("descripcion 1");
            contrato1.setDuracionMeses(10);
            contrato1.setMontoMensual(new BigDecimal("1000.00"));
            contrato1.setActivo(true);
            contrato1.setFechaInicio(LocalDate.of(2026, 1, 10));
            contrato1.setFechaTermino(LocalDate.of(2027, 1, 10));
            contrato1.setProveedor(proveedorsBase.get((1 - 1) % proveedorsBase.size()));
            contrato1 = contratoRepository.save(contrato1);
        
            Contrato contrato2 = new Contrato();
            contrato2.setCodigoContrato("CODIGOCONTRATO-002");
            contrato2.setDescripcion("descripcion 2");
            contrato2.setDuracionMeses(20);
            contrato2.setMontoMensual(new BigDecimal("2000.00"));
            contrato2.setActivo(false);
            contrato2.setFechaInicio(LocalDate.of(2026, 2, 10));
            contrato2.setFechaTermino(LocalDate.of(2027, 2, 10));
            contrato2.setProveedor(proveedorsBase.get((2 - 1) % proveedorsBase.size()));
            contrato2 = contratoRepository.save(contrato2);
        
            Contrato contrato3 = new Contrato();
            contrato3.setCodigoContrato("CODIGOCONTRATO-003");
            contrato3.setDescripcion("descripcion 3");
            contrato3.setDuracionMeses(30);
            contrato3.setMontoMensual(new BigDecimal("3000.00"));
            contrato3.setActivo(true);
            contrato3.setFechaInicio(LocalDate.of(2026, 3, 10));
            contrato3.setFechaTermino(LocalDate.of(2027, 3, 10));
            contrato3.setProveedor(proveedorsBase.get((3 - 1) % proveedorsBase.size()));
            contrato3 = contratoRepository.save(contrato3);
        
        }
        
    }
}
