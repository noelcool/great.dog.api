package great.dog.api.config;

import great.dog.api.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityUserService securityUserService;
    private final WebAccessDeniedHandler webAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(SecurityUserService securityUserService, WebAccessDeniedHandler webAccessDeniedHandler) {
        this.securityUserService = securityUserService;
        this.webAccessDeniedHandler = webAccessDeniedHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/api-docs/**", "/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/", "/login", "/join", "/login/**").permitAll().
                antMatchers("/v1", "/v1/**", "/swagger-ui.html").access("hasRole('ROLE_VIEW')").
                anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/v1", true)
                    .usernameParameter("username").passwordParameter("password")
                .and()
                    .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling().accessDeniedHandler(webAccessDeniedHandler)
                .and()
                    .authenticationProvider(authenticationProvider())
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // 로그인 성공시 자동으로 입력된 비밀번호 값을 인코딩
    // userDetailSErvice를 설정한 경우 비밀번호 암호화를 반드시 설정해야 한다
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
