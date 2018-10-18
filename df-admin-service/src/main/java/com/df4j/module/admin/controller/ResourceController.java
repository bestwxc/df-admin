package com.df4j.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import com.df4j.module.admin.model.Resource;
import com.df4j.module.admin.service.ResourceService;


@RestController
@RequestMapping("/api/admin")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/resource/list")
    public Result<List<Resource>> list(@RequestBody Map<String,?> map){
        Long id = MapUtils.getLongFromMap(map, "id", null);
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<Resource> list = resourceService.list(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/resource/add")
    public Result<Resource> add(@RequestBody Map<String,?> map){
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Resource resource = resourceService.add(menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag);
        return ResultUtils.success(resource);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/resource/update")
    public Result<Resource> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Resource resource = resourceService.update(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag);
        return ResultUtils.success(resource);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/resource/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = resourceService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
