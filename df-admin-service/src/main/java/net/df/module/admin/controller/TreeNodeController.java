package net.df.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import net.df.module.admin.model.TreeNode;
import net.df.module.admin.service.TreeNodeService;


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
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String nodeValue = MapUtils.getStringFromMap(map, "nodeValue", null);
        String nodeName = MapUtils.getStringFromMap(map, "nodeName", null);
        Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        String treeNodePath = MapUtils.getStringFromMap(map, "treeNodePath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<TreeNode> list = treeNodeService.list(id, nodeValue, nodeName, parentId, treeNodePath, orderNum, flag, null, null);
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
