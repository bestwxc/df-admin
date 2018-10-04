package com.df4j.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_user")
public class User {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 手机号码
     */
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * 用户状态
     */
    @Column(name = "user_state")
    private Integer userState;

    /**
     * 用户密码
     */
    @Column(name = "user_pass")
    private String userPass;

    /**
     * salt
     */
    private String salt;

    /**
     * 部门代码
     */
    @Column(name = "department_code")
    private String departmentCode;

    /**
     * 状态
     */
    private Integer flag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile_no - 手机号码
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置手机号码
     *
     * @param mobileNo 手机号码
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     * 获取用户状态
     *
     * @return user_state - 用户状态
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * 设置用户状态
     *
     * @param userState 用户状态
     */
    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    /**
     * 获取用户密码
     *
     * @return user_pass - 用户密码
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * 设置用户密码
     *
     * @param userPass 用户密码
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    /**
     * 获取salt
     *
     * @return salt - salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置salt
     *
     * @param salt salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取部门代码
     *
     * @return department_code - 部门代码
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置部门代码
     *
     * @param departmentCode 部门代码
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    /**
     * 获取状态
     *
     * @return flag - 状态
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置状态
     *
     * @param flag 状态
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}