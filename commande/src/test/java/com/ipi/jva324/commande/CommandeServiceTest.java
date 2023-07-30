package com.ipi.jva324.commande;


import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.repository.CommandeRepository;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.CommandeProduit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class CommandeServiceTest {
    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private CommandeProduit commandeProduit;

    @InjectMocks
    private CommandeService commandeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCommande() {
        Commande commande = new Commande();
        ProduitEnStock produit = new ProduitEnStock();
        produit.setQuantiteDisponible(100L);
        when(commandeProduit.getProduit()).thenReturn(produit);
        when(commandeRepository.save(commande)).thenReturn(commande);

        Commande createdCommande = commandeService.createCommande(commande);

        verify(commandeProduit).getProduit();
        verify(commandeRepository).save(commande);
        assertSame(createdCommande, commande);
        assertEquals("created", createdCommande.getStatus());
        assertEquals(produit.getQuantiteDisponible(), createdCommande.getQuantiteDisponibleStockConnu());
    }
}
