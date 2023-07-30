package com.ipi.jva324.commande;

import com.ipi.jva324.stock.model.ProduitEnStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpMethod;

@SpringBootTest
public class CommandeProduitServiceRestHalImplIntegrationTest {

    @Autowired
    private CommandeProduitServiceRESTHALImpl commandeProduitService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetProduit() {
        // Produit d'exemple à renvoyer
        String produitJson = "{\"id\": 1,\"name\": \"produit test\",\"description\": \"description test\"}";
        
        mockServer.expect(requestTo("http://localhost:8080/api/data-rest/produitsenstocks/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(produitJson, MediaType.APPLICATION_JSON));

        ProduitEnStock produit = commandeProduitService.getProduit(1L);

        // Vérification des données du produit retourné
        assertEquals(1L, produit.getId());
        assertEquals("produit test", produit.getName());
        assertEquals("description test", produit.getDescription());

        // Vérification que tous les scénarios attendus ont été appelés
        mockServer.verify();
    }
}
