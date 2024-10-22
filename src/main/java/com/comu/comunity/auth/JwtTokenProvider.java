package com.comu.comunity.auth;

import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final MemberRepository memberRepository;

//토큰과 관련된 기능 틀래스

    //토크을 가지오 오는 키
    private final String secretKey = "TTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEY";
    //유효시간
    private final long validityInMilliseconds = 3600000; // 1시간

    public String createToken(Member member) {
        Claims claims = Jwts.claims()
                .setSubject(member.getEmail());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getMemberId() {
        HttpServletRequest request = getCurrentHttpRequest();  // 현재 요청 객체 가져오기
        if (request != null) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = extractJwtToken(bearerToken);
                 String email = getEmailFromToken(token);  // 토큰에서 이메일 추출
               Member member =  memberRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("member not found"));

                return member.getId();
            }
        }
        return null;
    }

    public String extractJwtToken(String tokenWithBearer) {
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7);
        }

        return tokenWithBearer;
    }


    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
