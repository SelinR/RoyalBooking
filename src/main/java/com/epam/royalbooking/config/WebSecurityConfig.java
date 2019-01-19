package com.epam.royalbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()

                .antMatchers("/css/**").permitAll()
                .antMatchers("/", "/registration").permitAll()

                //this is the example how to restrict access to pages
                //.antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .antMatchers("/users").hasAuthority("ADMIN")

                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()

                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT email, password, true FROM users WHERE email = ?;")
                .authoritiesByUsernameQuery("SELECT email, user_type FROM users WHERE email = ?;");

    }
}