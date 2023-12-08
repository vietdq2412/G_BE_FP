package gre.jb.config;


import gre.jb.security.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@ComponentScan("gre.jb.config")
@CrossOrigin("*")
public class SecurityConfig {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_COMPANY = "COMPANY";
    private static final String ROLE_USER = "USER";

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("userPass")
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password("adminPass")
                .roles("USER", ROLE_ADMIN)
                .build());
        return manager;
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final String pathAccount = "/account/**";
        final String pathJob = "/jobs/**";
        final String pathCompany = "/company/**";
        final String pathAppUser = "/appUser/**";
        final String pathCv = "/cv/**";
        http.authorizeHttpRequests()
                .requestMatchers( "/account/login/**", "/account/register/**", "/cv/existsByAppUser/**").permitAll()
                .requestMatchers(HttpMethod.GET, pathAccount).hasAnyRole(ROLE_ADMIN, ROLE_COMPANY)
                .requestMatchers(HttpMethod.POST, pathAccount).hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, pathAccount).hasRole(ROLE_ADMIN)

                .requestMatchers(HttpMethod.GET, pathJob).hasAnyRole(ROLE_COMPANY, ROLE_USER)
                .requestMatchers(HttpMethod.POST, pathJob).hasRole(ROLE_COMPANY)
                .requestMatchers(HttpMethod.DELETE, pathJob).hasRole(ROLE_COMPANY)
                .requestMatchers(HttpMethod.PUT, pathJob).hasRole(ROLE_COMPANY)

                .requestMatchers(HttpMethod.GET, pathCompany).hasAnyRole(ROLE_COMPANY, "USER")
                .requestMatchers(HttpMethod.POST, pathCompany).hasRole(ROLE_COMPANY)
                .requestMatchers(HttpMethod.DELETE, pathCompany).hasRole(ROLE_COMPANY)
                .requestMatchers(HttpMethod.PUT, pathCompany).hasRole(ROLE_COMPANY)

                .requestMatchers(HttpMethod.GET, "/appUser").hasAnyRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.GET, pathAppUser).hasAnyRole(ROLE_USER)
                .requestMatchers(HttpMethod.POST, pathAppUser).hasRole(ROLE_USER)
                .requestMatchers(HttpMethod.DELETE, pathAppUser).hasRole(ROLE_USER)
                .requestMatchers(HttpMethod.PUT, pathAppUser).hasRole(ROLE_USER)

                .requestMatchers(HttpMethod.GET, pathCv).hasAnyRole(ROLE_COMPANY, "USER")
                .requestMatchers(HttpMethod.POST, pathCv).hasRole(ROLE_USER)
                .requestMatchers(HttpMethod.DELETE, pathCv).hasAnyRole(ROLE_COMPANY, ROLE_USER)

                .requestMatchers(HttpMethod.GET, "/admin/**").hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}