package com.example.ms_productos.runner;

import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.model.Producto;
import com.example.ms_productos.repository.CategoriaRepository;
import com.example.ms_productos.repository.ProductoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosRunner implements CommandLineRunner {

        @Autowired
        private CategoriaRepository categoriaRepository;
    @Autowired
        private ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
                if (categoriaRepository.count() == 0) {
            Categoria categoria1 = new Categoria();
            categoria1.setNombre("nombre 1");
            categoria1.setDescripcion("descripcion 1");
            categoria1.setPrioridad(10);
            categoria1.setMargenGanancia(new BigDecimal("1000.00"));
            categoria1.setActiva(true);
            categoria1.setFechaCreacion(LocalDate.of(2026, 1, 10));
            categoria1 = categoriaRepository.save(categoria1);
        
            Categoria categoria2 = new Categoria();
            categoria2.setNombre("nombre 2");
            categoria2.setDescripcion("descripcion 2");
            categoria2.setPrioridad(20);
            categoria2.setMargenGanancia(new BigDecimal("2000.00"));
            categoria2.setActiva(false);
            categoria2.setFechaCreacion(LocalDate.of(2026, 2, 10));
            categoria2 = categoriaRepository.save(categoria2);
        
            Categoria categoria3 = new Categoria();
            categoria3.setNombre("nombre 3");
            categoria3.setDescripcion("descripcion 3");
            categoria3.setPrioridad(30);
            categoria3.setMargenGanancia(new BigDecimal("3000.00"));
            categoria3.setActiva(true);
            categoria3.setFechaCreacion(LocalDate.of(2026, 3, 10));
            categoria3 = categoriaRepository.save(categoria3);
        
        }
        
        List<Categoria> categoriasBase = categoriaRepository.findAll();
        if (productoRepository.count() == 0) {
            Producto producto1 = new Producto();
            producto1.setNombre("nombre 1");
            producto1.setDescripcion("descripcion 1");
            producto1.setSku("SKU-001");
            producto1.setPrecio(new BigDecimal("1000.00"));
            producto1.setStock(10);
            producto1.setActivo(true);
            producto1.setFechaIngreso(LocalDate.of(2026, 1, 10));
            producto1.setCategoria(categoriasBase.get((1 - 1) % categoriasBase.size()));
            producto1 = productoRepository.save(producto1);
        
            Producto producto2 = new Producto();
            producto2.setNombre("nombre 2");
            producto2.setDescripcion("descripcion 2");
            producto2.setSku("SKU-002");
            producto2.setPrecio(new BigDecimal("2000.00"));
            producto2.setStock(20);
            producto2.setActivo(false);
            producto2.setFechaIngreso(LocalDate.of(2026, 2, 10));
            producto2.setCategoria(categoriasBase.get((2 - 1) % categoriasBase.size()));
            producto2 = productoRepository.save(producto2);
        
            Producto producto3 = new Producto();
            producto3.setNombre("nombre 3");
            producto3.setDescripcion("descripcion 3");
            producto3.setSku("SKU-003");
            producto3.setPrecio(new BigDecimal("3000.00"));
            producto3.setStock(30);
            producto3.setActivo(true);
            producto3.setFechaIngreso(LocalDate.of(2026, 3, 10));
            producto3.setCategoria(categoriasBase.get((3 - 1) % categoriasBase.size()));
            producto3 = productoRepository.save(producto3);
        
        }
        
    }
}
