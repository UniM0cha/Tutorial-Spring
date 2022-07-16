package com.springboot.rest.service;

import java.lang.reflect.Member;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.rest.dto.MemberDto;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {

  // Get 요청
  public String getName() {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:9090")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    return webClient.get()
        .uri("/api/v1/crud-api")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

  public String getNameWithPathVariable() {
    WebClient webClient = WebClient.create("http://localhost:9090");

    ResponseEntity<String> responseEntity = webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/{name}").build("solstice"))
        .retrieve()
        .toEntity(String.class)
        .block();

    return responseEntity.getBody();
  }

  public String getNameWithPArameter() {
    WebClient webClient = WebClient.create("http://localhost:9090");

    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api").queryParam("name", "solstice").build())
        .exchangeToMono(clientResponse -> {
          if (clientResponse.statusCode().equals(HttpStatus.OK)) {
            return clientResponse.bodyToMono(String.class);
          } else {
            return clientResponse.createException().flatMap(Mono::error);
          }
        })
        .block();
  }

  // Post 요청
  public ResponseEntity<MemberDto> postWithParamAndBody() {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:9090")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    MemberDto memberDto = new MemberDto();
    memberDto.setName("solstice");
    memberDto.setEmail("solst_ice@naver.com");
    memberDto.setOrganization("아 이게 뭐임");

    return webClient.post()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
            .queryParam("name", "solstice")
            .queryParam("email", "solst_ice@naver.com")
            .queryParam("organization", "wikibooks")
            .build())
        .bodyValue(memberDto)
        .retrieve()
        .toEntity(MemberDto.class)
        .block();
  }

  public ResponseEntity<MemberDto> postWithHeader() {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:9090")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    MemberDto memberDto = new MemberDto();
    memberDto.setName("solstice");
    memberDto.setEmail("solst_ice@naver.com");
    memberDto.setOrganization("아 이게 뭐임");

    return webClient.post()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/add-header").build())
        .bodyValue(memberDto)
        .header("my-header", "wikibooks api")
        .retrieve()
        .toEntity(MemberDto.class)
        .block();
  }
}
