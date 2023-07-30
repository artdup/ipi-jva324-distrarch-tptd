package com.ipi.jva324.commande.service;

import com.ipi.jva324.stock.model.ProduitEnStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommandeProduitServiceRestImpl implements CommandeProduitService {

    @Value("${stock.api.url}")
    private String stockApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public CommandeProduitServiceRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProduitEnStock getProduit(Long id) {
        String url = stockApiUrl + "/api/produits/" + id;
        return restTemplate.getForObject(url, ProduitEnStock.class);
    }
}
