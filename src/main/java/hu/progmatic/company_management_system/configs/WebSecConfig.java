package hu.progmatic.company_management_system.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")

                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)

                .and()

                .authorizeRequests()
                .antMatchers("/", "/register", "/*.css", "/*.jpg")
                .permitAll()

                .antMatchers("/users")
                .hasAnyRole("ADMIN", "USER")

                .anyRequest()
                .authenticated();
    }
}

