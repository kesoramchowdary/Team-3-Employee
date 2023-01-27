package com.demo.employee_management_system.Util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.employee_management_system.Services.USerDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private jwtTokenUtil jwt;

    @Autowired
    USerDetailService userdetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println(request.getHeader("Authorization"));
        String header = parseJwt(request);

        if (header != null && jwt.validateJwtToken(header)) {
            String role = jwt.getRoleFromJwtToken(header);
            System.out.println(role);

            String username = jwt.getUserNAmeFromJwtToken(header);

            UserDetails user = userdetails.loadUserByUsername(username);

            System.out.println(user);

            SecurityContextHolder.getContext()
                    .setAuthentication(
                            new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities()));

        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
