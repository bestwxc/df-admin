package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.MenuResourceMapper;
import com.df4j.module.admin.model.MenuResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class MenuResourceService {

    @Autowired
    private MenuResourceMapper menuResourceMapper;

    private WeekendSqlsUtils<MenuResource> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @return
     */
    public MenuResource add(Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag){
        MenuResource menuResource = new MenuResource();
        setObject(menuResource,menuId,resourceId,menuResourceType,orderNum,flag);
        Date now = new Date();
        menuResource.setCreateTime(now);
        menuResource.setUpdateTime(now);
        menuResourceMapper.insert(menuResource);
        return menuResource;
    }


    /**
     * 更新
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @return
     */
    public MenuResource update(Long id, Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag){
        MenuResource menuResource = menuResourceMapper.selectByPrimaryKey(id);
        setObject(menuResource,menuId, resourceId, menuResourceType, orderNum, flag);
        Date now = new Date();
        menuResource.setUpdateTime(now);
        menuResourceMapper.updateByPrimaryKey(menuResource);
        return menuResource;
    }

    /**
     * 查询
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<MenuResource> list(Long id, Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceId, menuResourceType, orderNum, flag, createTime, updateTime);
        return menuResourceMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<MenuResource> list(Field<Long> id, Field<Long> menuId, Field<Long> resourceId, Field<Integer> menuResourceType, Field<Integer> orderNum, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, menuId, resourceId, menuResourceType, orderNum, flag, createTime, updateTime);
        return menuResourceMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @return
     */
    public List<MenuResource> list(Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag){
        return this.list(null, menuId , resourceId , menuResourceType , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public MenuResource listOne(Long id){
        return menuResourceMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param menuId
     * @param resourceId
     * @return
     */
    public MenuResource listOne(Long menuId, Long resourceId){
        return listOne(null, menuId, resourceId, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public MenuResource listOne(Long id, Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceId, menuResourceType, orderNum, flag, createTime, updateTime);
        return menuResourceMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceId, menuResourceType, orderNum, flag, createTime, updateTime);
        return menuResourceMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        MenuResource menuResource = menuResourceMapper.selectByPrimaryKey(id);
        List<MenuResource> list = this.list(null, menuResource.getMenuId(), menuResource.getResourceId(), null, null, null, null, null);
        MenuResource maxMenuResource = list.stream().max((menuResource1, menuResource2) -> menuResource1.getFlag() - menuResource2.getFlag()).get();
        this.update(id, null, null, null, null, maxMenuResource.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(MenuResource menuResource, Long menuId, Long resourceId, Integer menuResourceType, Integer orderNum, Integer flag){
        if(ValidateUtils.notNull(menuId)){
            menuResource.setMenuId(menuId);
        }
        if(ValidateUtils.notNull(resourceId)){
            menuResource.setResourceId(resourceId);
        }
        if(ValidateUtils.notNull(menuResourceType)){
            menuResource.setMenuResourceType(menuResourceType);
        }
        if(ValidateUtils.notNull(orderNum)){
            menuResource.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            menuResource.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param menuId
     * @param resourceId
     * @param menuResourceType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object menuId,Object resourceId,Object menuResourceType,Object orderNum,Object flag,Object createTime,Object updateTime){
        WeekendSqls<MenuResource> sqls = WeekendSqls.<MenuResource>custom();
        sqlsUtils.appendSql(sqls, MenuResource::getId, id);
        sqlsUtils.appendSql(sqls, MenuResource::getMenuId, menuId);
        sqlsUtils.appendSql(sqls, MenuResource::getResourceId, resourceId);
        sqlsUtils.appendSql(sqls, MenuResource::getMenuResourceType, menuResourceType);
        sqlsUtils.appendSql(sqls, MenuResource::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, MenuResource::getFlag, flag);
        sqlsUtils.appendSql(sqls, MenuResource::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, MenuResource::getUpdateTime, updateTime);
        Example example = new Example.Builder(MenuResource.class).where(sqls).build();
        return example;
    }
}
