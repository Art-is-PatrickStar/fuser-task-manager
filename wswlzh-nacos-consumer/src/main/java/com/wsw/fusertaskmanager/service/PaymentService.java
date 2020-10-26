package com.wsw.fusertaskmanager.service;

import com.wsw.fusertaskmanager.entities.CommonResult;
import com.wsw.fusertaskmanager.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author WangSongWen
 * @Date 2020/10/21 下午10:01
 * @Description: 使用openFeign进行服务调用
 */
@Service
@FeignClient(value = "nacos-payment-provider", fallback = FallBackPaymentService.class)
public interface PaymentService {
    @GetMapping("/nacos/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
