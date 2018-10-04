package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.mapper.RoleResourceMapper;
import com.df4j.module.admin.model.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class RoleResourceService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;



    /**
     * 新增
     * @param roleId
     * @param resourceId
     * @param flag
     * @return
     */
    public RoleResource add(Long roleId,Long resourceId,Integer flag){
        RoleResource roleResource = new RoleResource();
        setObject(roleResource,roleId,resourceId,flag);
        Date now = new Date();
        roleResource.setCreateTime(now);
        roleResource.setUpdateTime(now);
        roleResourceMapper.insert(roleResource);
        return roleResource;
    }


    /**
     * 更新
     * @param id
     * @param roleId
     * @param resourceId
     * @param flag
     * @return
     */
    public RoleResource update(Long id, Long roleId,Long resourceId,Integer flag){
        RoleResource roleResource = roleResourceMapper.selectByPrimaryKey(id);
        setObject(roleResource,roleId,resourceId,flag);
        Date now = new Date();
        roleResource.setUpdateTime(now);
        roleResourceMapper.updateByPrimaryKey(roleResource);
        return roleResource;
    }

    /**
     * 查询
     * @param id
     * @param roleId
     * @param resourceId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<RoleResource> list(Long id,Long roleId,Long resourceId,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleId,resourceId,flag,createTime,updateTime);
        return roleResourceMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param roleId
     * @param resourceId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public RoleResource listOne(Long id,Long roleId,Long resourceId,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleId,resourceId,flag,createTime,updateTime);
        return roleResourceMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param roleId
     * @param resourceId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,Long roleId,Long resourceId,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleId,resourceId,flag,createTime,updateTime);
        return roleResourceMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        RoleResource roleResource = roleResourceMapper.selectByPrimaryKey(id);
        List<RoleResource> list = this.list(null, roleResource.getRoleId(), roleResource.getResourceId(), null, null, null);
        RoleResource maxRoleResource = list.stream().max((roleResource1, roleResource2) -> roleResource1.getFlag() - roleResource2.getFlag()).get();
        this.update(id, null, null, maxRoleResource.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param roleId
     * @param resourceId
     * @param flag
     * @return
     */
    private void setObject(RoleResource roleResource, Long roleId,Long resourceId,Integer flag){
        if(ValidateUtils.notNull(roleId)){
            roleResource.setRoleId(roleId);
        }
        if(ValidateUtils.notNull(resourceId)){
            roleResource.setResourceId(resourceId);
        }
        if(ValidateUtils.notNull(flag)){
            roleResource.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param roleId
     * @param resourceId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,Long roleId,Long resourceId,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<RoleResource> sqls = WeekendSqls.<RoleResource>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(RoleResource::getId, id);
        }
        if(ValidateUtils.notNull(roleId)) {
            sqls.andEqualTo(RoleResource::getRoleId, roleId);
        }
        if(ValidateUtils.notNull(resourceId)) {
            sqls.andEqualTo(RoleResource::getResourceId, resourceId);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(RoleResource::getFlag, flag);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(RoleResource::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(RoleResource::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(RoleResource.class).where(sqls).build();
        return example;
    }
}
