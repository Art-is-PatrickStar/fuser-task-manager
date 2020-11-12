//package com.wsw.fusertaskmanager.util;
//
//import com.wsw.fusertaskmanager.api.ResultCode;
//import com.wsw.fusertaskmanager.exception.ApiException;
//import io.jsonwebtoken.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * @Author WangSongWen
// * @Date: Created in 14:37 2020/11/11
// * @Description:
// * JwtToken生成的工具类
// * JWT token的格式：header.payload.signature
// * header的格式（算法、token的类型）：
// * {"alg": "HS512","typ": "JWT"}
// * payload的格式（用户名、创建时间、生成时间）：
// * {"sub":"wang","created":1489079981393,"exp":1489684781}
// * signature的生成算法：
// * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
// */
//@Component
//@Slf4j
//public class JwtTokenUtil {
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//
//    /**
//     * 从token中获取JWT的负载
//     * @param token
//     * @return
//     */
//    private Claims getClaimsFromToken(String token){
//        Claims claims = null;
//        try {
//            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            log.info("JWT格式验证失败:{}", token);
//        }
//        return claims;
//    }
//
//    /**
//     * 从token中获取登录用户名
//     * @param token
//     * @return
//     */
//    public String getUserNameFromToken(String token){
//        log.error("JWT格式验证失败:{}", token);
//        if (!token.startsWith(this.tokenHead)) {
//            throw new ApiException(ResultCode.UNAUTHORIZED);
//        }
//        String authToken = token.substring(this.tokenHead.length());
//        String username;
//        try {
//            Claims claims = getClaimsFromToken(authToken);
//            username = claims.getSubject();
//        } catch (Exception e) {
//            username = null;
//        }
//
//        return username;
//    }
//}
