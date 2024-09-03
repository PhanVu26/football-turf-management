package kms.bootcamp.footballturfmanagementservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Adjust this based on your needs
        config.addAllowedMethod("*"); // Adjust this based on your needs
        config.addAllowedHeader("*"); // Adjust this based on your needs
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
