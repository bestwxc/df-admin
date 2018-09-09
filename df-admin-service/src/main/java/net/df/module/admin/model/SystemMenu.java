package net.df.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_system_menu")
public class SystemMenu {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单代码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单节点类型
     */
    @Column(name = "menu_type")
    private Integer menuType;

    /**
     * 菜单图标
     */
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 上级菜单
     */
    @Column(name = "parent_menu")
    private String parentMenu;

    /**
     * 跳转地址
     */
    @Column(name = "jump_path")
    private String jumpPath;

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
     * 获取菜单代码
     *
     * @return menu_code - 菜单代码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单代码
     *
     * @param menuCode 菜单代码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单节点类型
     *
     * @return menu_type - 菜单节点类型
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单节点类型
     *
     * @param menuType 菜单节点类型
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取菜单图标
     *
     * @return menu_icon - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取上级菜单
     *
     * @return parent_menu - 上级菜单
     */
    public String getParentMenu() {
        return parentMenu;
    }

    /**
     * 设置上级菜单
     *
     * @param parentMenu 上级菜单
     */
    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu == null ? null : parentMenu.trim();
    }

    /**
     * 获取跳转地址
     *
     * @return jump_path - 跳转地址
     */
    public String getJumpPath() {
        return jumpPath;
    }

    /**
     * 设置跳转地址
     *
     * @param jumpPath 跳转地址
     */
    public void setJumpPath(String jumpPath) {
        this.jumpPath = jumpPath == null ? null : jumpPath.trim();
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