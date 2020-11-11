package com.wsw.fusertaskmanager.controller;

import com.wsw.fusertaskmanager.api.CommonResult;
import com.wsw.fusertaskmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 18:01 2020/10/27
 * @Description:
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(Long userId, BigDecimal money){
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
