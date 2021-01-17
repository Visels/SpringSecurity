package com.sec.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.sec.demo.Security.ApplicationUserPermission.*;
import static com.sec.demo.Security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //authorizing the api access points
           http
                   .csrf().disable()
                   .authorizeRequests()
                   .antMatchers("/", "index", "/css/*" ,"/js/*" ).permitAll()
                   .antMatchers("/api/**").hasRole(STUDENT.name())
//                   .antMatchers(HttpMethod.DELETE, "management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                   .antMatchers(HttpMethod.POST, "management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                   .antMatchers(HttpMethod.PUT, "management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                   .antMatchers(HttpMethod.GET, "management/api/**").hasAnyAuthority(ADMIN.name(), ADMIN_TRAINEE.name())
                   .anyRequest()
                   .authenticated()
                   .and()
                   .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails elvisUser = User.builder()
                .username("elvis")
                .password(passwordEncoder.encode("elvis"))
                .roles(STUDENT.name())
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("linda"))
                .roles(ADMIN.name())
                .build();


        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("elvis"))
                .roles(ADMIN_TRAINEE.name())
                .build();


        return new InMemoryUserDetailsManager(elvisUser, lindaUser, tomUser);
    }







}
