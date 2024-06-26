package com.cos.security1.config.oauth;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.NaverUserInfo;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.config.oauth.provider.OAuth2UserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;

  // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
  // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
  @Override
  @SuppressWarnings("unchecked")
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴(OAuth-Client라이브러리) ->
    // AccessToken 요청
    // -> userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아옴.
    System.out.println("getClientRegistration:" + userRequest.getClientRegistration());
    System.out.println("getAccessToken:" + userRequest.getAccessToken());

    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println("getAttributes:" + oAuth2User.getAttributes());

    OAuth2UserInfo oAuth2UserInfo = null;
    if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
      System.out.println("구글 로그인 요청");
      oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
    } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
      System.out.println("페이스북 로그인 요청");
      oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
    } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
      System.out.println("네이버 로그인 요청");
      // unchecked 경고 발생
      oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
    } else {
      System.out.println("구글, 페이스북, 네이버만 지원함");
    }

    String provider = oAuth2UserInfo.getProvider(); // google
    String providerId = oAuth2UserInfo.getProviderId();
    String username = provider + "_" + providerId; // google_2918573298755...
    String password = bCryptPasswordEncoder.encode("겟인데어"); // 필요 없지만 만들어준다.
    String email = oAuth2UserInfo.getEmail();
    String role = "ROLE_USER";

    User userEntity = userRepository.findByUsername(username);

    if (userEntity == null) {
      System.out.println("최초 로그인입니다.");
      userEntity = User.builder()
          .username(username)
          .password(password)
          .email(email)
          .role(role)
          .provider(provider)
          .providerId(providerId)
          .build();
      userRepository.save(userEntity);
    }

    // Authentication 객체에 들어갈 것이다.
    return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
  }
}