package com.vti.finalexam.config;

import com.vti.finalexam.Constants.API;
import com.vti.finalexam.Constants.ROLE;
import com.vti.finalexam.exception.AuthExceptionHandler;
import com.vti.finalexam.security.JwtTokenFilter;
import com.vti.finalexam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final AuthExceptionHandler authExceptionHandler;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfiguration(AccountService accountService,
        AuthExceptionHandler authExceptionHandler) {
        this.accountService = accountService;
        this.authExceptionHandler = authExceptionHandler;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // de tranh loi 403
            .cors()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authExceptionHandler)
            .accessDeniedHandler(authExceptionHandler)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html/**",
                "/webjars/**",
                "favicon.ico").permitAll()
            .antMatchers(HttpMethod.POST, "/login", "/register",
                "/forgot", "/forgot/**").permitAll()
            .antMatchers(API.BASE_API_V1 + "/auth/register").permitAll()
            .antMatchers(API.BASE_API_V1 + "/auth/login").permitAll()
            .antMatchers("/swagger-resources").permitAll()
            .antMatchers(API.BASE_API_V1 + API.ACCOUNTS + "/**").permitAll()
            .antMatchers(API.BASE_API_V1 + API.ACCOUNTS + "/**").hasAnyAuthority(ROLE.ADMIN, ROLE.MANAGER)
            .antMatchers(API.BASE_API_V1 + API.DEPARTMENTS + "/**").hasAnyAuthority(ROLE.ADMIN, ROLE.MANAGER, ROLE.EMPLOYEE)
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic()
            .and()
            .csrf()
            .disable();
    }

    @Bean
    @Primary
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // de tranh loi 403
                .cors().disable() // de web goi duoc API
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/accounts/login", "/accounts/register",
                        "/accounts/forgot", "/accounts/forgot/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
