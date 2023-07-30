package com.ipi.jva324.stock.service;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.repository.ProduitEnStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {

    @Mock
    private ProduitEnStockRepository produitEnStockRepository;

    @InjectMocks
    private ProduitService produitService;

    private ProduitEnStock produit;

    @BeforeEach
    public void setUp() {
        produit = new ProduitEnStock();
        produit.setId(1L);
        produit.setQuantiteDisponible(10);
    }

    @Test
    public void testGetProduit() {
        when(produitEnStockRepository.findById(1L)).thenReturn(Optional.of(produit));

        ProduitEnStock foundProduit = produitService.getProduit(1L);

        assertEquals(foundProduit.getId(), produit.getId());
        assertEquals(foundProduit.getQuantiteDisponible(), produit.getQuantiteDisponible());
    }
}
