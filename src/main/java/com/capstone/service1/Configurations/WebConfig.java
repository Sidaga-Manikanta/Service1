package com.capstone.service1.Configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration


public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> {
//            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
//                    .requestMatchers("/api/user/**").permitAll()
//                    .requestMatchers("/plans/").permitAll()
//                    .anyRequest()).authenticated();
//        });
//     //   http.formLogin(Customizer.withDefaults());
//       // http.httpBasic(Customizer.withDefaults());
//        return (SecurityFilterChain)http.build();
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedOrigins("http://localhost:4200","https://recharge-service-client.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
