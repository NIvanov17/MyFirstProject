package com.example.myfirstproject.config;

import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.UserRepository;
import com.example.myfirstproject.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;


@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/static/**", "/css/**", "/img/**").permitAll()
                .requestMatchers("/","/about-us").permitAll()
                .requestMatchers("/login","/register","/login-error").anonymous()
                .requestMatchers("/admin").hasRole(UserRoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home",true)
                .failureForwardUrl("/login-error")
                .and()
                .logout().deleteCookies("JSESSIONID").clearAuthentication(true).invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

//        http
//                //which requests are allowed and which not
//                .authorizeHttpRequests()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .requestMatchers("/static/**", "/js/**", "/css/**", "/img/**").permitAll()
//                .requestMatchers("/", "/home", "/about-us", "/login/error","/login","/register").permitAll()
//                .requestMatchers("/**").authenticated()
////                .requestMatchers("/login","/register").anonymous()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/login-error")
//                .and()
//                .logout()
//                .logoutUrl("/users/logout")
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }
}
