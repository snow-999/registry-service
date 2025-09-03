package com.example.registry_service.client;

import com.example.registry_service.dto.TokenModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
// security-service
@FeignClient(name = "SECURITY-SERVICE", url = "http://localhost:9000")
public interface AuthClient {
    @PostMapping(value = "/oauth2/token")
    TokenModel getToken(@RequestBody Map<String, ?> formParams);
}
