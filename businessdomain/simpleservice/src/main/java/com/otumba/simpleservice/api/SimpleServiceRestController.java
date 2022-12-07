package com.otumba.simpleservice.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.otumba.simpleservice.back.DemoProperties;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author pelsan
 */
@RestController
@RequestMapping("/simpleservice")
public class SimpleServiceRestController {
    
     @Autowired
    private final WebClient.Builder webClientBuilder;

    private final DemoProperties properties;

    public SimpleServiceRestController(DemoProperties properties, WebClient.Builder webClientBuilder) {
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
    
    
        @GetMapping("/check")
    public String check(@RequestHeader Map<String, String> headers) {

        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(properties.getServicecheck())
                // Setting headers
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                
                
                .defaultUriVariables(Collections.singletonMap("url", properties.getServicecheck()))
                .build();

        // Setting call get product by id
        JsonNode block = build.method(HttpMethod.GET).uri("/")
                      .headers(
                        httpHeaders -> {

                            httpHeaders.set("key-simple1", "value1");
                            httpHeaders.set("key-simple2", "value2");
                            headers.forEach((key, value) -> {
                                System.out.println(String.format("Simple Service Header '%s' = %s", key, value));
                                if (key.startsWith("x-")) {
                                    httpHeaders.set(key, value);
                                    System.out.println("added");
                                }
                            }
                            );
                        }
                )
                .retrieve().bodyToMono(JsonNode.class).block();

        System.out.println("json" + block.toPrettyString());
        System.out.println("parameter hello:" + block.get("hello").asText());
        return block.toPrettyString();
    }

}
