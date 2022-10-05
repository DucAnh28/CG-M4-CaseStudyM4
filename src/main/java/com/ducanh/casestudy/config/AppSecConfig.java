//package com.ducanh.casestudy.config;
//
//import com.ducanh.casestudy.service.appuser.IAppUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//public class AppSecConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private IAppUserService appUserService;
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(
//                        (UserDetailsService) appUserService
//                ).
//                passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/home").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/admin").hasRole("ADMIN").and()
//                .authorizeRequests().antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .and()
//                .formLogin()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/dangxuat"));
//        http.csrf().disable();
//    }
//}
