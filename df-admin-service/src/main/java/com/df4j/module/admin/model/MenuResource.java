package com.df4j.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_menu_resource")
public class MenuResource {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 资源类型
     */
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 页面资源类型
     */
    @Column(name = "menu_resource_type")
    private Integer menuResourceType;

    /**
     * 排序值
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 删除标记
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
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取菜单ID
     *
     * @return menu_id - 菜单ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取资源类型
     *
     * @return resource_id - 资源类型
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源类型
     *
     * @param resourceId 资源类型
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取页面资源类型
     *
     * @return menu_resource_type - 页面资源类型
     */
    public Integer getMenuResourceType() {
        return menuResourceType;
    }

    /**
     * 设置页面资源类型
     *
     * @param menuResourceType 页面资源类型
     */
    public void setMenuResourceType(Integer menuResourceType) {
        this.menuResourceType = menuResourceType;
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
     * 获取删除标记
     *
     * @return flag - 删除标记
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置删除标记
     *
     * @param flag 删除标记
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