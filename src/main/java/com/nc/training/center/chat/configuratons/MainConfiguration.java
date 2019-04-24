package com.nc.training.center.chat.configuratons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * в этом пакете лежат java конфиги для спринга
 */
@Configuration
public class MainConfiguration extends WebSecurityConfigurerAdapter {
    private static final String MAIN_PAGE = "/";
    /**
     * Endpoint mapping for authentication by login.
     */
    private static final String AUTH_ENDPOINT = "/api/users/auth";
    /**
     * Endpoint mapping for authentication by login.
     */
    private static final String REGISTRATION_ENDPOINT = "/api/users/registration";
    /**
     * Endpoint mapping for authentication's cookie deleting.
     */
    private static final String LOGOUT_ENDPOINT = "/api/logout";
    /**
     * Configure endpoint's whitelist and logout logic.
     *
     * @param http http security object
     * @throws Exception if you is great
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN))
                .and()
                .authorizeRequests()
                .regexMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .antMatchers(MAIN_PAGE).permitAll()
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers("/h2-console").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .logout()
                .logoutUrl(LOGOUT_ENDPOINT)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true);
    }

    /**
     * CORS configuration bean. Info can be fetched by OPTION pre-request method to any endpoint.
     *
     * @return Configured CORS bean
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://127.0.0.1"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
