package com.ll.sbrestapi.global.security;

import com.ll.sbrestapi.domain.member.member.service.MemberService;
import com.ll.sbrestapi.global.rq.Rq;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final Rq rq;
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String apiKey = rq.getHeader("X-apiKey",null);

        if (apiKey != null) {
            SecurityUser user = memberService.getUserFromApiKey(apiKey);
            rq.setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }
}
