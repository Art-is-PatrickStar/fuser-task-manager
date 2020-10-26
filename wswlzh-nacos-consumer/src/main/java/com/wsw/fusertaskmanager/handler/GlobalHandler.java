package com.wsw.fusertaskmanager.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wsw.fusertaskmanager.entities.CommonResult;

/**
 * @Author WangSongWen
 * @Date: Created in 14:31 2020/10/26
 * @Description:
 */
public class GlobalHandler {
    public static CommonResult blockHandler1(BlockException exception){
        return new CommonResult(444, "获取资源失败: " + exception.getMessage().toString());
    }
}
