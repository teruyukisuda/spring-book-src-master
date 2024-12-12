package com.example.myapp.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//            .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//            .requestMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
//            //.anyRequest().permitAll()
//            .anyRequest().authenticated()
//         .and()
//            .formLogin()
//            .loginPage("/login")
//            .failureUrl("/login?error")
//            .defaultSuccessUrl("/admin/training/display-list");
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRFを無効化する場合（APIとして使用する場合など）
            //.csrf().disable()
            .authorizeHttpRequests()
            // 静的リソースへのアクセスを明示的に許可
            .requestMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/favicon.ico",
                "/error",
                "/webjars/**"
            ).permitAll()
            // ログイン関連のパスを許可
            .requestMatchers("/login", "/logout")
            .permitAll()
            // shopping
            .requestMatchers(HttpMethod.GET, "/shoppings").hasRole("ADMIN")
            // training 
            .requestMatchers(HttpMethod.GET, "/trainings").hasRole("ADMIN")
            // 誰でも見られるページへのGET
            .requestMatchers(HttpMethod.GET, "/shoppings/showall")
            .permitAll()
            // apiは許可
            .requestMatchers(HttpMethod.GET, "/api/**")
            .permitAll()
            // その他すべてのリクエストは認証が必要
            .anyRequest().authenticated()
         .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?failure")
            .defaultSuccessUrl("/shopping", false)  // true を追加
            .permitAll()  // ログインフォームへのアクセスを許可
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        .and()
            .exceptionHandling()
            .accessDeniedPage("/display-access-denied");

        // for h2-console        
        http
            .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).and()
            .headers().frameOptions().sameOrigin();

        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails taro = User.builder().username("taro").password("{noop}taro").roles(
            "ADMIN").build();
        UserDetails neko = User.builder().username("neko").password("{noop}neko").roles(
            "GENERAL").build();
        return new InMemoryUserDetailsManager(taro, neko);
    }
} 
