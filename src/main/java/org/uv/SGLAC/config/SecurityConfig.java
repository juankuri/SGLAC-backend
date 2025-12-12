// package org.uv.SGLAC.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()  // solo para pruebas de Postman
//             .authorizeHttpRequests()
//             .requestMatchers(HttpMethod.POST, "/users").permitAll()  
//             .anyRequest().authenticated() 
//             .and()
//             .httpBasic(); 
//         return http.build();
//     }
// }


package org.uv.SGLAC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .httpBasic(httpBasic -> httpBasic.disable())     // 🔥 desactiva auth básica
            .formLogin(form -> form.disable())               // 🔥 desactiva login por formulario
            .logout(logout -> logout.disable())              // opcional
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()      // 🔥 deja pasar todo API
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
