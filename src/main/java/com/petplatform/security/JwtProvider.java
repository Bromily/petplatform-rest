package com.petplatform.security;

import com.petplatform.dto.RefreshTokenDto;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.TokenMapper;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.password}")
    private String secretKey;

    @Autowired
    private TokenMapper tokenMapper;

    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60; // 1시간

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    // token으로 사용자 속성정보 조회
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 모든 token에 대한 사용자 속성정보 조회
    private Claims getAllClaimsFromToken(String token) {
        log.info(token);
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody();
    }

    // 토큰 만료일자 조회
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // id를 입력받아 accessToken 생성
    public String generateAccessToken(String id) {
        return generateAccessToken(id, new HashMap<>());
    }

    // id, 속성정보를 이용해 accessToken 생성
    public String generateAccessToken(String id, Map<String, Object> claims) {
        return doGenerateAccessToken(id, claims);
    }

    // JWT accessToken 생성
    private String doGenerateAccessToken(String id, Map<String, Object> claims) {
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY * 1))// 1시간
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        return accessToken;
    }

    // id를 입력받아 accessToken 생성
    public String generateRefreshToken(String id) {
        return doGenerateRefreshToken(id);
    }

    // JWT accessToken 생성
    private String doGenerateRefreshToken(String id) {
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .setId(id)
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY * 5)) // 5시간
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        return refreshToken;
    }


    // id를 입력받아 accessToken, refreshToken 생성
    public Map<String, String> generateTokenSet(String id) {
        return generateTokenSet(id, new HashMap<>());
    }

    // id, 속성정보를 이용해 accessToken, refreshToken 생성
    public Map<String, String> generateTokenSet(String id, Map<String, Object> claims) {
        return doGenerateTokenSet(id, claims);
    }

    // JWT accessToken, refreshToken 생성
    private Map<String, String> doGenerateTokenSet(String id, Map<String, Object> claims) {
        Map<String, String> tokens = new HashMap<String, String>();
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY * 1))// 1시간
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        String refreshToken = Jwts.builder()
                .setId(id)
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY * 5)) // 5시간
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public Boolean reGenerateRefreshToken(String id) throws Exception {
        log.info("[reGenerateRefreshToken] refreshToken 재발급 요청");
        // 관리자 정보 조회
        UserDto user = tokenMapper.getUserById(id);
        // DB에서 정보 조회

        // DB에서 refreshToken 정보 조회
        RefreshTokenDto rDTO = tokenMapper.getRefreshToken(id);

        // refreshToken 정보가 존재하지 않는 경우
        if(rDTO == null) {
            log.info("[reGenerateRefreshToken] refreshToken 정보가 존재하지 않습니다.");
            return false;
        }

        // refreshToken 만료 여부 체크
        try {
            String refreshToken = rDTO.getRefreshToken();
            Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(refreshToken);
            log.info("[reGenerateRefreshToken] refreshToken이 만료되지 않았습니다.");
            return true;
        }
        // refreshToken이 만료된 경우 재발급
        catch(ExpiredJwtException e) {
            rDTO.setRefreshToken(generateRefreshToken(id));
            // ... DB에서 refreshToken 정보 수정
            tokenMapper.updateRefreshToken(rDTO);
            log.info("[reGenerateRefreshToken] refreshToken 재발급 완료 : {}", generateRefreshToken(id));
            return true;
        }
        // 그 외 예외처리
        catch(Exception e) {
            log.error("[reGenerateRefreshToken] refreshToken 재발급 중 문제 발생 : {}", e.getMessage());
            return false;
        }
    }

    // 토근 검증
    public Boolean validateToken(String token, UserDto userDto) {
        try {
            Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token);
            return true;
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
        }catch(Exception e) {
            log.error("encoding Error: {}", e.getMessage());
        }

        return false;
    }
}
