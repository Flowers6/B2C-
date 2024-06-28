package com.jjy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/28
 * @time : 15:56
 */
@Mapper
public interface SysRoleUserMapper {
    /**
     * 删除对应用户id的角色
     * @param userId
     */
    void deleteByUserId(Long userId);

    /**
     * 为用户id分配角色id
     * @param userId
     * @param roleId
     */
    void doAssign(Long userId, Long roleId);

    /**
     * //根据用户id查询用户分配过的角色id列表
     * @param userId
     * @return
     */
    List<Long> selectRoleIdsByUserId(Long userId);
}
