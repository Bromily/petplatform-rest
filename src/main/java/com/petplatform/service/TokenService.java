package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.dto.RefreshTokenDto;
import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.ResponsePost;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.TokenMapper;
import com.petplatform.security.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JwtProvider jwtTokenUtil;
    @Autowired
    TokenMapper tokenMapper;

    public ResponseDto tokenCreate(UserDto user,
                                   HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse) throws Exception {
        SHA256 sha256 = new SHA256();
        ResponseDto response = new ResponseDto();

        UserDto tokenUser = tokenMapper.getUserInfo(user);

        if (tokenUser.getPassword().equals(sha256.encrypt(user.getPassword()))) {
            tokenUser.setPassword("");
            response.setBody(tokenUser);
        } else {
            response.setBody("ID 또는 비밀번호를 확인해 주세요.");
        }

        // 권한 map 저장
        Map<String, Object> rules = new HashMap<String, Object>();
        rules.put("rules", tokenUser.getUserType()/* ... 권한 정보 조회 로직 실행 */);
        // JWT 발급
        Map<String, String> tokens = jwtTokenUtil.generateTokenSet(tokenUser.getUserId(), rules);
        String accessToken = URLEncoder.encode(tokens.get("accessToken"), "utf-8");
        String refreshToken = URLEncoder.encode(tokens.get("refreshToken"), "utf-8");

        log.info("[JWT 발급] accessToken : " + accessToken);
        log.info("[JWT 발급] refreshToken : " + refreshToken);

        // JWT 쿠키 저장(쿠키 명 : token)
        Cookie cookie = new Cookie("petPlatformToken", accessToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 1); // 유효기간 1일
        // httoOnly 옵션을 추가해 서버만 쿠키에 접근할 수 있게 설정
        cookie.setHttpOnly(true);
        servletResponse.addCookie(cookie);

        // refresh token 정보 저장/수정
        RefreshTokenDto rDTO = new RefreshTokenDto();
        rDTO.setAdmIdx(tokenUser.getUserId());
        rDTO.setRefreshToken(refreshToken);
        // ... DB에서 refresh token 정보 수정
        tokenMapper.updateRefreshToken(rDTO);

        return response;
    }

    public ResponseDto tokenRefresh(UserDto user,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse) throws Exception {
        ResponseDto response = new ResponseDto();
        ResponsePost result = new ResponsePost();
        String refreshToken = null;
        String adminId = "";

        // 관리자 정보 조회
        UserDto tokenUser = tokenMapper.getUserInfo(user);// ... DB에서 정보 조회 로직 실행
        // refreshToken 정보 조회
        RefreshTokenDto rDTO = new RefreshTokenDto();
        rDTO.setAdmIdx(tokenUser.getUserId());
        rDTO = tokenMapper.getRefreshToken(tokenUser.getUserId());// ... DB에서 refreshToken 정보 조회

        // token 정보가 존재하지 않는 경우
        if(rDTO == null) {
            result = new ResponsePost("refresh token 정보가 존재하지 않습니다.");
            response.setBody(result);
            return response;
        }
        // token 정보가 존재하는 경우
        else {
            refreshToken = rDTO.getRefreshToken();
        }

        // refreshToken이 존재하는 경우 검증
        boolean tokenFl = false;
        try {
            adminId = jwtTokenUtil.getUsernameFromToken(refreshToken);
            tokenFl = true;
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

        // refreshToken 사용이 불가능한 경우
        if(!tokenFl) {
            result = new ResponsePost("refresh token이 만료되었거나 정보가 존재하지 않습니다.");
            response.setBody(result);
            // ... refreshToken 정보 조회 실패 시 기존에 존재하는 refreshToken 정보 삭제

            return response;
        }

        // refreshToken 인증 성공인 경우 accessToken 재발급
        if(adminId != null && !adminId.equals("")) {
            // 권한 map 저장
            Map<String, Object> rules = new HashMap<String, Object>();
            rules.put("rules", tokenUser.getUserType()/* ... 권한 정보 조회 로직 실행 */);

            // JWT 발급
            String tokens = jwtTokenUtil.generateAccessToken(user.getUserId(), rules);
            String accessToken = URLEncoder.encode(tokens, "utf-8");

            log.info("[JWT 재발급] accessToken : " + accessToken);

            // JWT 쿠키 저장(쿠키 명 : token)
            Cookie cookie = new Cookie("petPlatformToken", tokens);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 1); // 유효기간 1일
            // httoOnly 옵션을 추가해 서버만 쿠키에 접근할 수 있게 설정
            cookie.setHttpOnly(true);
            servletResponse.addCookie(cookie);

            result = new ResponsePost(null);
            response.setBody(result);
        }else {
            result = new ResponsePost("access token 발급 중 문제가 발생했습니다.");
            response.setBody(result);
            return response;
        }

        return response;
    }

}
