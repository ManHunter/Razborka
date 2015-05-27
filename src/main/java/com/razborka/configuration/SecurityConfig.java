package com.razborka.configuration;

import com.razborka.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by Admin on 09.04.2015.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // регистрируем нашу реализацию UserDetailsService
    // а также PasswordEncoder для приведения пароля в формат SHA1
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
                //.passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/registration").anonymous();
        http.authorizeRequests()
                .antMatchers("/login").anonymous();
        http.authorizeRequests()
                .antMatchers("/customer/**").access("hasAnyRole('CUSTOMER', 'ADMIN')");
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')");
        http.authorizeRequests()
                .antMatchers("/image/**", "/catalog**").permitAll();
        http.authorizeRequests()
                .antMatchers("/profile/**").access("hasAnyRole('CUSTOMER', 'SELLER', 'STO')");
        http.authorizeRequests()
                .antMatchers("/profile/cars").access("hasAnyRole('SELLER')");
        http.rememberMe()
                .key("key")
//                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600);

        // включаем защиту от CSRF атак
        http.csrf()
                .disable()
                        // указываем правила запросов
                        // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                        // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                        // указываем URL при неудачном логине
                .failureUrl("/login?error")
                        // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                        // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                        // указываем URL логаута
                .logoutUrl("/logout")
                        // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                        // делаем не валидной текущую сессию
                .invalidateHttpSession(true);

    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password from user where email=?")
                .authoritiesByUsernameQuery("select email, role from user where email=?");
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(dataSource);
//        return db;
//    }
//
//    @Bean
//    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
//        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
//        auth.setTargetUrlParameter("targetUrl");
//        return auth;
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/register").permitAll();
//        http.authorizeRequests()
//                .antMatchers("/seller/**").access("hasAnyRole('SELLER', 'ADMIN')");
//        http.authorizeRequests()
//                .antMatchers("/customer/**").access("hasAnyRole('CUSTOMER', 'ADMIN')");
//        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ADMIN')");
//        http.authorizeRequests()
//                .antMatchers("/image/**", "/catalog**").access("hasAnyRole('ADMIN', 'SELLER', 'CUSTOMER')");
//        http.authorizeRequests()
//                .antMatchers("/profile/**").access("hasAnyRole('ADMIN', 'CUSTOMER', 'SELLER')");
//        http.formLogin()
//                .loginPage("/loginPage")
//                .defaultSuccessUrl("/")
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .failureUrl("/loginPage?error")
//                .defaultSuccessUrl("/");
//        http.logout()
//                .logoutSuccessUrl("/?logout")
//                .logoutUrl("/j_spring_security_logout");
//        http.rememberMe()
//                .key("verySecretKey")
//                        //.tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(1209600);
//
//        http.csrf().disable();
//    }

    // Указываем Spring контейнеру, что надо инициализировать <b></b>ShaPasswordEncoder
    // Это можно вынести в WebAppConfig, но для понимаемости оставил тут
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }
}