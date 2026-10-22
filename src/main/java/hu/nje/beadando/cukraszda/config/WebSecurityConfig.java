package hu.nje.beadando.cukraszda.config;
import hu.nje.beadando.cukraszda.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig {
    private final CustomUserDetailsService service;
    public WebSecurityConfig(CustomUserDetailsService service) { this.service = service; }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            boolean admin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
            response.sendRedirect(request.getContextPath() + (admin ? "/crud" : "/uzenetek"));
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        // A REST API admin műveleteit Postman/cURL HTTP Basic hitelesítéssel lehet tesztelni.
        // A webes űrlapokon a CSRF védelem továbbra is aktív.
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/sutik", "/api/sutik/**"));
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/adatbazis", "/kapcsolat", "/diagram", "/rest",
                "/login", "/register", "/css/**", "/images/**", "/favicon.ico").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/sutik", "/api/sutik/**").permitAll()
            .requestMatchers("/api/sutik", "/api/sutik/**").hasRole("ADMIN")
            .requestMatchers("/crud", "/crud/**").hasRole("ADMIN")
            .requestMatchers("/uzenetek", "/uzenetek/**").authenticated()
            .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/login")
            .successHandler(authenticationSuccessHandler()).permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.logout(logout -> logout.logoutSuccessUrl("/").permitAll());
        http.exceptionHandling(exception -> exception
            .accessDeniedHandler((request, response, ex) -> {
                if (request.getRequestURI().contains("/api/")) response.sendError(403);
                else response.sendRedirect(request.getContextPath() + "/");
            }));
        return http.build();
    }
}
