package com.wsw.fusertaskmanager.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * tester
 * @author 
 */
@Data
public class Tester implements Serializable {
    private Long id;

    private String name;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}