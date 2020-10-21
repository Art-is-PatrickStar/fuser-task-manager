package com.wsw.fusertaskmanager.controller;

import com.oracle.tools.packager.Log;
import com.wsw.fusertaskmanager.entities.CommonResult;
import com.wsw.fusertaskmanager.entities.Payment;
import com.wsw.fusertaskmanager.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WangSongWen
 * @Date 2020/10/21 下午10:03
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/nacos/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
