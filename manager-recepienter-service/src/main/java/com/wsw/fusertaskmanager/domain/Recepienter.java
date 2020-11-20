package com.wsw.fusertaskmanager.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * recepienter
 * @author 
 */
@Data
public class Recepienter implements Serializable {
    private Long id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;
}