package com.graphqljava.project.websocket_authentication;

import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.web.reactive.socket.client.ReactorNetty2WebSocketClient;

import java.net.URI;

public class GreetingClient {

    public static void main(String[] args) {

        WebSocketGraphQlClient graphQlClient = WebSocketGraphQlClient
                .builder(URI.create("ws://localhost:8080/graphql"), new ReactorNetty2WebSocketClient())
                .interceptors(JwtGraphQlClientInterceptor.create())
                .build();

        graphQlClient.document("subscription {greetings}")
                .retrieveSubscription("greetings")
                .toEntity(String.class)
                .take(5)
                .doOnNext(System.out::println)
                .blockLast();
    }
}
