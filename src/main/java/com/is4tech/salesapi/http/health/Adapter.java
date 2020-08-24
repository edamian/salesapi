package com.is4tech.salesapi.http.health;

import com.is4tech.salesapi.dto.ProductDTO;
import com.is4tech.salesapi.services.HealthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Adapter implements HealthServices {

    private static final String MICRO_SERVICE_0 = "http://salesapi0/api/status";
    private final RestTemplate restTemplate;

    public Adapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isOk() {
        ResponseEntity<ProductDTO[]> exchange = restTemplate.getForEntity(
                MICRO_SERVICE_0,
                ProductDTO[].class);
        return exchange.getStatusCode().is2xxSuccessful();
    }
}
