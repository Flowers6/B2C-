package com.jjy.manager.mapper;

import com.jjy.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 19:54
 */
@Mapper
public interface SysOperLogMapper {
    /**
     * 保存日志
     * @param sysOperLog
     */
    void insert(SysOperLog sysOperLog);
}
