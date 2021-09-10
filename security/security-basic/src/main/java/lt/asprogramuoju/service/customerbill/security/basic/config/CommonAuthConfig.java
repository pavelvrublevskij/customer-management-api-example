package lt.asprogramuoju.service.customerbill.security.basic.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public abstract class CommonAuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and().authorizeRequests()
                .antMatchers(
                        "/",
                        "/actuator/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html").permitAll()
                .and()
                .authorizeRequests().anyRequest().hasAnyRole("ADMIN", "CUSTOMER")
                .and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable(); // this is needed to access the H2 db's console

    }
}
