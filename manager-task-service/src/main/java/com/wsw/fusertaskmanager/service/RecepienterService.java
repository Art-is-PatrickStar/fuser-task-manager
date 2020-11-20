package com.wsw.fusertaskmanager.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 13:51 2020/11/20
 * @Description:
 */
@FeignClient(value = "manager-recepienter-service")
public interface RecepienterService {

    @PostMapping("/recepient/create")
    int create(@RequestParam("name") String name, @RequestParam("remark") String remark);
}
