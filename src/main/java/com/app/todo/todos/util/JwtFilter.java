package com.app.todo.todos.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.todo.todos.service.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwt;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        try {
            String header = req.getHeader("Authorization");
            
            if (header != null && header.startsWith("Bearer ")) {
                try {
                    var claims = jwt.parse(header.substring(7)).getPayload();
                    Long uid = Long.valueOf(claims.getSubject());
                    
                    // 🔥 Lazy load UserService to avoid circular injection
                    UserService users = context.getBean(UserService.class);
                    
                    var auth = new UsernamePasswordAuthenticationToken(
                            uid,
                            null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("roles", String.class)));
                    
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (NumberFormatException | BeansException ignored) {
                }
            }
            
            chain.doFilter(req, res);
        } catch (java.io.IOException ex) {
        }
    }
}
