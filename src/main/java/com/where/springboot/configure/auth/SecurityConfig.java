package com.where.springboot.configure.auth;

import com.where.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}

/*
    @EnableWebSecurity : Spring Security 설정들을 활성화 시켜준다.
    .csrf().disable().headers().frameOptions().disable() : h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
    .authorizeRequests() : URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야 antMatchers를 사용할 수 있다.
    .anyRequest() : 설정된 값들 이외 나머지 URL들을 나타낸다, 현재설정으로는 authenticated() 가 붙어있기 때문에 나머지 URL들은 모두 인증된 사용자 즉, 로그인한 사람만 사용 가능해진다.
    .logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공시 / 주소로 이동한다.
    .oauth2Login() : OAuth2 로그인 기능에 대한 여러 설정의 진입점
    .userInfoEndpoint() : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당한다.
    .userService() : 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다, 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
*/