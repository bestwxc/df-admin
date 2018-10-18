package com.df4j.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_department")
public class Department {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门代码
     */
    @Column(name = "department_code")
    private String departmentCode;

    /**
     * 部门名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 上级部门代码
     */
    @Column(name = "parent_department_code")
    private String parentDepartmentCode;

    /**
     * 部门描述
     */
    private String description;

    /**
     * 排序值
     */
    @Column(name = "order_num")
    private Integer orderNum;

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
     * 修改时间
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
     * 获取部门名称
     *
     * @return department_name - 部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置部门名称
     *
     * @param departmentName 部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 获取上级部门代码
     *
     * @return parent_department_code - 上级部门代码
     */
    public String getParentDepartmentCode() {
        return parentDepartmentCode;
    }

    /**
     * 设置上级部门代码
     *
     * @param parentDepartmentCode 上级部门代码
     */
    public void setParentDepartmentCode(String parentDepartmentCode) {
        this.parentDepartmentCode = parentDepartmentCode == null ? null : parentDepartmentCode.trim();
    }

    /**
     * 获取部门描述
     *
     * @return description - 部门描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置部门描述
     *
     * @param description 部门描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取排序值
     *
     * @return order_num - 排序值
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序值
     *
     * @param orderNum 排序值
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}