package com.wsw.fusertaskmanager.service;

import com.wsw.fusertaskmanager.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 16:44 2020/10/27
 * @Description: 账户服务接口
 */
@FeignClient(value = "manager-account-service")
public interface AccountService {
    /**
     * feign服务调用 扣减账户接口
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
