package com.wsw.fusertaskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 15:18 2020/11/2
 * @Description: 本地消息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalMessage implements Serializable {
    private Long localMessageId;
    private String message;
    private Integer status;
}
