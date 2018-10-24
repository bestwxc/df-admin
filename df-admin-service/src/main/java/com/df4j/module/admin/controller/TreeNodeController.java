package com.df4j.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.FieldUtils;
import com.df4j.base.utils.DateUtils;
import com.df4j.base.form.Field;
import com.df4j.base.form.BoundType;
import com.df4j.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.df4j.module.admin.model.TreeNode;
import com.df4j.module.admin.service.TreeNodeService;


@RestController
@RequestMapping("/api/admin")
public class TreeNodeController {

    private Logger logger = LoggerFactory.getLogger(TreeNodeController.class);

    @Autowired
    private TreeNodeService treeNodeService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/tree/list")
    public Result<List<TreeNode>> list(@RequestBody Map<String,?> map){
        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String nodeValue = MapUtils.getStringFromMap(map, "nodeValue", null);
        Field<String> nodeValue = FieldUtils.getStringField(map, "nodeValue", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String nodeName = MapUtils.getStringFromMap(map, "nodeName", null);
        Field<String> nodeName = FieldUtils.getStringField(map, "nodeName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        Field<Long> parentId = FieldUtils.getLongField(map, "parentId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String treeNodePath = MapUtils.getStringFromMap(map, "treeNodePath", null);
        Field<String> treeNodePath = FieldUtils.getStringField(map, "treeNodePath", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Field<Integer> orderNum = FieldUtils.getIntegerField(map, "orderNum", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Field<Integer> flag = FieldUtils.getIntegerField(map, "flag", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date createTime = MapUtils.getDateFromMap(map, "createTime", null);
        Field<Date> createTime = FieldUtils.getDateField(map, "createTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date updateTime = MapUtils.getDateFromMap(map, "updateTime", null);
        Field<Date> updateTime = FieldUtils.getDateField(map, "updateTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        List<TreeNode> list = treeNodeService.list(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/tree/add")
    public Result<TreeNode> add(@RequestBody Map<String,?> map){
        String nodeValue = MapUtils.getStringFromMap(map, "nodeValue", null);
        String nodeName = MapUtils.getStringFromMap(map, "nodeName", null);
        Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        String treeNodePath = MapUtils.getStringFromMap(map, "treeNodePath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        TreeNode treeNode = treeNodeService.add(nodeValue, nodeName, parentId, treeNodePath, orderNum, flag);
        return ResultUtils.success(treeNode);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/tree/update")
    public Result<TreeNode> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String nodeValue = MapUtils.getStringFromMap(map, "nodeValue", null);
        String nodeName = MapUtils.getStringFromMap(map, "nodeName", null);
        Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        String treeNodePath = MapUtils.getStringFromMap(map, "treeNodePath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        TreeNode treeNode = treeNodeService.update(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag);
        return ResultUtils.success(treeNode);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/tree/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = treeNodeService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
