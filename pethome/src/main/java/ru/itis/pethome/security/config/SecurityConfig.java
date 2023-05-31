package ru.itis.pethome.security.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.itis.pethome.security.filter.TokenAuthenticationFilter;
import ru.itis.pethome.security.filter.TokenAuthorizationFilter;
import ru.itis.pethome.security.handler.LogoutAuthSuccessHandler;
import ru.itis.pethome.security.provider.RefreshTokenAuthenticationProvider;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, TokenAuthenticationFilter tokenAuthenticationFilter, TokenAuthorizationFilter tokenAuthorizationFilter, LogoutAuthSuccessHandler logoutAuthSuccessHandler) throws Exception {
        return httpSecurity
                .authorizeRequests()
                .antMatchers("/account/sign").permitAll()
                .antMatchers("/missing/**").permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/mvc/auth/login")
                .loginProcessingUrl("/mvc/auth/login")
                .defaultSuccessUrl("/mvc/account/profile")
                .failureUrl("/mvc/auth/login?error=")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutAuthSuccessHandler)
                .and()
                //.cors().configurationSource(new UrlBasedCorsConfigurationSource().)
                .addFilter(tokenAuthenticationFilter)
                .addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Autowired
    public void injectAccountDetailAndPasswordEncode(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(refreshTokenAuthenticationProvider);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
