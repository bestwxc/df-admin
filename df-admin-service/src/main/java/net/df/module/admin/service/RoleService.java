package net.df.module.admin.service;

import net.df.base.utils.ValidateUtils;
import net.df.module.admin.mapper.RoleMapper;
import net.df.module.admin.model.Role;
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
     * @param departmentId
     * @return
     */
    public Role add(String roleCode,String roleName,Long departmentId){
        Role role = new Role();
        setObject(role,roleCode,roleName,departmentId);
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
     * @param departmentId
     * @return
     */
    public Role update(Long id, String roleCode,String roleName,Long departmentId){
        Role role = roleMapper.selectByPrimaryKey(id);
        setObject(role,roleCode,roleName,departmentId);
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
     * @param departmentId
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Role> list(Long id,String roleCode,String roleName,Long departmentId,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,departmentId,createTime,updateTime);
        return roleMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param roleCode
     * @param roleName
     * @param departmentId
     * @param createTime
     * @param updateTime
     * @return
     */
    public Role listOne(Long id,String roleCode,String roleName,Long departmentId,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,departmentId,createTime,updateTime);
        return roleMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param roleCode
     * @param roleName
     * @param departmentId
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,String roleCode,String roleName,Long departmentId,Date createTime,Date updateTime){
        Example example = this.getExample(id,roleCode,roleName,departmentId,createTime,updateTime);
        return roleMapper.deleteByExample(example);
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
     * 组装更新数据
     * @param roleCode
     * @param roleName
     * @param departmentId
     * @return
     */
    private void setObject(Role role, String roleCode,String roleName,Long departmentId){
        if(ValidateUtils.isNotEmptyString(roleCode)){
            role.setRoleCode(roleCode);
        }
        if(ValidateUtils.isNotEmptyString(roleName)){
            role.setRoleName(roleName);
        }
        if(ValidateUtils.notNull(departmentId)){
            role.setDepartmentId(departmentId);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param roleCode
     * @param roleName
     * @param departmentId
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String roleCode,String roleName,Long departmentId,Date createTime,Date updateTime){
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
        if(ValidateUtils.notNull(departmentId)) {
            sqls.andEqualTo(Role::getDepartmentId, departmentId);
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
