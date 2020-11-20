package com.wsw.fusertaskmanager.service;

/**
 * @Author WangSongWen
 * @Date: Created in 13:35 2020/11/20
 * @Description:
 */
public interface TesterService {
    /**
     * 新增测试人员
     * @param name
     * @param remark
     * @return
     */
    int insert(Long taskId, String taskName, String name, String remark);
}
