package great.dog.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebAccessDeniedHandler webAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(WebAccessDeniedHandler webAccessDeniedHandler) {
        this.webAccessDeniedHandler = webAccessDeniedHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/api-docs/**", "/resource/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/v1", "/v1/**").access("hasRole('ROLE_ADMIN')").
                anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/v1").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout().permitAll()
                .and()
                    .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling().accessDeniedHandler(webAccessDeniedHandler)
                .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

}
