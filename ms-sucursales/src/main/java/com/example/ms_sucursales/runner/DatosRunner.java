package com.example.ms_sucursales.runner;

import com.example.ms_sucursales.model.Region;
import com.example.ms_sucursales.model.Sucursal;
import com.example.ms_sucursales.repository.RegionRepository;
import com.example.ms_sucursales.repository.SucursalRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosRunner implements CommandLineRunner {

        @Autowired
        private RegionRepository regionRepository;
    @Autowired
        private SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) {
                if (regionRepository.count() == 0) {
            Region region1 = new Region();
            region1.setNombre("nombre 1");
            region1.setCodigo("CODIGO-001");
            region1.setCantidadComunas(10);
            region1.setCostoDespachoBase(new BigDecimal("1000.00"));
            region1.setActiva(true);
            region1.setFechaCreacion(LocalDate.of(2026, 1, 10));
            region1 = regionRepository.save(region1);
        
            Region region2 = new Region();
            region2.setNombre("nombre 2");
            region2.setCodigo("CODIGO-002");
            region2.setCantidadComunas(20);
            region2.setCostoDespachoBase(new BigDecimal("2000.00"));
            region2.setActiva(false);
            region2.setFechaCreacion(LocalDate.of(2026, 2, 10));
            region2 = regionRepository.save(region2);
        
            Region region3 = new Region();
            region3.setNombre("nombre 3");
            region3.setCodigo("CODIGO-003");
            region3.setCantidadComunas(30);
            region3.setCostoDespachoBase(new BigDecimal("3000.00"));
            region3.setActiva(true);
            region3.setFechaCreacion(LocalDate.of(2026, 3, 10));
            region3 = regionRepository.save(region3);
        
        }
        
        if (sucursalRepository.count() == 0) {
            Sucursal sucursal1 = new Sucursal();
            sucursal1.setRegionId(1);
            sucursal1.setNombre("nombre 1");
            sucursal1.setDireccion("direccion 1");
            sucursal1.setCapacidadBodega(10);
            sucursal1.setVentasMensuales(new BigDecimal("1000.00"));
            sucursal1.setActiva(true);
            sucursal1.setFechaApertura(LocalDate.of(2026, 1, 10));
            sucursal1 = sucursalRepository.save(sucursal1);
        
            Sucursal sucursal2 = new Sucursal();
            sucursal2.setRegionId(2);
            sucursal2.setNombre("nombre 2");
            sucursal2.setDireccion("direccion 2");
            sucursal2.setCapacidadBodega(20);
            sucursal2.setVentasMensuales(new BigDecimal("2000.00"));
            sucursal2.setActiva(false);
            sucursal2.setFechaApertura(LocalDate.of(2026, 2, 10));
            sucursal2 = sucursalRepository.save(sucursal2);
        
            Sucursal sucursal3 = new Sucursal();
            sucursal3.setRegionId(3);
            sucursal3.setNombre("nombre 3");
            sucursal3.setDireccion("direccion 3");
            sucursal3.setCapacidadBodega(30);
            sucursal3.setVentasMensuales(new BigDecimal("3000.00"));
            sucursal3.setActiva(true);
            sucursal3.setFechaApertura(LocalDate.of(2026, 3, 10));
            sucursal3 = sucursalRepository.save(sucursal3);
        
        }
        
    }
}
