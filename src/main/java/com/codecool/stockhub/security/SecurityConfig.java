package com.codecool.stockhub.security;


import com.codecool.stockhub.model.ApplicationUserPermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.codecool.stockhub.model.ApplicationUserPermission.CLIENT_READ;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/add").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasAuthority(ApplicationUserPermission.ADMIN_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/active/**").hasAnyAuthority(CLIENT_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/buy").hasAuthority(CLIENT_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/client/**").hasAuthority(CLIENT_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/companies").permitAll()
                .antMatchers(HttpMethod.GET, "/symbol").permitAll()
                .antMatchers(HttpMethod.GET, "/news/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
