package com.wsw.fusertaskmanager.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.fusertaskmanager.api.CommonResult;
import com.wsw.fusertaskmanager.config.AuthConfig;
import com.wsw.fusertaskmanager.domain.User;
import com.wsw.fusertaskmanager.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:47 2020/11/12
 * @Description:
 */
@RestController
public class AuthController {
    @Resource
    private AuthService authService;
    @Resource
    private AuthConfig authConfig;

    @PostMapping("/auth")
    @ResponseBody
    public CommonResult<Map> auth(@RequestParam("username") String username, @RequestParam("password") String password){
        String tokenKey = authConfig.getSecretKey();  // token密钥
        final Integer authTokenET = 24*60*60*1000;  // 认证 token过期时间
        final Integer refreshTokenET = 7*24*60*60*1000;  // 刷新token 过期时间

        CommonResult<Map> commonResult = null;
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            User user = authService.auth(username, password);
            map.put("user", user);
            String auth_token = getToken(user, tokenKey, authTokenET);
            String refresh_token = refreshToken(user, tokenKey, refreshTokenET);
            map.put("auth_token", auth_token);
            map.put("refresh_token", refresh_token);

            commonResult = CommonResult.success(map);
        } catch (Exception e) {
            commonResult = CommonResult.failed();
        }

        return commonResult;
    }

    /**
     * 生成token
     * @param user
     * @param tokenKey
     * @param authTokenET
     * @return
     */
    private String getToken(User user, String tokenKey, Integer authTokenET) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        user.setPassword(null);  // jwt的json数据不能包含敏感信息
        String authToken = null;

        try {
            String userString = objectMapper.writeValueAsString(user);
            // 对密匙进行Base64编码
            String base64 = new BASE64Encoder().encode(tokenKey.getBytes());
            // 生成密匙对象,会根据base64长度自动选择相应的HMAC算法
            SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
            // 利用jwt生成token
            authToken = Jwts.builder()
                    .setSubject(userString)
                    .setExpiration(new Date(System.currentTimeMillis() + authTokenET))  // token过期时间
                    .signWith(secretKey)
                    .compact();

            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 刷新token
     * @param user
     * @param tokenKey
     * @param refreshTokenET
     * @return
     */
    private String refreshToken(User user, String tokenKey, Integer refreshTokenET) {
        String refreshToken = null;
        try {
            refreshToken = getToken(user, tokenKey, refreshTokenET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refreshToken;
    }
}
