package com.jjy.manager.service.impl;

import com.jjy.common.exception.CustomException;
import com.jjy.manager.mapper.SysMenuMapper;
import com.jjy.manager.mapper.SysRoleMenuMapper;
import com.jjy.model.entity.system.SysMenu;
import com.jjy.model.entity.system.SysUser;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.system.SysMenuVo;
import com.jjy.manager.service.SysMenuService;
import com.jjy.manager.utils.AuthContextUtil;
import com.jjy.manager.utils.MenuHelper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

import static com.jjy.manager.utils.MenuHelper.buildTree;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 11:11
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //查询所有菜单，返回list集合
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }

        //调用工具类，把list集合封装为要求格式
        List<SysMenu> treeList = buildTree(sysMenuList);

        //返回
        return treeList;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
        //当添加新子菜单时，设置其父菜单的is_half = 1
        updateSysRoleMenu(sysMenu);
    }

    private void updateSysRoleMenu(SysMenu sysMenu) {

        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());

        if(parentMenu != null) {

            // 将该id的菜单设置为半开is_half = 1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId()) ;

            // 递归调用
            updateSysRoleMenu(parentMenu) ;

        }

    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        //根据当前菜单id查询是否包含子菜单
        int count = sysMenuMapper.selectCountById(id);

        //判断，count > 0 包含子菜单，不能删除
        if (count > 0) {
            throw new CustomException(ResultCodeEnum.NODE_ERROR);
        }

        //count = 0 直接删除
        sysMenuMapper.delete(id);
    }

    @Override
    public List<SysMenuVo> findMenuByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        //根据id查询可操作的菜单
        //封装要求数据格式，返回
        List<SysMenu> sysMenuList = buildTree(sysMenuMapper.findMenuByUserId(userId));

        List<SysMenuVo> sysMenuVos = this.buildMenus(sysMenuList);

        return sysMenuVos;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
