package com.ipi.jva324.commande.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.CommandeProduit;

@Component
public class CommandeProduitRESTHALImpl implements CommandeProduit {

    @Value("${stock.api.baseurl:http://localhost:8080}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public CommandeProduitRESTHALImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProduitEnStock getProduit() {
        // utilisez baseUrl à la place de l'URL codée en dur
        String url = baseUrl + "/stock/produits/{id}";
        Long produitId = (long) 123;
        ProduitEnStock produitEnStock = restTemplate.getForObject(url, ProduitEnStock.class, produitId);
        return produitEnStock;
    }
}