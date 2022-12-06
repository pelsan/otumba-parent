/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.otumba.customer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.otumba.customer.back.DemoProperties;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.BodyInserters;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author pelsa
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private final WebClient.Builder webClientBuilder;

    private final DemoProperties properties;

    public CustomerRestController(DemoProperties properties, WebClient.Builder webClientBuilder) {
        this.properties = properties;
        this.webClientBuilder = webClientBuilder;
    }

    //webClient requires HttpClient library to work propertly       
    HttpClient client = HttpClient.create()
            //Connection Timeout: is a period within which a connection between a client and a server must be established
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            //Response Timeout: The maximun time we wait to receive a response after sending a request
            .responseTimeout(Duration.ofSeconds(1))
            // Read and Write Timeout: A read timeout occurs when no data was read within a certain 
            //period of time, while the write timeout when a write operation cannot finish at a specific time
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    @GetMapping("/hello")
    public String hello() {
        System.out.println("DB URL: " + properties.getUrl());
        System.out.println("DB Username: " + properties.getUsername());
        System.out.println("DB Password: " + properties.getPassword());
        System.out.println("service check: " + properties.getServicecheck());

        return "Hello World!";
    }

    @GetMapping("/check")
    public String check(@RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(properties.getServicecheck())
                // Setting headers
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", properties.getServicecheck()))
                .build();

        // Setting call get product by id
        JsonNode block = build.method(HttpMethod.GET).uri("/1")
                .retrieve().bodyToMono(JsonNode.class).block();

        System.out.println("json" + block.toPrettyString());
        System.out.println("parameter hello:" + block.get("name").asText());
        return block.toPrettyString();
    }

    @GetMapping("/preparecheck")
    public String preparecheck(@RequestHeader Map<String, String> headers) {

        Map<String, String> bodyMap = new HashMap();
        bodyMap.put("name", "Product 000");
        bodyMap.put("code", "000");

        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(properties.getServicecheck())
                // Setting headers
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", properties.getServicecheck()))
                .build();

        // Setting call get product by id
        JsonNode block = build.method(HttpMethod.POST).uri("")
                .body(BodyInserters.fromValue(bodyMap))
                .retrieve().bodyToMono(JsonNode.class).block();

        System.out.println("json" + block.toPrettyString());
        System.out.println("parameter hello:" + block.get("name").asText());
        return block.toPrettyString();
    }

}
