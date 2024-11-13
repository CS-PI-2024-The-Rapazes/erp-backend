package org.therapazes.luisaoproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTests {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProdutoById_ReturnsProduto_WhenProdutoExists() {
        Produto produto = new Produto();
        produto.setIdProduto(1);
        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));

        Produto result = produtoService.getProdutoById(1);

        assertEquals(produto, result);
    }

    @Test
    void getProdutoById_ThrowsEntityNotFoundException_WhenProdutoDoesNotExist() {
        when(produtoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoService.getProdutoById(1));
    }

    @Test
    void getAllProduto_ReturnsPageOfProdutos() {
        Page<Produto> page = new PageImpl<>(Collections.emptyList());
        when(produtoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Produto> result = produtoService.getAllProduto(PageRequest.of(0, 10));

        assertEquals(page, result);
    }

    @Test
    void save_SavesAndReturnsProduto() {
        Produto produto = new Produto();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto result = produtoService.save(produto);

        assertNotNull(result.getDataCadastro());
        assertTrue(result.getStatus());
        assertEquals(produto, result);
    }

    @Test
    void deleteById_DeletesProduto_WhenProdutoExists() {
        when(produtoRepository.existsById(1)).thenReturn(true);

        produtoService.deleteById(1);

        verify(produtoRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteById_ThrowsEntityNotFoundException_WhenProdutoDoesNotExist() {
        when(produtoRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> produtoService.deleteById(1));
    }

    @Test
    void updateProduto_UpdatesAndReturnsProduto_WhenProdutoExists() {
        Produto produto = new Produto();
        produto.setIdProduto(1);
        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto result = produtoService.updateProduto(produto);

        assertEquals(produto, result);
    }

    @Test
    void updateProduto_ReturnsNull_WhenProdutoDoesNotExist() {
        Produto produto = new Produto();
        produto.setIdProduto(1);
        when(produtoRepository.findById(1)).thenReturn(Optional.empty());

        Produto result = produtoService.updateProduto(produto);

        assertNull(result);
    }

    @Test
    void updateStatus_TogglesStatusAndReturnsUpdatedProduto() {
        Produto produto = new Produto();
        produto.setIdProduto(1);
        produto.setStatus(true);
        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto result = produtoService.updateStatus(1);

        assertFalse(result.getStatus());
    }

    @Test
    void updateStatus_ThrowsRuntimeException_WhenProdutoDoesNotExist() {
        when(produtoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> produtoService.updateStatus(1));
    }
}