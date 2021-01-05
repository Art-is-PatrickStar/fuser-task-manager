package com.wsw.fusertaskmanager.fallback;

import com.wsw.fusertaskmanager.client.RecepienterClient;
import feign.hystrix.FallbackFactory;

/**
 * @Author WangSongWen
 * @Date: Created in 13:45 2021/1/5
 * @Description:
 */
public class RecepienterClientFallBack implements FallbackFactory<RecepienterClient> {
    @Override
    public RecepienterClient create(Throwable throwable) {
        return null;
    }
}
