package com.amchiche.product_shop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAthFilter  extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     final String authHeader = request.getHeader("Authorization")  ;
     String jwt = null;
     String username = null;

     if(authHeader != null && authHeader.startsWith("Bearer")){
         jwt = authHeader.substring(7);
         if(jwtUtil.validateToken(jwt)){
             username= jwtUtil.extractUsername(jwt);
         }
     }

if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    //build authetification

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authToken);

}
filterChain.doFilter(request, response);

    }
}
