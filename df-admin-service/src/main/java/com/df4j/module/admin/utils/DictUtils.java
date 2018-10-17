package com.df4j.module.admin.utils;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.model.TreeNode;
import com.df4j.module.admin.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DictUtils {
    @Autowired
    private TreeNodeService treeNodeService;

    public List<TreeNode> getDictsByType(String type){
        List<TreeNode> list = treeNodeService.list(null, null, null, null, type, null, 0, null, null);
        return list;
    }

    public String getDictName(String type, String value){
        List<TreeNode> list = treeNodeService.list(null, value, null, null, type, null, 0, null, null);
        if(ValidateUtils.notEmpty(list) && list.size() == 1){
            return list.get(0).getNodeName();
        }
        return null;
    }
}
