package com.alkemy.ong.configuration;

import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.security.JwtRequestFilter;
import com.alkemy.ong.security.impl.UserSecurityService;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.alkemy.ong.security.CustomAccessDeniedHandler;
import com.alkemy.ong.security.CustomAuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoded());
    }

    @Bean
    PasswordEncoder passwordEncoded() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return (UserDetailsService) email -> userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/v1/organizations/public/*", "/v1/slides/public/*", "/v1/auth/register","/v1/auth/login","/api/docs/**","/api/swagger-ui/**","/v3/api-docs/**").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){    
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}

