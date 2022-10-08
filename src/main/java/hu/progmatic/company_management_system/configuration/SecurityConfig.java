package hu.progmatic.company_management_system.configuration;

import hu.progmatic.company_management_system.models.Position;
import hu.progmatic.company_management_system.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

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
                .failureUrl("/login-error")
                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()

                .authorizeRequests()
                .antMatchers("/", "/home", "/login", "/*.css", "/main", "/logout", "/login-error")
                .permitAll()

                .antMatchers("/register")
                .hasRole("ADMIN")

                .antMatchers("/workstation")
                .hasAnyRole("PRODUCTIONMANAGER", "ADMIN", "CEO", "MACHINE_OPERATOR")

                .antMatchers("/warehouses")
                .hasAnyRole( "TRADE_MANAGER", "WAREHOUSEWORKER", "ADMIN", "CEO")

                .antMatchers( "/shippingins", "/shippingouts", "/suppliers", "/customers")
                .hasAnyRole("TRADE_MANAGER", "ADMIN", "CEO")

                .antMatchers("/transfer")
                .hasAnyRole("WAREHOUSEWORKER", "ADMIN", "CEO")

                .antMatchers("/finance", "/employees", "/payroll")
                .hasAnyRole("FINANCE_STAFF", "ADMIN", "CEO", "ACCOUNTANT")

                .antMatchers("/rawmaterials", "/producedproducts", "/ingredients", "/bomlists", "/endproducts")
                .hasAnyRole("PRODUCTIONMANAGER", "ADMIN", "CEO");
    }
}
