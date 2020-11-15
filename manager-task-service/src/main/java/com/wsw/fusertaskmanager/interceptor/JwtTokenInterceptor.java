package com.wsw.fusertaskmanager.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.fusertaskmanager.annotation.JwtToken;
import com.wsw.fusertaskmanager.api.CommonResult;
import com.wsw.fusertaskmanager.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author WangSongWen
 * @Date: Created in 10:44 2020/11/13
 * @Description: 自定义JwtToken拦截器
 */
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Value("${jwt.secretKey}")
    private String key;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("JwtTokenInterceptor.preHandle");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        response.setContentType("text/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取目标方法的Method对象
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtToken.class)){
            // 方法包含JwtToken注解
            String token = request.getHeader("token");
            if (null == token){
                // token不存在
                JwtToken jwtToken = method.getAnnotation(JwtToken.class);
                if (jwtToken.required()){  // required属性为true
                    response.setStatus(401);
                    CommonResult<Object> commonResult = CommonResult.unauthorized();
                    String json = objectMapper.writeValueAsString(commonResult);
                    response.getWriter().println(json);
                    return false;
                }
            }else {
                // token存在，验证有效性
                String base64 = new BASE64Encoder().encode(key.getBytes());
                SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
                try {
                    Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                    String userJson = claimsJws.getBody().getSubject();
                    log.info(userJson);
                    User user = objectMapper.readValue(userJson, User.class);
                    request.setAttribute("$user", user);
                    return true;
                } catch (JsonProcessingException e){
                    // json转换失败抛出异常
                    e.printStackTrace();
                    response.setStatus(500);
                    CommonResult<Object> commonResult = CommonResult.failed("Json转换异常");
                    String json = objectMapper.writeValueAsString(commonResult);
                    response.getWriter().println(json);
                    return false;
                } catch (JwtException e) {
                    // 验证失败抛出异常
                    e.printStackTrace();
                    response.setStatus(404);
                    CommonResult<Object> commonResult = CommonResult.validateFailed();
                    String json = objectMapper.writeValueAsString(commonResult);
                    response.getWriter().println(json);
                    return false;
                }
            }
        }
        return true;
    }
}
