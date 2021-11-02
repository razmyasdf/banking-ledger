package com.nimi.ledgerservice.config;

import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class KeycloakRestTemplateBean {
    @Autowired
    public KeycloakClientRequestFactory keycloakClientRequestFactory;

    @Bean
    @LoadBalanced
    public KeycloakRestTemplate keycloakRestTemplate(){
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }
}
