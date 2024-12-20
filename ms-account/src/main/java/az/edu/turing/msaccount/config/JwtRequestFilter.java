package az.edu.turing.msaccount.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {

                email = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Expired token");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
                return;
            }
        }

        // If email is extracted successfully and the user is not already authenticated
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details by email
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);


            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
