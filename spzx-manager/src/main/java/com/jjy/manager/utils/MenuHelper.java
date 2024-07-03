package com.jjy.manager.utils;

import com.jjy.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 11:43
 */
//封装树形菜单工具类
public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //完成封装的过程
        //创建一个新list接收封装好的结果集
        List<SysMenu> trees = new ArrayList<>();

        //先从第一层开始，找到递归入口
        for (SysMenu sysMenu : sysMenuList) {
            //getParentId为0，递归入口
            if (sysMenu.getParentId() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    //递归方法
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //重置children
        sysMenu.setChildren(new ArrayList<>());

        //遍历
        for (SysMenu children : sysMenuList) {
            //找到下级节点
            if (children.getParentId().longValue() == sysMenu.getId().longValue()) {
                //实现递归
                sysMenu.getChildren().add(findChildren(children, sysMenuList));
            }
        }
        return sysMenu;
    }
}
