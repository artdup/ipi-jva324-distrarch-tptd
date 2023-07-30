package com.ipi.jva324.commande;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.stock.model.ProduitEnStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class CommandeServiceIntegrationTest {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createCommandeTest() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(testRestTemplate.getRestTemplate());

        ProduitEnStock produit = new ProduitEnStock();
        produit.setId(1L);
        produit.setNom("Produit Test");
        produit.setQuantiteDisponible(100);

        mockServer.expect(requestTo("http://localhost:8080/api/produits/1"))
                .andRespond(withSuccess(testRestTemplate.getObjectMapper().writeValueAsString(produit), MediaType.APPLICATION_JSON));

        Commande commande = commandeService.createCommande(1L, 50);

        assertEquals(50, commande.getQuantite());
        mockServer.verify();
    }
}
