package com.wsw.fusertaskmanager.service;

import com.wsw.fusertaskmanager.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 16:45 2020/10/27
 * @Description: 库存服务接口
 */
@FeignClient(value = "manager-storage-service")
public interface StorageService {
    /**
     * feign服务调用 减库存接口
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
