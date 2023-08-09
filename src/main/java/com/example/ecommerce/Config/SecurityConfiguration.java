package com.example.ecommerce.Config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // protect endpoint /api/orders
        http.authorizeRequests(configurer ->
                configurer
                        .requestMatchers("/api/orders/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll())
                .oauth2ResourceServer()
                .jwt();
        // add CORS filters
        http.cors();
        // add content negotiation strategy to support Okta sending back
        // friendly response
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());
        // force a non-empty body response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // fails because we are sending checkout request with http poet
        // by default CSRF is enabled CSRF performs checks on Post Using cookies
        // since we are not using cookies for session tracking CSRF says request is unauthorized
        // we can resolve this by disabling CSRF
        // this technique is commonly used for rest APIs

        http.csrf().disable();
        return http.build();
    }
}
