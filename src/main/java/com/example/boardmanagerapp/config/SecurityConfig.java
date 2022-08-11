package com.example.boardmanagerapp.config;

import com.example.boardmanagerapp.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_ADMIN = Role.RoleName.ADMIN.name();
    private static final String ROLE_USER = Role.RoleName.USER.name();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/boards", "/sections",
                        "/tasks").hasAnyAuthority(ROLE_ADMIN, ROLE_USER)

                .antMatchers(HttpMethod.POST, "/boards", "/sections",
                        "/tasks")
                .hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.PUT,"/movie-sessions/**").hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.GET, "/orders", "/shopping-carts/by-user")
//                .hasRole(ROLE_USER)
//                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(ROLE_USER)
//                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
