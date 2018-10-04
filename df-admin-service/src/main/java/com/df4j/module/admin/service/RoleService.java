package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.mapper.RoleMapper;
import com.df4j.module.admin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;



    /**
     * 新增
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @return
     */
    public Role add(String roleCode,String roleName,String description,Integer flag){
        Role role = new Role();
        setObject(role,roleCode,roleName,description,flag);
        Date now = new Date();
        role.setCreateTime(now);
        role.setUpdateTime(now);
        roleMapper.insert(role);
        return role;
    }


    /**
     * 更新
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @return
     */
    public Role update(Long id, String roleCode,String roleName,String description,Integer flag){
        Role role = roleMapper.selectByPrimaryKey(id);
        setObject(role,roleCode,roleName,description,flag);
        Date now = new Date();
        role.setUpdateTime(now);
        roleMapper.updateByPrimaryKey(role);
        return role;
    }

    /**
     * 查询
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Role> list(Long id,String roleCode,String roleName,String description,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,description,flag,createTime,updateTime);
        return roleMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public Role listOne(Long id,String roleCode,String roleName,String description,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,description,flag,createTime,updateTime);
        return roleMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,String roleCode,String roleName,String description,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,description,flag,createTime,updateTime);
        return roleMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        Role role = roleMapper.selectByPrimaryKey(id);
        List<Role> list = this.list(null, role.getRoleCode(), null, null, null, null, null);
        Role maxRole = list.stream().max((role1, role2) -> role1.getFlag() - role2.getFlag()).get();
        this.update(id, null, null, null, maxRole.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @return
     */
    private void setObject(Role role, String roleCode,String roleName,String description,Integer flag){
        if(ValidateUtils.isNotEmptyString(roleCode)){
            role.setRoleCode(roleCode);
        }
        if(ValidateUtils.isNotEmptyString(roleName)){
            role.setRoleName(roleName);
        }
        if(ValidateUtils.isNotEmptyString(description)){
            role.setDescription(description);
        }
        if(ValidateUtils.notNull(flag)){
            role.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String roleCode,String roleName,String description,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<Role> sqls = WeekendSqls.<Role>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(Role::getId, id);
        }
        if(ValidateUtils.isNotEmptyString(roleCode)) {
            sqls.andEqualTo(Role::getRoleCode, roleCode);
        }
        if(ValidateUtils.isNotEmptyString(roleName)) {
            sqls.andEqualTo(Role::getRoleName, roleName);
        }
        if(ValidateUtils.isNotEmptyString(description)) {
            sqls.andEqualTo(Role::getDescription, description);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(Role::getFlag, flag);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(Role::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(Role::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(Role.class).where(sqls).build();
        return example;
    }
}
