package lt.asprogramuoju.service.customerbill.security.basic.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Simple basic security
 * Implementation used application configuration for authentication
 *
 * @author pavel.vrublevskij
 * @version 2021-09-10
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationsUsers.class)
@RequiredArgsConstructor
@Slf4j
public class BasicAuthConfig extends CommonAuthConfig {

    private final ApplicationsUsers applicationsUsers;

    @Bean
    protected InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        log.info("Importing {} globally users:", applicationsUsers.getUsers().size());
        // loading in memory from configuration file all users 'api-users'
        applicationsUsers.getUsers().forEach(user -> {
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles())
                    .build());
        });
        log.info("Imported globally users {}", applicationsUsers);

        return manager;
    }
}
