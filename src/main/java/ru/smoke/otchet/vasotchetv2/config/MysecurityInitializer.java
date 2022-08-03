package ru.smoke.otchet.vasotchetv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class MysecurityInitializer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/allinfo/**").hasAnyRole("ADMIN", "CHIEF")
                .and()
                .formLogin();

    }
    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("smoke")
                .password("{bcrypt}$2a$12$T8I01tI4t6/J2sCgDu0TX.R1DhCZwnRXhEZ.l4yx0aZYFQYDviwcW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$tPiZIbinXf.CRryYTUm/z.kx4dJ2jl9ggav3D2.rEAjdEsPA8sIHW")
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
