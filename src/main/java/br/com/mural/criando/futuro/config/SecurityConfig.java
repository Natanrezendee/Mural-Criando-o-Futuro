package br.com.mural.criando.futuro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Criando um usuário em memória com credenciais simples
        var user = User.withUsername("teste")
                .password(passwordEncoder().encode("teste"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user); // Gerenciador de usuários em memória
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/criar-postagem").authenticated()
                                .requestMatchers("/gerenciar-postagens").authenticated()
                                .requestMatchers("/excluir-postagens").authenticated()
                                .requestMatchers(HttpMethod.POST,"/postar-postagem").authenticated()
                                .requestMatchers(HttpMethod.POST,"/excluir-postagem").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/gerenciar-postagens", true)
                                .permitAll()
                );
        return http.build();
    }
}
