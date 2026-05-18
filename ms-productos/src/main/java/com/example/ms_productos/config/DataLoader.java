package com.example.ms_productos.config;

import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.model.Producto;
import com.example.ms_productos.repository.CategoriaRepository;
import com.example.ms_productos.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
// esto es lo mismo que el commandlinnerunner , recordar modificarlo al final
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        return args -> {
            if (categoriaRepository.count() == 0) {

                Categoria c1 = new Categoria();
                c1.setNombre("Electronica");
                c1.setDescripcion("Articulos tecnologicos");
                c1.setCodigoAlterno("CAT-ELEC-01");
                c1.setPrioridadVisualizacion(1);
                c1.setActivo(true);
                c1.setFechaCreacion(LocalDate.now());

                Categoria c2 = new Categoria();
                c2.setNombre("Hogar");
                c2.setDescripcion("Muebles y electrodomesticos");
                c2.setCodigoAlterno("CAT-HOG-02");
                c2.setPrioridadVisualizacion(2);
                c2.setActivo(true);
                c2.setFechaCreacion(LocalDate.now());

                Categoria c3 = new Categoria();
                c3.setNombre("Deportes");
                c3.setDescripcion("Ropa y accesorios deportivos");
                c3.setCodigoAlterno("CAT-DEP-03");
                c3.setPrioridadVisualizacion(3);
                c3.setActivo(false);
                c3.setFechaCreacion(LocalDate.now());

                categoriaRepository.save(c1);
                categoriaRepository.save(c2);
                categoriaRepository.save(c3);

                Producto p1 = new Producto();
                p1.setNombre("Notebook Gamer");
                p1.setCodigoSku("SKU-1001");
                p1.setPrecio(1500000.0);
                p1.setStock(10);
                p1.setFechaIngreso(LocalDate.now());
                p1.setDisponible(true);
                p1.setCategoria(c1);

                Producto p2 = new Producto();
                p2.setNombre("Silla Ergonomica");
                p2.setCodigoSku("SKU-1002");
                p2.setPrecio(120000.0);
                p2.setStock(25);
                p2.setFechaIngreso(LocalDate.now());
                p2.setDisponible(true);
                p2.setCategoria(c2);

                Producto p3 = new Producto();
                p3.setNombre("Mancuernas 10kg");
                p3.setCodigoSku("SKU-1003");
                p3.setPrecio(45000.0);
                p3.setStock(5);
                p3.setFechaIngreso(LocalDate.now());
                p3.setDisponible(false);
                p3.setCategoria(c3);

                productoRepository.save(p1);
                productoRepository.save(p2);
                productoRepository.save(p3);
            }
        };
    }
}