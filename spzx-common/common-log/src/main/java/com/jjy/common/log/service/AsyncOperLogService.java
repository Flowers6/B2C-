package com.jjy.common.log.service;

import com.jjy.model.entity.system.SysOperLog;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 19:52
 */
public interface AsyncOperLogService {
    /**
     * 保存系统日志
     * @param sysOperLog
     */
    void saveSysOperLog(SysOperLog sysOperLog);
}
