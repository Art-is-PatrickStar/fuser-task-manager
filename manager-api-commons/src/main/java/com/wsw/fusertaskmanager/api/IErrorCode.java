package com.wsw.fusertaskmanager.api;

/**
 * @Author WangSongWen
 * @Date: Created in 14:59 2020/11/11
 * @Description: 封装API错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
