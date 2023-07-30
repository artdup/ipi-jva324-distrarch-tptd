package com.ipi.jva324.commande.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.CommandeProduit;

@Component
public class CommandeProduitRESTHALImpl implements CommandeProduit {

    @Value("${stock.apiUrl:http://localhost:8080/api/data-rest/produitsenstocks}")
    private String stockApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public CommandeProduitRESTHALImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProduitEnStock getProduit() {
        // Implémentez la logique pour appeler l'API Spring Data HAL du microservice
        // "stock" avec RESTTemplate
        // Utilisez restTemplate.getForObject() pour effectuer la requête GET et
        // récupérer les données du produit
        String url = "http://localhost:8080/stock/produits/{id}"; // Remplacez l'URL avec l'URL réelle du microservice
                                                                  // "stock"
        Long produitId = (long) 123; // Remplacez par l'ID du produit requis
        ProduitEnStock produitEnStock = restTemplate.getForObject(url, ProduitEnStock.class, produitId);
        return produitEnStock;
    }
}