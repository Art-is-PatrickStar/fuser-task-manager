package com.wsw.fusertaskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 17:20 2020/10/27
 * @Description: 库存实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage implements Serializable {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
