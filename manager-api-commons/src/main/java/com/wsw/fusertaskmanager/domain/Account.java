package com.wsw.fusertaskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/10/27
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private Long id;
    private Long userId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
