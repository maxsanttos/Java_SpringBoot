package io.maxsanttos.vendas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    // criptografia e descriptografia
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //autenticação dos usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("fulano")
        .password(passwordEncoder().encode("123"))
        .roles("USER");
    }

    //autorização
    @Override
    protected void configure( HttpSecurity http) throws Exception {
       http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/clientes/*")
                    .authenticated()
            .and().formLogin();      
    }
}
 