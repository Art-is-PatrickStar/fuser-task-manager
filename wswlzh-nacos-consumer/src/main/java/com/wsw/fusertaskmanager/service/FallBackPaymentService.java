package com.wsw.fusertaskmanager.service;

import com.wsw.fusertaskmanager.entities.CommonResult;
import com.wsw.fusertaskmanager.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author WangSongWen
 * @Date: Created in 15:33 2020/10/26
 * @Description:
 */
@Component
public class FallBackPaymentService implements PaymentService{
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult(444, "服务降级", new Payment(id, "errorSerial"));
    }
}
