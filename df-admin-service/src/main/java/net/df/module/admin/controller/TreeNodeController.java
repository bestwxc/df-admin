package net.df.module.admin.controller;

import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import net.df.module.admin.model.TreeNode;
import net.df.module.admin.service.TreeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class TreeNodeController {

    private Logger logger = LoggerFactory.getLogger(TreeNodeController.class);

    @Autowired
    private TreeNodeService treeNodeService;

    /**
     * 查询字典
     * @param map
     * @return
     */
    @RequestMapping("/tree/list")
    public Result<?> list(@RequestBody Map<String,?> map) {
        Integer resultType = MapUtils.getIntegerFromMap(map, "resultType", 1);
        Result<?> result = null;
        switch (resultType){
            case 1: result = listType(map);break;
            case 2: result = listTypes(map);break;
            default: break;
        }
        return result;
    }

    /**
     * 查询多种类型字典
     * @param map
     * @return
     */
    public Result<Map<String,List<TreeNode>>> listTypes(Map<String,?> map){
        List<String> treeNodePaths = MapUtils.getListFromMapNotNull(map, "treeNodePaths");
        Map<String,List<TreeNode>> result = new LinkedHashMap<>(treeNodePaths.size());
        treeNodePaths.forEach(treeNodePath -> {
            result.put(treeNodePath, treeNodeService.list(null, null, null, null, treeNodePath, null, null, null, null));
        });
        return ResultUtils.success(result);
    }

    /**
     * 查询单种类型字典
     * @param map
     * @return
     */
    public Result<List<TreeNode>> listType(Map<String,?> map){
        String treeNodePath = MapUtils.getStringFromMap(map, "treeNodePath","");
        Long parentId = MapUtils.getLongFromMap(map, "parentId",0l);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<TreeNode> result = treeNodeService.list(null, null, null, parentId, treeNodePath, null, flag, null, null);
        return ResultUtils.success(result);
    }
}
