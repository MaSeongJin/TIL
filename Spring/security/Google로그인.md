## OAuth2 설정 추가

1. **application 설정 파일 작성**:

    - `application-oauth.yml` 또는 `application-oauth.properties` 파일을 생성

    ### application-oauth.yml

    ```yaml
    spring:
      security:
        oauth2:
          client:
            registration:
              google:
                client-id: Your Client ID
                client-secret: Your Client Secure Password
                scope: profile,email
    ```

    ### application-oauth.properties

    ```properties
    spring.security.oauth2.client.registration.google.client-id=Your Client ID
    spring.security.oauth2.client.registration.google.client-secret=Your Client Secure Password
    spring.security.oauth2.client.registration.google.scope=profile,email
    ```

2. **application 설정 파일에 추가**:

    - 기존의 `application.yml` 또는 `application.properties` 파일에 다음 내용을 추가

    ### application.yml

    ```yaml
    spring:
      profiles:
        include: oauth
    ```

    ### application.properties

    ```properties
    spring.profiles.include=oauth
    ```

## 패키지 및 클래스 생성

1. **config.auth.dto 패키지 추가**:
   
   - `config.auth.dto` 패키지를 생성

2. **OAuthAttributes와 SessionUser 클래스 생성**:

    - `config.auth.dto` 안에 `OAuthAttributes`와 `SessionUser` 클래스를 생성

    ### OAuthAttributes.java

    ```java
    package com.example.domain.config.auth.dto;

    import com.example.domain.web.user.entities.User;
    import com.example.domain.web.user.enums.Role;
    import lombok.Builder;
    import lombok.Getter;

    import java.util.Map;

    @Getter
    public class OAuthAttributes {
        private Map<String, Object> attributes;
        private String nameAttributeKey;
        private String name;
        private String email;
        private String picture;
        
        @Builder
        public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
            this.attributes = attributes;
            this.nameAttributeKey = nameAttributeKey;
            this.name = name;
            this.email = email;
            this.picture = picture;
        }
        
        public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
            return ofGoogle(userNameAttributeName, attributes);
        }
        
        private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
            return OAuthAttributes.builder()
                    .name((String) attributes.get("name"))
                    .email((String) attributes.get("email"))
                    .picture((String) attributes.get("picture"))
                    .attributes(attributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();
        }
        
        public User toEntity() {
            return User.builder()
                    .name(name)
                    .email(email)
                    .picture(picture)
                    .role(Role.GUEST)
                    .build();
        }
    }
    ```

    ### SessionUser.java

    ```java
    package com.example.domain.config.auth.dto;

    import com.example.domain.entities.User;
    import lombok.Getter;

    import java.io.Serializable;

    @Getter
    public class SessionUser implements Serializable {
        private String name;
        private String email;
        private String picture;
        
        public SessionUser(User user) {
            this.name = user.getName();
            this.email = user.getEmail();
            this.picture = user.getPicture();
        }
    }
    ```

3. **config.auth 패키지 추가**:
   
   - `config.auth` 패키지를 생성

4. **CustomOAuth2UserService와 SecurityConfig 클래스 생성**:

    - `config.auth` 안에 `CustomOAuth2UserService`와 `SecurityConfig` 클래스를 생성

    ### CustomOAuth2UserService.java

    ```java
    package com.example.domain.config.auth;

    import com.example.domain.config.auth.dto.OAuthAttributes;
    import com.example.domain.config.auth.dto.SessionUser;
    import com.example.domain.web.user.entities.User;
    import com.example.domain.web.user.repositories.UserRepository;
    import jakarta.servlet.http.HttpSession;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
    import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
    import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
    import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
    import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
    import org.springframework.security.oauth2.core.user.OAuth2User;
    import org.springframework.stereotype.Service;

    import java.util.Collections;

    @Service
    @RequiredArgsConstructor
    public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        private final UserRepository userRepository;
        private final HttpSession httpSession;
        
        @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
            
            OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
            
            User user = saveOrUpdate(attributes);
            
            httpSession.setAttribute("user", new SessionUser(user));
            
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(
                            user.getRoleKey())),
                            attributes.getAttributes(),
                            attributes.getNameAttributeKey()
            );
        }
        
        private User saveOrUpdate(OAuthAttributes attributes) {
            User user = userRepository.findByEmail(attributes.getEmail())
                    .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                    .orElse(attributes.toEntity());
            
            return userRepository.save(user);
        }
    }
    ```

    ### SecurityConfig.java

    ```java
    package com.example.domain.config.auth;

    import com.example.domain.web.user.enums.Role;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {
        @Autowired
        private final CustomOAuth2UserService customOAuth2UserService;
        
        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
            
            return http.build();
        }
    }
    ```

## User 엔티티 및 Repository 생성

1. **user 패키지 추가**:

    - 프로젝트 패키지 아래에 `user` 패키지를 생성

2. **User 클래스 생성**:

    - `user.entities` 패키지 안에 `User` 클래스를 생성

    ### User.java

    ```java
    package com.example.domain.web.user.entities;

    import com.example.domain.web.entities.BaseTimeEntity;
    import com.example.domain.web.user.enums.Role;
    import jakarta.persistence.*;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    @Entity
    @Table(name = "users")
    public class User extends BaseTimeEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false)
        private String name;
        
        @Column(nullable = false)
        private String email;
        
        @Column
        private String picture;
        
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;
        
        @Builder
        public User(String name, String email, String picture, Role role) {
            this.name = name;
            this.email = email;
            this.picture = picture;
            this.role = role;
        }
        
        public User update(String name, String picture) {
            this.name = name;
            this.picture = picture;
            return this;
        }
        
        public String getRoleKey() {
            return this.role.getKey();
        }
    }
    ```

    ### Role.java

    ```java
    package com.example.domain.web.user.enums;

    import lombok.Getter;
    import lombok.RequiredArgsConstructor;

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        GUEST("ROLE_GUEST", "Guest"),
        USER("ROLE_USER", "Common User");
        
        private final String key;
        private final String title;
    }
    ```

    ### UserRepository.java

    ```java
    package com.example.domain.web.user.repositories;

    import com.example.domain.web.user.entities.User;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.Optional;

    public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
    }
    ```

## BaseEntity 추상 클래스 생성

1. **entities 패키지 추가**:

    - 프로젝트 패키지 아래에 `entities` 패키지를 생성

2. **BaseTimeEntity 추상 클래스 생성**:

    - `entities` 패키지 안에 `BaseTimeEntity` 추상 클래스를 생성

    ### BaseTimeEntity.java

    ```java
    package com.example.domain.web.entities;

    import jakarta.persistence.EntityListeners;
    import jakarta.persistence.MappedSuperclass;
    import lombok.Getter;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;
    import java.time.LocalDateTime;

    @Getter
    @MappedSuperclass
    @EntityListeners(AuditingEntityListener.class)
    public abstract class BaseTimeEntity {
        @CreatedDate
        private LocalDateTime createdDate;

        @LastModifiedDate
        private LocalDateTime modifiedDate;
    }
    ```

## Application 클래스 수정

1. **Application 클래스에 @EnableJpaAuditing 추가**:

    - `SpringStudyApplication` 클래스에 `@EnableJpaAuditing` 어노테이션을 추가합니다.

    ```java
    package com.example.domain;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

    @EnableJpaAuditing
    @SpringBootApplication
    public class SpringStudyApplication {
        public static void main(String[] args) {
            SpringApplication.run(SpringStudyApplication.class, args);
        }
    }
    ```
