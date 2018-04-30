package kr.footcoder.receipt.config;

import kr.footcoder.receipt.handler.AuthFailureHandler;
import kr.footcoder.receipt.handler.AuthSuccessHandler;
import kr.footcoder.receipt.handler.HttpLogoutSuccessHandler;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final HttpAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;
    private final HttpLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        http.addFilterBefore(new TokenFilter(), UsernamePasswordAuthenticationFilter.class);


        http.csrf().disable();
        http.formLogin()
                .permitAll().loginProcessingUrl("/user/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
                .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(this.userService).passwordEncoder(new BCryptPasswordEncoder());

    }

}
