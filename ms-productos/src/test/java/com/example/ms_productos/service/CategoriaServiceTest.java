package com.example.ms_productos.service;

import com.example.ms_productos.dto.request.CategoriaRequestDTO;
import com.example.ms_productos.dto.response.CategoriaResponseDTO;
import com.example.ms_productos.exception.ResourceNotFoundException;
import com.example.ms_productos.mapper.CategoriaMapper;
import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de la capa Service de Categoria.
 * Se mockean Repository y Mapper para aislar la logica del Service.
 */
@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    @DisplayName("listarTodos debe retornar la lista de categorias convertidas a DTO")
    void listarTodos_debeRetornarListaDeCategorias() {
        // Given: el repositorio tiene una categoria guardada
        Categoria categoria = new Categoria();
        categoria.setId(1);
        CategoriaResponseDTO dtoEsperado = new CategoriaResponseDTO();
        dtoEsperado.setId(1);

        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(categoriaMapper.toResponseDTO(categoria)).thenReturn(dtoEsperado);

        // When: se listan todas las categorias
        List<CategoriaResponseDTO> resultado = categoriaService.listarTodos();

        // Then: se obtiene la lista con el DTO mapeado
        assertEquals(1, resultado.size());
        assertEquals(dtoEsperado, resultado.get(0));
    }

    @Test
    @DisplayName("buscarPorId debe retornar la categoria cuando existe")
    void buscarPorId_debeRetornarCategoria_cuandoExiste() {
        // Given: la categoria con ID 1 existe en el repositorio
        Categoria categoria = new Categoria();
        categoria.setId(1);
        CategoriaResponseDTO dtoEsperado = new CategoriaResponseDTO();
        dtoEsperado.setId(1);

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toResponseDTO(categoria)).thenReturn(dtoEsperado);

        // When: se busca la categoria por ID
        CategoriaResponseDTO resultado = categoriaService.buscarPorId(1);

        // Then: se retorna el DTO correspondiente
        assertEquals(dtoEsperado, resultado);
    }

    @Test
    @DisplayName("buscarPorId debe lanzar ResourceNotFoundException cuando la categoria no existe")
    void buscarPorId_debeLanzarExcepcion_cuandoNoExiste() {
        // Given: no existe ninguna categoria con ID 99
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion esperada
        assertThrows(ResourceNotFoundException.class, () -> categoriaService.buscarPorId(99));
    }

    @Test
    @DisplayName("guardar debe crear y retornar la categoria")
    void guardar_debeCrearCategoria() {
        // Given: un request valido de categoria
        CategoriaRequestDTO request = new CategoriaRequestDTO();
        request.setNombre("Electronica");

        Categoria categoriaSinGuardar = new Categoria();
        Categoria categoriaGuardada = new Categoria();
        categoriaGuardada.setId(1);

        CategoriaResponseDTO dtoEsperado = new CategoriaResponseDTO();
        dtoEsperado.setId(1);

        when(categoriaMapper.toEntity(request)).thenReturn(categoriaSinGuardar);
        when(categoriaRepository.save(categoriaSinGuardar)).thenReturn(categoriaGuardada);
        when(categoriaMapper.toResponseDTO(categoriaGuardada)).thenReturn(dtoEsperado);

        // When: se guarda la categoria
        CategoriaResponseDTO resultado = categoriaService.guardar(request);

        // Then: se retorna el DTO de la categoria guardada
        assertEquals(dtoEsperado, resultado);
        verify(categoriaRepository).save(categoriaSinGuardar);
    }

    @Test
    @DisplayName("actualizar debe modificar y retornar la categoria cuando existe")
    void actualizar_debeModificarCategoria_cuandoExiste() {
        // Given: una categoria existente y nuevos datos para actualizar
        Categoria categoria = new Categoria();
        categoria.setId(1);

        CategoriaRequestDTO request = new CategoriaRequestDTO();
        request.setNombre("Hogar");
        request.setDescripcion("Articulos para el hogar");
        request.setCodigoAlterno("CAT-HOG-99");
        request.setPrioridadVisualizacion(5);
        request.setActivo(true);
        request.setFechaCreacion(LocalDate.now());

        CategoriaResponseDTO dtoEsperado = new CategoriaResponseDTO();
        dtoEsperado.setId(1);

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toResponseDTO(categoria)).thenReturn(dtoEsperado);

        // When: se actualiza la categoria
        CategoriaResponseDTO resultado = categoriaService.actualizar(1, request);

        // Then: los campos quedan actualizados y se retorna el DTO
        assertEquals(dtoEsperado, resultado);
        assertEquals("Hogar", categoria.getNombre());
        assertEquals("CAT-HOG-99", categoria.getCodigoAlterno());
    }

    @Test
    @DisplayName("eliminar debe borrar la categoria cuando existe")
    void eliminar_debeBorrarCategoria_cuandoExiste() {
        // Given: la categoria con ID 1 existe
        Categoria categoria = new Categoria();
        categoria.setId(1);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        // When: se elimina la categoria
        categoriaService.eliminar(1);

        // Then: se llama a delete con la categoria encontrada
        verify(categoriaRepository).delete(categoria);
    }

    @Test
    @DisplayName("eliminar debe lanzar ResourceNotFoundException cuando la categoria no existe")
    void eliminar_debeLanzarExcepcion_cuandoNoExiste() {
        // Given: no existe la categoria con ID 99
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion esperada
        assertThrows(ResourceNotFoundException.class, () -> categoriaService.eliminar(99));
    }
}
