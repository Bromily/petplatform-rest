package com.petplatform.security;

import com.petplatform.dto.UserDto;
import com.petplatform.mapper.TokenMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtProvider jwtTokenProvider;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // jwt local storage 사용 시 해당 코드를 사용하여 header에서 토큰을 받아오도록 함
        // final String token = request.getHeader("Authorization");
        if(request.getMethod().equals("POST") && request.getRequestURI().equals("/api/user")){
            filterChain.doFilter(request, response);
            return;
        }

        // jwt cookie 사용 시 해당 코드를 사용하여 쿠키에서 토큰을 받아오도록 함
        String token = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("petPlatformToken"))
                .findFirst() .map(Cookie::getValue)
                .orElse(null);

        String adminId = null;
        String jwtToken = null;

        try {
            adminId = jwtTokenProvider.getUsernameFromToken(token);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        // token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
        if(adminId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDto userDto = tokenMapper.getUserById(adminId);
            userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getUserType())));
            // DB에서 관련 정보 조회
            jwtToken = tokenMapper.getRefreshToken(adminId).getRefreshToken();

            if(jwtTokenProvider.validateToken(jwtToken, userDto)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDto, null ,userDto.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // accessToken 인증이 되었다면 refreshToken 재발급이 필요한 경우 재발급
        try {
            if(adminId != null) {
                jwtTokenProvider.reGenerateRefreshToken(adminId);
            }
        }catch (Exception e) {
            log.error("[JwtRequestFilter] refreshToken 재발급 체크 중 문제 발생 : {}", e.getMessage());
        }

        filterChain.doFilter(request,response);

    }
}