package org.example.nbcam_addvanced_1.common.filter;

import static org.example.nbcam_addvanced_1.common.utils.JwtUtil.BEARER_PREFIX;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbcam_addvanced_1.common.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // JWT 검증이 필요 없는 경우 조 ex) 로그인
        String requestURI = request.getRequestURI();

        if(requestURI.equals("/api/user/login")) {
            filterChain.doFilter(request,response);
            return;
        }

        // JWT 토큰이 있는지 없는지 검사

        String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            log.info("JWT 토큰이 필요 합니다.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요 합니다.");
            return;
        }


        // JWT 토큰이 있다면 유효한 토큰인지 검사

        String jwt = authorizationHeader.substring(7);

        if (!jwtUtil.validateToken(jwt)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\": \"Unauthorized\"}");
        }

        // JWT 토큰에서 복호화 한 데이터 저장하기

        String username = jwtUtil.extractUsername(jwt);

        request.setAttribute("username", username);

        User user = new User(username,"", List.of());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));


        filterChain.doFilter(request, response);


    }
}
