package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
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

    private WeekendSqlsUtils<Role> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param roleCode
     * @param roleName
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public Role add(String roleCode, String roleName, String description, Integer orderNum, Integer flag){
        Role role = new Role();
        setObject(role,roleCode,roleName,description,orderNum,flag);
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
     * @param orderNum
     * @param flag
     * @return
     */
    public Role update(Long id, String roleCode, String roleName, String description, Integer orderNum, Integer flag){
        Role role = roleMapper.selectByPrimaryKey(id);
        setObject(role,roleCode, roleName, description, orderNum, flag);
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
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Role> list(Long id, String roleCode, String roleName, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, roleCode, roleName, description, orderNum, flag, createTime, updateTime);
        return roleMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param roleCode
     * @param roleName
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public List<Role> list(String roleCode, String roleName, String description, Integer orderNum, Integer flag){
        return this.list(null, roleCode , roleName , description , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Role listOne(Long id){
        return roleMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param roleCode
     * @return
     */
    public Role listOne(String roleCode){
        return listOne(null, roleCode, null, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public Role listOne(Long id, String roleCode, String roleName, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, roleCode, roleName, description, orderNum, flag, createTime, updateTime);
        return roleMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param roleCode
     * @param roleName
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, String roleCode, String roleName, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, roleCode, roleName, description, orderNum, flag, createTime, updateTime);
        return roleMapper.deleteByExample(example);
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
        Role role = roleMapper.selectByPrimaryKey(id);
        List<Role> list = this.list(null, role.getRoleCode(), null, null, null, null, null, null);
        Role maxRole = list.stream().max((role1, role2) -> role1.getFlag() - role2.getFlag()).get();
        this.update(id, null, null, null, null, maxRole.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param roleCode
     * @param roleName
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(Role role, String roleCode, String roleName, String description, Integer orderNum, Integer flag){
        if(ValidateUtils.isNotEmptyString(roleCode)){
            role.setRoleCode(roleCode);
        }
        if(ValidateUtils.isNotEmptyString(roleName)){
            role.setRoleName(roleName);
        }
        if(ValidateUtils.isNotEmptyString(description)){
            role.setDescription(description);
        }
        if(ValidateUtils.notNull(orderNum)){
            role.setOrderNum(orderNum);
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
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String roleCode,String roleName,String description,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<Role> sqls = WeekendSqls.<Role>custom();
        sqlsUtils.appendSql(sqls, Role::getId, id);
        sqlsUtils.appendSql(sqls, Role::getRoleCode, roleCode);
        sqlsUtils.appendSql(sqls, Role::getRoleName, roleName);
        sqlsUtils.appendSql(sqls, Role::getDescription, description);
        sqlsUtils.appendSql(sqls, Role::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, Role::getFlag, flag);
        sqlsUtils.appendSql(sqls, Role::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, Role::getUpdateTime, updateTime);
        Example example = new Example.Builder(Role.class).where(sqls).build();
        return example;
    }
}
