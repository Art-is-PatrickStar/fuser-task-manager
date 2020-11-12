package com.wsw.fusertaskmanager.controller;

import com.wsw.fusertaskmanager.api.CommonResult;
import com.wsw.fusertaskmanager.domain.User;
import com.wsw.fusertaskmanager.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 17:47 2020/11/12
 * @Description:
 */
@RestController
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("/auth")
    public CommonResult auth(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = null;
        try {
            user = authService.auth(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != user){
            return CommonResult.success(user);
        }
        return CommonResult.failed();
    }
}
