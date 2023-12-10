package rut.miit.carservice.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.security.AppUserDetailsService;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfiguration {
    private UserRepository userRepository;

    public ApplicationSecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http
            .authorizeHttpRequests(
                authorizeHttpRequests ->
                    authorizeHttpRequests
                    .requestMatchers("/get-models").permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/", "/sign/in", "/sign/up", "/sign/in/error").permitAll()
                    .requestMatchers("/users/profile", "/offers/add", "/offers/edit/*", "/offers/all", "/brands/all", "/models/all", "/sign/out").authenticated()
                    .requestMatchers("/models/add", "/models/edit/*").hasAnyRole(UserRoleType.MODERATOR.name(), UserRoleType.ADMIN.name())
                    .requestMatchers("/brands/add", "/brands/edit/*", "/users/all").hasRole(UserRoleType.ADMIN.name())
                    .anyRequest().authenticated()
            )
            .formLogin(
                (formLogin) ->
                    formLogin.
                        loginPage("/sign/in").
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        defaultSuccessUrl("/",true).
                        failureForwardUrl("/sign/in/error")
            )
            .logout((logout) ->
                logout.logoutUrl("/sign/out").
                    logoutSuccessUrl("/").
                    invalidateHttpSession(true)
            ).securityContext(
                securityContext -> securityContext.
                    securityContextRepository(securityContextRepository)
            );

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() { return new AppUserDetailsService(userRepository); }
}
