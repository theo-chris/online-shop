package eMarket;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

    @EnableWebSecurity
    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        DataSource dataSource;

        @Bean
        public PasswordEncoder passwordEncoder(){
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
        }

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        protected void configure(HttpSecurity http) throws Exception {
            http
            .csrf().disable()
             // TODO: AUTHENTICATION AND AUTHORIZATION

                    .requiresChannel()
                    .anyRequest()
                    .requiresSecure()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")

                    .defaultSuccessUrl("/success-login",true)
                    .failureUrl("/login-form")
                    .and()
                    .logout()


                    .deleteCookies()
                    .logoutSuccessUrl("/login-form")

                    .and()
                    .authorizeRequests()
                    .antMatchers("/system").hasRole("ADMIN")
                    .antMatchers("/system/").hasRole("ADMIN")
                    .antMatchers("/system/user").hasAnyRole("USER","PREMIUM")
                    .antMatchers("/system/user/**").hasAnyRole("USER","PREMIUM")
                    .antMatchers("/system/premium").hasRole("PREMIUM")
                    .antMatchers("/system/premium/**").hasRole("PREMIUM")
                    .antMatchers("/setDate/**").hasRole("ADMIN")
                    .antMatchers("/deal/").hasRole("ADMIN")
                    .antMatchers("/deal/add/**").hasRole("ADMIN")
                    .antMatchers("/deal/delete/**").hasRole("ADMIN")
                    .antMatchers("/product/").hasRole("ADMIN")
                    .antMatchers("/product/productDetail/**").hasRole("ADMIN")
                    .antMatchers("/product/add/**").hasRole("ADMIN")
                    .antMatchers("/product/delete/**").hasRole("ADMIN")

                    .antMatchers("/order/").hasAnyRole("USER","PREMIUM")
                    .antMatchers("/order").hasAnyRole("USER","PREMIUM")
                    .antMatchers("/order/wishlist/**").hasRole("PREMIUM")



                    .antMatchers("/signup/").permitAll()
                    .antMatchers("/signup/add/**").permitAll()
                    .and()

                    .exceptionHandling().accessDeniedPage("/access-denied")
            ;
        }

    }
