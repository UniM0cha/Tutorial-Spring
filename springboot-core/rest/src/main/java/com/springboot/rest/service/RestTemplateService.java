package com.springboot.rest.service;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.dto.MemberDto;

@Service
public class RestTemplateService {

  // Get 형식의 RestTemplate
  public String getName() {
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/api/v1/crud-api")
        .encode()
        .build()
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    return responseEntity.getBody();
  }

  public String getNameWithPathVariable() {
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/api/v1/crud-api/{name}")
        .encode()
        .build()
        .expand("solstice") // 복수의 값을 넣어야 할 경우 , 를 추가하여 구분
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    return responseEntity.getBody();
  }

  public String getNameWithParameter() {
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/api/v1/crud-api/param")
        .queryParam("name", "solstice")
        .encode()
        .build()
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    return responseEntity.getBody();
  }

  // Post 형식의 RestTemplate
  public ResponseEntity<MemberDto> postWithParamAndBody() {
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/api/v1/crud-api")
        .queryParam("name", "solstice")
        .queryParam("email", "solst_ice@naver.com")
        .queryParam("organization", "wikibooks")
        .encode()
        .build()
        .toUri();

    MemberDto memberDto = new MemberDto();
    memberDto.setName("solstice");
    memberDto.setEmail("solst_ice@naver.com");
    memberDto.setOrganization("ksjdfksjfdklsf");

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);

    return responseEntity;
  }

  public ResponseEntity<MemberDto> postWithHeader() {
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/api/v1/crud-api/add-header")
        .encode()
        .build()
        .toUri();

    MemberDto memberDto = new MemberDto();
    memberDto.setName("solstice");
    memberDto.setEmail("solst_ice@naver.com");
    memberDto.setOrganization("ksjdfksjfdklsf");

    RequestEntity<MemberDto> requestEntity = RequestEntity
        .post(uri)
        .header("my-header", "wikibooks api")
        .body(memberDto);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);

    return responseEntity;
  }

  public RestTemplate restTemplate() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

    HttpClient client = HttpClientBuilder.create()
        .setMaxConnTotal(500)
        .setMaxConnPerRoute(500)
        .build();

    CloseableHttpClient httpClient = HttpClients.custom()
        .setMaxConnTotal(500)
        .setMaxConnPerRoute(500)
        .build();

    factory.setHttpClient(httpClient);
    factory.setConnectTimeout(2000);
    factory.setReadTimeout(5000);

    RestTemplate restTemplate = new RestTemplate(factory);

    return restTemplate;
  }

}
