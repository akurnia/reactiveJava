package com.mentoring.parallelism.mentoring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class MentoringController {
    WebClient webClient = WebClient.create("http://localhost:8080");

    private Mono<String> getCustomerById(String customerId){
        return null;
    }
}
