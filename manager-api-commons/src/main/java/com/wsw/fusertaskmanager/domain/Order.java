package com.wsw.fusertaskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 16:03 2020/10/27
 * @Description: 订单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;
}
