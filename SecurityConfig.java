import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtUtils jwtUtils = new JwtUtils();


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    static class JwtAuthFilter extends OncePerRequestFilter {
        private final JwtUtils jwtUtils;
        public JwtAuthFilter(JwtUtils jwtUtils) { this.jwtUtils = jwtUtils; }
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                try {
                    String token = header.substring(7);
                    var decoded = jwtUtils.validateToken(token);
                    String username = decoded.getSubject();
                    String role = decoded.getClaim("role").asString();
                    var auth = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
                    org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
// invalid token — ignore or set 401
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}