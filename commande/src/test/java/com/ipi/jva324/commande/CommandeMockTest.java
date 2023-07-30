package com.ipi.jva324.commande;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.service.CommandeInvalideException;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.commande.service.StockInsuffisantCommandeException;
import com.ipi.jva324.stock.model.ProduitEnStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandeMockTest {

    @Mock
    private CommandeService commandeService;

    @Test
    public void testCreateCommande() throws StockInsuffisantCommandeException, CommandeInvalideException {
        ProduitEnStock p1 = new ProduitEnStock();
        p1.setId(1L);
        Commande c1 = new Commande(p1.getId(), 1L);

        when(commandeService.createCommande(c1)).thenReturn(c1);

        Commande created = commandeService.createCommande(c1);

        Assertions.assertNotNull(created);
        Assertions.assertEquals(c1, created);
    }
}
