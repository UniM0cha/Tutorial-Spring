package com.springboot.actuator.config.actuator;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;

@Component
public class CustomInfoContributer implements InfoContributor {

  @Override
  public void contribute(Builder builder) {
    Map<String, Object> content = new HashMap<>();
    content.put("code-info", "InfoContributor 구현체에서 정의한 정보입니다.");
    builder.withDetail("custom-info-contributer", content);
  }

}
