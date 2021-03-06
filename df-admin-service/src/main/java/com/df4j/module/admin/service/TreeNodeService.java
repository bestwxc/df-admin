package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.TreeNodeMapper;
import com.df4j.module.admin.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class TreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeMapper;

    private WeekendSqlsUtils<TreeNode> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @return
     */
    public TreeNode add(String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag){
        TreeNode treeNode = new TreeNode();
        setObject(treeNode,nodeValue,nodeName,parentId,treeNodePath,orderNum,flag);
        Date now = new Date();
        treeNode.setCreateTime(now);
        treeNode.setUpdateTime(now);
        treeNodeMapper.insert(treeNode);
        return treeNode;
    }


    /**
     * 更新
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @return
     */
    public TreeNode update(Long id, String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag){
        TreeNode treeNode = treeNodeMapper.selectByPrimaryKey(id);
        setObject(treeNode,nodeValue, nodeName, parentId, treeNodePath, orderNum, flag);
        Date now = new Date();
        treeNode.setUpdateTime(now);
        treeNodeMapper.updateByPrimaryKey(treeNode);
        return treeNode;
    }

    /**
     * 查询
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<TreeNode> list(Long id, String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, createTime, updateTime);
        return treeNodeMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<TreeNode> list(Field<Long> id, Field<String> nodeValue, Field<String> nodeName, Field<Long> parentId, Field<String> treeNodePath, Field<Integer> orderNum, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, createTime, updateTime);
        return treeNodeMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @return
     */
    public List<TreeNode> list(String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag){
        return this.list(null, nodeValue , nodeName , parentId , treeNodePath , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public TreeNode listOne(Long id){
        return treeNodeMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param parentId
     * @param nodeValue
     * @return
     */
    public TreeNode listOne(Long parentId, String nodeValue){
        return listOne(null, nodeValue, null, parentId, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public TreeNode listOne(Long id, String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, createTime, updateTime);
        return treeNodeMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, createTime, updateTime);
        return treeNodeMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        TreeNode treeNode = treeNodeMapper.selectByPrimaryKey(id);
        List<TreeNode> list = this.list(null, treeNode.getNodeValue(), null, treeNode.getParentId(), null, null, null, null, null);
        TreeNode maxTreeNode = list.stream().max((treeNode1, treeNode2) -> treeNode1.getFlag() - treeNode2.getFlag()).get();
        this.update(id, null, null, null, null, null, maxTreeNode.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(TreeNode treeNode, String nodeValue, String nodeName, Long parentId, String treeNodePath, Integer orderNum, Integer flag){
        if(ValidateUtils.isNotEmptyString(nodeValue)){
            treeNode.setNodeValue(nodeValue);
        }
        if(ValidateUtils.isNotEmptyString(nodeName)){
            treeNode.setNodeName(nodeName);
        }
        if(ValidateUtils.notNull(parentId)){
            treeNode.setParentId(parentId);
        }
        if(ValidateUtils.isNotEmptyString(treeNodePath)){
            treeNode.setTreeNodePath(treeNodePath);
        }
        if(ValidateUtils.notNull(orderNum)){
            treeNode.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            treeNode.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param nodeValue
     * @param nodeName
     * @param parentId
     * @param treeNodePath
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object nodeValue,Object nodeName,Object parentId,Object treeNodePath,Object orderNum,Object flag,Object createTime,Object updateTime){
        WeekendSqls<TreeNode> sqls = WeekendSqls.<TreeNode>custom();
        sqlsUtils.appendSql(sqls, TreeNode::getId, id);
        sqlsUtils.appendSql(sqls, TreeNode::getNodeValue, nodeValue);
        sqlsUtils.appendSql(sqls, TreeNode::getNodeName, nodeName);
        sqlsUtils.appendSql(sqls, TreeNode::getParentId, parentId);
        sqlsUtils.appendSql(sqls, TreeNode::getTreeNodePath, treeNodePath);
        sqlsUtils.appendSql(sqls, TreeNode::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, TreeNode::getFlag, flag);
        sqlsUtils.appendSql(sqls, TreeNode::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, TreeNode::getUpdateTime, updateTime);
        Example example = new Example.Builder(TreeNode.class).where(sqls).build();
        return example;
    }
}
