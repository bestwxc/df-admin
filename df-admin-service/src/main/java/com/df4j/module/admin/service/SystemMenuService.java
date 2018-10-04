package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.mapper.SystemMenuMapper;
import com.df4j.module.admin.model.SystemMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class SystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;



    /**
     * 新增
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @return
     */
    public SystemMenu add(String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag){
        SystemMenu systemMenu = new SystemMenu();
        setObject(systemMenu,menuCode,menuName,menuIcon,parentMenu,jumpPath,orderNum,flag);
        Date now = new Date();
        systemMenu.setCreateTime(now);
        systemMenu.setUpdateTime(now);
        systemMenuMapper.insert(systemMenu);
        return systemMenu;
    }


    /**
     * 更新
     * @param id
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @return
     */
    public SystemMenu update(Long id, String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag){
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        setObject(systemMenu,menuCode,menuName,menuIcon,parentMenu,jumpPath,orderNum,flag);
        Date now = new Date();
        systemMenu.setUpdateTime(now);
        systemMenuMapper.updateByPrimaryKey(systemMenu);
        return systemMenu;
    }

    /**
     * 查询
     * @param id
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<SystemMenu> list(Long id,String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,menuCode,menuName,menuIcon,parentMenu,jumpPath,orderNum,flag,createTime,updateTime);
        return systemMenuMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public SystemMenu listOne(Long id,String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,menuCode,menuName,menuIcon,parentMenu,jumpPath,orderNum,flag,createTime,updateTime);
        return systemMenuMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,menuCode,menuName,menuIcon,parentMenu,jumpPath,orderNum,flag,createTime,updateTime);
        return systemMenuMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        List<SystemMenu> list = this.list(null, systemMenu.getMenuCode(), null, null, systemMenu.getParentMenu(), null, null, null, null, null);
        SystemMenu maxSystemMenu = list.stream().max((systemMenu1, systemMenu2) -> systemMenu1.getFlag() - systemMenu2.getFlag()).get();
        this.update(id, null, null, null, null, null, null, maxSystemMenu.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(SystemMenu systemMenu, String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag){
        if(ValidateUtils.isNotEmptyString(menuCode)){
            systemMenu.setMenuCode(menuCode);
        }
        if(ValidateUtils.isNotEmptyString(menuName)){
            systemMenu.setMenuName(menuName);
        }
        if(ValidateUtils.isNotEmptyString(menuIcon)){
            systemMenu.setMenuIcon(menuIcon);
        }
        if(ValidateUtils.isNotEmptyString(parentMenu)){
            systemMenu.setParentMenu(parentMenu);
        }
        if(ValidateUtils.isNotEmptyString(jumpPath)){
            systemMenu.setJumpPath(jumpPath);
        }
        if(ValidateUtils.notNull(orderNum)){
            systemMenu.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            systemMenu.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param menuCode
     * @param menuName
     * @param menuIcon
     * @param parentMenu
     * @param jumpPath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String menuCode,String menuName,String menuIcon,String parentMenu,String jumpPath,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<SystemMenu> sqls = WeekendSqls.<SystemMenu>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(SystemMenu::getId, id);
        }
        if(ValidateUtils.isNotEmptyString(menuCode)) {
            sqls.andEqualTo(SystemMenu::getMenuCode, menuCode);
        }
        if(ValidateUtils.isNotEmptyString(menuName)) {
            sqls.andEqualTo(SystemMenu::getMenuName, menuName);
        }
        if(ValidateUtils.isNotEmptyString(menuIcon)) {
            sqls.andEqualTo(SystemMenu::getMenuIcon, menuIcon);
        }
        if(ValidateUtils.isNotEmptyString(parentMenu)) {
            sqls.andEqualTo(SystemMenu::getParentMenu, parentMenu);
        }
        if(ValidateUtils.isNotEmptyString(jumpPath)) {
            sqls.andEqualTo(SystemMenu::getJumpPath, jumpPath);
        }
        if(ValidateUtils.notNull(orderNum)) {
            sqls.andEqualTo(SystemMenu::getOrderNum, orderNum);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(SystemMenu::getFlag, flag);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(SystemMenu::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(SystemMenu::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(SystemMenu.class).where(sqls).build();
        return example;
    }
}
