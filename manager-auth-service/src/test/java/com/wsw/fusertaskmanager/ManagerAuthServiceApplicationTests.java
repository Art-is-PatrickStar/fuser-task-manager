package com.wsw.fusertaskmanager;

import com.wsw.fusertaskmanager.api.CommonResult;
import com.wsw.fusertaskmanager.controller.AuthController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@SpringBootTest
class ManagerAuthServiceApplicationTests {
	@Autowired
	private AuthController authController;

	@Test
	void createJwt() {
		// 密匙
		String key = "1234567890-1234567890-1234567890";  // 32位
		// 对密匙进行Base64编码
		String base64 = new BASE64Encoder().encode(key.getBytes());
		// 生成密匙对象,会根据base64长度自动选择相应的HMAC算法
		SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
		// 利用jwt生成token
		String token = Jwts.builder()
				.setSubject("{\"wsw\":123}")
				.setExpiration(new Date(System.currentTimeMillis() + 30*60*1000))
				.signWith(secretKey)
				.compact();
		System.out.println(token);
	}

	@Test
	void checkJwt(){
		// token
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ3c3dcIjoxMjN9IiwiZXhwIjoxNjA2MTIyNzkxfQ.QBugwSjmLUE5qeQfoKvFP1LQpGxDSwOctgllf95xG0I";
		// 密匙
		String key = "1234567890-1234567890-1234567890";
		// 对密匙进行Base64编码
		String base64 = new BASE64Encoder().encode(key.getBytes());
		// 生成密匙对象,会根据base64长度自动选择相应的HMAC算法
		SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
		// 验证token
		JwtParser parse = Jwts.parserBuilder().setSigningKey(secretKey).build();
		Jws<Claims> claimsJws = parse.parseClaimsJws(token);
		String subject = claimsJws.getBody().getSubject();
		String expiration = claimsJws.getBody().getExpiration().toString();
		System.out.println(subject + "\n" + expiration);
	}

	@Test
	void test(){
		CommonResult<Map> result = authController.auth("lzh", "123456");
		System.out.println(result);
	}
}
