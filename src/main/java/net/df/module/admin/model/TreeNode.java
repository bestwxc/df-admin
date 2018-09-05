package net.df.module.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_base_tree_node")
public class TreeNode {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 节点值
     */
    @Column(name = "node_value")
    private String nodeValue;

    /**
     * 节点名称
     */
    @Column(name = "node_name")
    private String nodeName;

    /**
     * 父节点ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 节点路径
     */
    @Column(name = "tree_node_path")
    private String treeNodePath;

    /**
     * 排序值
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 状态值
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
     * 获取节点值
     *
     * @return node_value - 节点值
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * 设置节点值
     *
     * @param nodeValue 节点值
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue == null ? null : nodeValue.trim();
    }

    /**
     * 获取节点名称
     *
     * @return node_name - 节点名称
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置节点名称
     *
     * @param nodeName 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * 获取父节点ID
     *
     * @return parent_id - 父节点ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父节点ID
     *
     * @param parentId 父节点ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取节点路径
     *
     * @return tree_node_path - 节点路径
     */
    public String getTreeNodePath() {
        return treeNodePath;
    }

    /**
     * 设置节点路径
     *
     * @param treeNodePath 节点路径
     */
    public void setTreeNodePath(String treeNodePath) {
        this.treeNodePath = treeNodePath == null ? null : treeNodePath.trim();
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
     * 获取状态值
     *
     * @return flag - 状态值
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置状态值
     *
     * @param flag 状态值
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