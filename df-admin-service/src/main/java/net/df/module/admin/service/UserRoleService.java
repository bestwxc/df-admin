package net.df.module.admin.service;

import net.df.base.utils.ValidateUtils;
import net.df.module.admin.mapper.UserRoleMapper;
import net.df.module.admin.model.UserRole;
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


    /**
     * 新增
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole add(Long userId,Long roleId){
        UserRole userRole = new UserRole();
        setObject(userRole,userId,roleId);
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
     * @return
     */
    public UserRole update(Long id, Long userId,Long roleId){
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);
        setObject(userRole,userId,roleId);
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
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<UserRole> list(Long id,Long userId,Long roleId,Date createTime,Date updateTime){
        Example example = this.getExample(id,userId,roleId,createTime,updateTime);
        return userRoleMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param userId
     * @param roleId
     * @param createTime
     * @param updateTime
     * @return
     */
    public UserRole listOne(Long id,Long userId,Long roleId,Date createTime,Date updateTime){
        Example example = this.getExample(id,userId,roleId,createTime,updateTime);
        return userRoleMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param userId
     * @param roleId
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,Long userId,Long roleId,Date createTime,Date updateTime){
        Example example = this.getExample(id,userId,roleId,createTime,updateTime);
        return userRoleMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null);
    }


    /**
     * 组装更新数据
     * @param userId
     * @param roleId
     * @return
     */
    private void setObject(UserRole userRole, Long userId,Long roleId){
        if(ValidateUtils.notNull(userId)){
            userRole.setUserId(userId);
        }
        if(ValidateUtils.notNull(roleId)){
            userRole.setRoleId(roleId);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param userId
     * @param roleId
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,Long userId,Long roleId,Date createTime,Date updateTime){
        WeekendSqls<UserRole> sqls = WeekendSqls.<UserRole>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(UserRole::getId, id);
        }
        if(ValidateUtils.notNull(userId)) {
            sqls.andEqualTo(UserRole::getUserId, userId);
        }
        if(ValidateUtils.notNull(roleId)) {
            sqls.andEqualTo(UserRole::getRoleId, roleId);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(UserRole::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(UserRole::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(UserRole.class).where(sqls).build();
        return example;
    }
}
