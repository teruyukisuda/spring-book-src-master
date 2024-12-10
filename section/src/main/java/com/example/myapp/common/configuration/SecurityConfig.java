package com.example.myapp.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
            .requestMatchers("/login", "/logout").permitAll()
            // admin配下のPOSTリクエストはADMINロールのみ
            .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
            // admin配下はADMIN or STAFFロール
            .requestMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
            // その他すべてのリクエストは認証が必要
            .anyRequest().authenticated()
         .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?failure")
            .defaultSuccessUrl("/admin/training/display-list", true)  // true を追加
            .permitAll()  // ログインフォームへのアクセスを許可
        .and()
            .exceptionHandling()
            .accessDeniedPage("/display-access-denied");
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails taro = User.builder().username("taro").password("{noop}123456").roles(
            "MANAGER").build();
        return new InMemoryUserDetailsManager(taro);
    }
} 
