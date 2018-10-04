package com.df4j.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_administrative_division")
public class AdministrativeDivision {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 区域代码
     */
    @Column(name = "division_code")
    private String divisionCode;

    /**
     * 区域名称
     */
    @Column(name = "division_name")
    private String divisionName;

    /**
     * 上级ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 上级区域代码
     */
    @Column(name = "parent_division_code")
    private String parentDivisionCode;

    /**
     * 区域级别
     */
    @Column(name = "division_level")
    private Integer divisionLevel;

    /**
     * 级别调整
     */
    @Column(name = "level_adjust")
    private Integer levelAdjust;

    /**
     * 区域代码
     */
    @Column(name = "division_type")
    private String divisionType;

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
     * 获取区域代码
     *
     * @return division_code - 区域代码
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     * 设置区域代码
     *
     * @param divisionCode 区域代码
     */
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode == null ? null : divisionCode.trim();
    }

    /**
     * 获取区域名称
     *
     * @return division_name - 区域名称
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * 设置区域名称
     *
     * @param divisionName 区域名称
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName == null ? null : divisionName.trim();
    }

    /**
     * 获取上级ID
     *
     * @return parent_id - 上级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置上级ID
     *
     * @param parentId 上级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取上级区域代码
     *
     * @return parent_division_code - 上级区域代码
     */
    public String getParentDivisionCode() {
        return parentDivisionCode;
    }

    /**
     * 设置上级区域代码
     *
     * @param parentDivisionCode 上级区域代码
     */
    public void setParentDivisionCode(String parentDivisionCode) {
        this.parentDivisionCode = parentDivisionCode == null ? null : parentDivisionCode.trim();
    }

    /**
     * 获取区域级别
     *
     * @return division_level - 区域级别
     */
    public Integer getDivisionLevel() {
        return divisionLevel;
    }

    /**
     * 设置区域级别
     *
     * @param divisionLevel 区域级别
     */
    public void setDivisionLevel(Integer divisionLevel) {
        this.divisionLevel = divisionLevel;
    }

    /**
     * 获取级别调整
     *
     * @return level_adjust - 级别调整
     */
    public Integer getLevelAdjust() {
        return levelAdjust;
    }

    /**
     * 设置级别调整
     *
     * @param levelAdjust 级别调整
     */
    public void setLevelAdjust(Integer levelAdjust) {
        this.levelAdjust = levelAdjust;
    }

    /**
     * 获取区域代码
     *
     * @return division_type - 区域代码
     */
    public String getDivisionType() {
        return divisionType;
    }

    /**
     * 设置区域代码
     *
     * @param divisionType 区域代码
     */
    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType == null ? null : divisionType.trim();
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