package com.springboot.test.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.google.gson.Gson;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.impl.ProductServiceImpl;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductServiceImpl productService;

  @Test
  @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
  void testGetProduct() throws Exception {
    given(productService.getProduct(123L)).willReturn(new ProductResponseDto(123L, "pen", 5000, 2000));

    String productId = "123";

    mockMvc.perform(get("/product?number=" + productId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.number").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.price").exists())
        .andExpect(jsonPath("$.stock").exists())
        .andDo(print());

    verify(productService).getProduct(123L);
  }

  @Test
  @DisplayName("Product 데이터 생성 테스트")
  void createProductTest() throws Exception {
    // Mock 객체에서 특정 메서드가 실행되는 경우 실제 return을 줄 수 없기 때문에 아래와 같이 만들어준다.
    given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
        .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

    ProductDto productDto = ProductDto.builder()
        .name("pen")
        .price(5000)
        .stock(2000)
        .build();

    Gson gson = new Gson();
    String content = gson.toJson(productDto);

    mockMvc.perform(post("/product").content(content).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.number").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.price").exists())
        .andExpect(jsonPath("$.stock").exists())
        .andDo(print());

    verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
  }
}
