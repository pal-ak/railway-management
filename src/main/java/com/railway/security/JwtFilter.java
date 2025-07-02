package com.railway.security;

import com.railway.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    // Exclude login and register endpoints from filtering
    private static final List<String> excludedUrls = List.of("/api/login", "/api/register");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip filter for excluded URLs
        if (excludedUrls.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("[JWT DEBUG] Token: " + token); // ✅ Debug

            String username = jwtUtil.extractUsername(token);
            System.out.println("[JWT DEBUG] Username from token: " + username); // ✅ Debug

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                System.out.println("[JWT DEBUG] Loaded user: " + userDetails.getUsername()); // ✅ Debug
                System.out.println("[JWT DEBUG] Authorities: " + userDetails.getAuthorities()); // ✅ Debug

                if (jwtUtil.validateToken(token)) {
                    System.out.println("[JWT DEBUG] Token is valid"); // ✅ Debug

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("[JWT DEBUG] Token is INVALID"); // ✅ Debug
                }
            }
        } else {
            System.out.println("[JWT DEBUG] Authorization header missing or invalid"); // ✅ Debug
        }

        chain.doFilter(request, response);
    }
}
