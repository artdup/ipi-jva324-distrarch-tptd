package com.ipi.jva324.commande;

import com.ipi.jva324.commande.service.CommandeProduitServiceRestImpl;
import com.ipi.jva324.stock.model.ProduitEnStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class CommandeProduitServiceRestImplIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CommandeProduitServiceRestImpl commandeProduitService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getProduitTest() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(testRestTemplate.getRestTemplate());

        ProduitEnStock produit = new ProduitEnStock();
        produit.setId(1L);
        produit.setNom("Produit Test");
        produit.setQuantiteDisponible(100);

        mockServer.expect(requestTo("http://localhost:8080/api/produits/1"))
                .andRespond(withSuccess(testRestTemplate.getObjectMapper().writeValueAsString(produit), MediaType.APPLICATION_JSON));

        ProduitEnStock produitRetourne = commandeProduitService.getProduit(1L);

        assertNotNull(produitRetourne);
        mockServer.verify();
    }
}
