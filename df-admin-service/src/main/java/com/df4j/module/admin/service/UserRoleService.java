package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.UserRoleMapper;
import com.df4j.module.admin.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    private WeekendSqlsUtils<UserRole> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param userId
     * @param roleId
     * @param flag
     * @return
     */
    public UserRole add(Long userId, Long roleId, Integer flag){
        UserRole userRole = new UserRole();
        setObject(userRole,userId,roleId,flag);
        Date now = new Date();
        userRole.setCreateTime(now);
        userRole.setUpdateTime(now);
        userRoleMapper.insert(userRole);
        return userRole;
    }


    /**
     * 更新
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @return
     */
    public UserRole update(Long id, Long userId, Long roleId, Integer flag){
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);
        setObject(userRole,userId, roleId, flag);
        Date now = new Date();
        userRole.setUpdateTime(now);
        userRoleMapper.updateByPrimaryKey(userRole);
        return userRole;
    }

    /**
     * 查询
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<UserRole> list(Long id, Long userId, Long roleId, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userId, roleId, flag, createTime, updateTime);
        return userRoleMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<UserRole> list(Field<Long> id, Field<Long> userId, Field<Long> roleId, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, userId, roleId, flag, createTime, updateTime);
        return userRoleMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param userId
     * @param roleId
     * @param flag
     * @return
     */
    public List<UserRole> list(Long userId, Long roleId, Integer flag){
        return this.list(null, userId , roleId , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public UserRole listOne(Long id){
        return userRoleMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole listOne(Long userId, Long roleId){
        return listOne(null, userId, roleId, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public UserRole listOne(Long id, Long userId, Long roleId, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userId, roleId, flag, createTime, updateTime);
        return userRoleMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, Long userId, Long roleId, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userId, roleId, flag, createTime, updateTime);
        return userRoleMapper.deleteByExample(example);
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
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);
        List<UserRole> list = this.list(null, userRole.getUserId(), userRole.getRoleId(), null, null, null);
        UserRole maxUserRole = list.stream().max((userRole1, userRole2) -> userRole1.getFlag() - userRole2.getFlag()).get();
        this.update(id, null, null, maxUserRole.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param userId
     * @param roleId
     * @param flag
     * @return
     */
    private void setObject(UserRole userRole, Long userId, Long roleId, Integer flag){
        if(ValidateUtils.notNull(userId)){
            userRole.setUserId(userId);
        }
        if(ValidateUtils.notNull(roleId)){
            userRole.setRoleId(roleId);
        }
        if(ValidateUtils.notNull(flag)){
            userRole.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param userId
     * @param roleId
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object userId,Object roleId,Object flag,Object createTime,Object updateTime){
        WeekendSqls<UserRole> sqls = WeekendSqls.<UserRole>custom();
        sqlsUtils.appendSql(sqls, UserRole::getId, id);
        sqlsUtils.appendSql(sqls, UserRole::getUserId, userId);
        sqlsUtils.appendSql(sqls, UserRole::getRoleId, roleId);
        sqlsUtils.appendSql(sqls, UserRole::getFlag, flag);
        sqlsUtils.appendSql(sqls, UserRole::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, UserRole::getUpdateTime, updateTime);
        Example example = new Example.Builder(UserRole.class).where(sqls).build();
        return example;
    }
}
