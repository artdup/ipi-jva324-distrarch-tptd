package com.ipi.jva324.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ipi.jva324.stock.model.ProduitEnStock;

@Component
@Primary
public class CommandeProduitLocalImpl implements CommandeProduit {

    private final ProduitService produitService;

    @Autowired
    public CommandeProduitLocalImpl(ProduitService produitService) {
        this.produitService = produitService;
    }

    @Override
    public ProduitEnStock getProduit() {
        // Implémentez la logique pour obtenir le produit en utilisant ProduitService
        return produitService.getProduit(0);
        return RestTemplate.getForObject(url, ProduitEnStock.class, produitId);
    }
}
