package com.epam.royalbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()

                .antMatchers("/css/**", "/",
                        "/registration",
                        "/room/*", "/media/**")
                    .permitAll()

                .antMatchers("/admin**").hasAuthority("ADMIN")

                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll()
                    .clearAuthentication(true)
                    .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder)
                .usersByUsernameQuery("SELECT email, password, true FROM users WHERE email = ?;")
                .authoritiesByUsernameQuery("SELECT email, user_type FROM users WHERE email = ?;");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(4);
    }
}