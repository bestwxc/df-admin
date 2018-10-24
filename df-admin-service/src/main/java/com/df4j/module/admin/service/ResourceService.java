package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.ResourceMapper;
import com.df4j.module.admin.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    private WeekendSqlsUtils<Resource> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public Resource add(Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag){
        Resource resource = new Resource();
        setObject(resource,menuId,resourceType,resourceCode,resourceName,resourcePath,description,orderNum,flag);
        Date now = new Date();
        resource.setCreateTime(now);
        resource.setUpdateTime(now);
        resourceMapper.insert(resource);
        return resource;
    }


    /**
     * 更新
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public Resource update(Long id, Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag){
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        setObject(resource,menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag);
        Date now = new Date();
        resource.setUpdateTime(now);
        resourceMapper.updateByPrimaryKey(resource);
        return resource;
    }

    /**
     * 查询
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Resource> list(Long id, Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, createTime, updateTime);
        return resourceMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Resource> list(Field<Long> id, Field<Long> menuId, Field<Integer> resourceType, Field<String> resourceCode, Field<String> resourceName, Field<String> resourcePath, Field<String> description, Field<Integer> orderNum, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, createTime, updateTime);
        return resourceMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public List<Resource> list(Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag){
        return this.list(null, menuId , resourceType , resourceCode , resourceName , resourcePath , description , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Resource listOne(Long id){
        return resourceMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param resourceCode
     * @return
     */
    public Resource listOne(String resourceCode){
        return listOne(null, null, null, resourceCode, null, null, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public Resource listOne(Long id, Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, createTime, updateTime);
        return resourceMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, createTime, updateTime);
        return resourceMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        List<Resource> list = this.list(null, null, null, resource.getResourceCode(), null, null, null, null, null, null, null);
        Resource maxResource = list.stream().max((resource1, resource2) -> resource1.getFlag() - resource2.getFlag()).get();
        this.update(id, null, null, null, null, null, null, null, maxResource.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(Resource resource, Long menuId, Integer resourceType, String resourceCode, String resourceName, String resourcePath, String description, Integer orderNum, Integer flag){
        if(ValidateUtils.notNull(menuId)){
            resource.setMenuId(menuId);
        }
        if(ValidateUtils.notNull(resourceType)){
            resource.setResourceType(resourceType);
        }
        if(ValidateUtils.isNotEmptyString(resourceCode)){
            resource.setResourceCode(resourceCode);
        }
        if(ValidateUtils.isNotEmptyString(resourceName)){
            resource.setResourceName(resourceName);
        }
        if(ValidateUtils.isNotEmptyString(resourcePath)){
            resource.setResourcePath(resourcePath);
        }
        if(ValidateUtils.isNotEmptyString(description)){
            resource.setDescription(description);
        }
        if(ValidateUtils.notNull(orderNum)){
            resource.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            resource.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param menuId
     * @param resourceType
     * @param resourceCode
     * @param resourceName
     * @param resourcePath
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object menuId,Object resourceType,Object resourceCode,Object resourceName,Object resourcePath,Object description,Object orderNum,Object flag,Object createTime,Object updateTime){
        WeekendSqls<Resource> sqls = WeekendSqls.<Resource>custom();
        sqlsUtils.appendSql(sqls, Resource::getId, id);
        sqlsUtils.appendSql(sqls, Resource::getMenuId, menuId);
        sqlsUtils.appendSql(sqls, Resource::getResourceType, resourceType);
        sqlsUtils.appendSql(sqls, Resource::getResourceCode, resourceCode);
        sqlsUtils.appendSql(sqls, Resource::getResourceName, resourceName);
        sqlsUtils.appendSql(sqls, Resource::getResourcePath, resourcePath);
        sqlsUtils.appendSql(sqls, Resource::getDescription, description);
        sqlsUtils.appendSql(sqls, Resource::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, Resource::getFlag, flag);
        sqlsUtils.appendSql(sqls, Resource::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, Resource::getUpdateTime, updateTime);
        Example example = new Example.Builder(Resource.class).where(sqls).build();
        return example;
    }
}
