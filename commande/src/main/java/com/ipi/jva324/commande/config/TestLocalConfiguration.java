package com.ipi.jva324.commande.config;

import com.ipi.jva324.commande.service.CommandeProduitServiceLocalImpl;
import com.ipi.jva324.stock.service.ProduitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestLocalConfiguration {

    @Bean
    public CommandeProduitServiceLocalImpl commandeProduitServiceLocalImpl(ProduitService produitService) {
        return new CommandeProduitServiceLocalImpl(produitService);
    }
}
