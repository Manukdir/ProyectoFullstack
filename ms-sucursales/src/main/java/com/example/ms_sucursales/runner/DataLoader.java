package com.example.ms_sucursales.runner;

import com.example.ms_sucursales.model.Region;
import com.example.ms_sucursales.model.Sucursal;
import com.example.ms_sucursales.repository.RegionRepository;
import com.example.ms_sucursales.repository.SucursalRepository;
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
            RegionRepository regionRepository,
            SucursalRepository sucursalRepository
    ) {
        return args -> {

            if (regionRepository.count() == 0) {
                Region metropolitana = new Region();
                metropolitana.setNombre("Metropolitana");
                metropolitana.setCodigo("RM");
                metropolitana.setCantidadComunas(52);
                metropolitana.setCostoDespachoBase(new BigDecimal("3500.00"));
                metropolitana.setActiva(true);
                metropolitana.setFechaCreacion(LocalDate.of(2026, 1, 1));

                Region valparaiso = new Region();
                valparaiso.setNombre("Valparaiso");
                valparaiso.setCodigo("V");
                valparaiso.setCantidadComunas(38);
                valparaiso.setCostoDespachoBase(new BigDecimal("4500.00"));
                valparaiso.setActiva(true);
                valparaiso.setFechaCreacion(LocalDate.of(2026, 1, 2));

                Region biobio = new Region();
                biobio.setNombre("Biobio");
                biobio.setCodigo("VIII");
                biobio.setCantidadComunas(33);
                biobio.setCostoDespachoBase(new BigDecimal("5500.00"));
                biobio.setActiva(true);
                biobio.setFechaCreacion(LocalDate.of(2026, 1, 3));

                regionRepository.saveAll(List.of(metropolitana, valparaiso, biobio));
            }

            if (sucursalRepository.count() == 0) {
                List<Region> regiones = regionRepository.findAll();

                Region metropolitana = regiones.stream()
                        .filter(region -> region.getCodigo().equals("RM"))
                        .findFirst()
                        .orElseThrow();

                Region valparaiso = regiones.stream()
                        .filter(region -> region.getCodigo().equals("V"))
                        .findFirst()
                        .orElseThrow();

                Region biobio = regiones.stream()
                        .filter(region -> region.getCodigo().equals("VIII"))
                        .findFirst()
                        .orElseThrow();

                Sucursal sucursalSantiago = new Sucursal();
                sucursalSantiago.setRegion(metropolitana);
                sucursalSantiago.setNombre("Sucursal Santiago Centro");
                sucursalSantiago.setDireccion("Avenida Libertador Bernardo O'Higgins 1234");
                sucursalSantiago.setCapacidadBodega(500);
                sucursalSantiago.setVentasMensuales(new BigDecimal("25000000.00"));
                sucursalSantiago.setActiva(true);
                sucursalSantiago.setFechaApertura(LocalDate.of(2026, 1, 10));

                Sucursal sucursalValparaiso = new Sucursal();
                sucursalValparaiso.setRegion(valparaiso);
                sucursalValparaiso.setNombre("Sucursal Valparaiso");
                sucursalValparaiso.setDireccion("Avenida Argentina 800");
                sucursalValparaiso.setCapacidadBodega(350);
                sucursalValparaiso.setVentasMensuales(new BigDecimal("18000000.00"));
                sucursalValparaiso.setActiva(true);
                sucursalValparaiso.setFechaApertura(LocalDate.of(2026, 1, 15));

                Sucursal sucursalConcepcion = new Sucursal();
                sucursalConcepcion.setRegion(biobio);
                sucursalConcepcion.setNombre("Sucursal Concepcion");
                sucursalConcepcion.setDireccion("Avenida Paicavi 1200");
                sucursalConcepcion.setCapacidadBodega(400);
                sucursalConcepcion.setVentasMensuales(new BigDecimal("21000000.00"));
                sucursalConcepcion.setActiva(true);
                sucursalConcepcion.setFechaApertura(LocalDate.of(2026, 1, 20));

                sucursalRepository.saveAll(
                        List.of(sucursalSantiago, sucursalValparaiso, sucursalConcepcion)
                );
            }
        };
    }
}
