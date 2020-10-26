package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.dao.PaymentDao;
import com.wsw.fusertaskmanager.entities.Payment;
import com.wsw.fusertaskmanager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 10:50 2020/10/10
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
