package com.shop.utils;
import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT生成、檢驗
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtProvider {

    private long tokenExpiration; //有效時間
    private String tokenSignKey;  //自定義密鑰

    // 生成token字符串
    public String createToken(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        return Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000 * 60))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    // 從token字符串中獲取用戶userId
    public Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    // 判斷token是否有效
    public boolean isExpiration(String token) {
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //沒有過期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //過期，返回true
            return true;
        }
    }
}
