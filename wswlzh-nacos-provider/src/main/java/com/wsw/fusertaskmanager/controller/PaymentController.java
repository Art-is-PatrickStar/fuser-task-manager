package com.wsw.fusertaskmanager.controller;

import com.wsw.fusertaskmanager.entities.CommonResult;
import com.wsw.fusertaskmanager.entities.Payment;
import com.wsw.fusertaskmanager.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WangSongWen
 * @Date: Created in 17:44 2020/10/21
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/nacos/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果: " + payment);

        if (null != payment){
            return new CommonResult(200, "查询数据成功, serverPort: " + serverPort, payment);
        }else {
            return new CommonResult(444, "查询数据失败, id = " + id, null);
        }
    }
}
