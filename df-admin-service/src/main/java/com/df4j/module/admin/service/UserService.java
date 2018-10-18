package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.module.admin.mapper.UserMapper;
import com.df4j.module.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private WeekendSqlsUtils<User> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @return
     */
    public User add(String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag){
        User user = new User();
        setObject(user,userName,nickName,mobileNo,userState,userPass,salt,departmentCode,flag);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.insert(user);
        return user;
    }


    /**
     * 更新
     * @param id
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @return
     */
    public User update(Long id, String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag){
        User user = userMapper.selectByPrimaryKey(id);
        setObject(user,userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag);
        Date now = new Date();
        user.setUpdateTime(now);
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    /**
     * 查询
     * @param id
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<User> list(Long id, String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag, createTime, updateTime);
        return userMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @return
     */
    public List<User> list(String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag){
        return this.list(null, userName , nickName , mobileNo , userState , userPass , salt , departmentCode , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public User listOne(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param userName
     * @return
     */
    public User listOne(String userName){
        return listOne(null, userName, null, null, null, null, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public User listOne(Long id, String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag, createTime, updateTime);
        return userMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag, createTime, updateTime);
        return userMapper.deleteByExample(example);
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
        User user = userMapper.selectByPrimaryKey(id);
        List<User> list = this.list(null, user.getUserName(), null, null, null, null, null, null, null, null, null);
        User maxUser = list.stream().max((user1, user2) -> user1.getFlag() - user2.getFlag()).get();
        this.update(id, null, null, null, null, null, null, null, maxUser.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @return
     */
    private void setObject(User user, String userName, String nickName, String mobileNo, Integer userState, String userPass, String salt, String departmentCode, Integer flag){
        if(ValidateUtils.isNotEmptyString(userName)){
            user.setUserName(userName);
        }
        if(ValidateUtils.isNotEmptyString(nickName)){
            user.setNickName(nickName);
        }
        if(ValidateUtils.isNotEmptyString(mobileNo)){
            user.setMobileNo(mobileNo);
        }
        if(ValidateUtils.notNull(userState)){
            user.setUserState(userState);
        }
        if(ValidateUtils.isNotEmptyString(userPass)){
            user.setUserPass(userPass);
        }
        if(ValidateUtils.isNotEmptyString(salt)){
            user.setSalt(salt);
        }
        if(ValidateUtils.isNotEmptyString(departmentCode)){
            user.setDepartmentCode(departmentCode);
        }
        if(ValidateUtils.notNull(flag)){
            user.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param userName
     * @param nickName
     * @param mobileNo
     * @param userState
     * @param userPass
     * @param salt
     * @param departmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String userName,String nickName,String mobileNo,Integer userState,String userPass,String salt,String departmentCode,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<User> sqls = WeekendSqls.<User>custom();
        sqlsUtils.appendSql(sqls, User::getId, id);
        sqlsUtils.appendSql(sqls, User::getUserName, userName);
        sqlsUtils.appendSql(sqls, User::getNickName, nickName);
        sqlsUtils.appendSql(sqls, User::getMobileNo, mobileNo);
        sqlsUtils.appendSql(sqls, User::getUserState, userState);
        sqlsUtils.appendSql(sqls, User::getUserPass, userPass);
        sqlsUtils.appendSql(sqls, User::getSalt, salt);
        sqlsUtils.appendSql(sqls, User::getDepartmentCode, departmentCode);
        sqlsUtils.appendSql(sqls, User::getFlag, flag);
        sqlsUtils.appendSql(sqls, User::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, User::getUpdateTime, updateTime);
        Example example = new Example.Builder(User.class).where(sqls).build();
        return example;
    }
}
