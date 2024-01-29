package fr.dorian_ferreira.cap_entreprise.configuration;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers("/admin/**").hasRole("MODERATOR")
                    .requestMatchers("/review/**").authenticated()
                    .requestMatchers("/game/**").authenticated()
                    .requestMatchers("/**").permitAll()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl(UrlRoute.URL_REDIRECT)
                    .permitAll()
            )
            .logout(logout ->
                 logout
                    .logoutSuccessUrl("/")
                    .permitAll()
            );

        return http.build();
    }

}
