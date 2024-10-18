package com.graphqljava.project.websocket_authentication;


import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class GreetingController {

    @QueryMapping
    String greeting(Authentication authentication){
        return "Hello " + authentication.getName() + "!";
    }

    Flux<String> greetings(Authentication authentication){
        return Flux.interval(Duration.ofMillis(50))
                .map((num) -> "Hello " + authentication.getName() + num + "!");
    }

}
