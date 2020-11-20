package com.wsw.fusertaskmanager.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tester implements Serializable {
    private Long id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;
}