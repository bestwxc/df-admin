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
import net.df.module.admin.model.Resource;
import net.df.module.admin.service.ResourceService;


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
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        List<Resource> list = resourceService.list(id, resourceCode, resourcePath, resourceType, flag, description, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/resource/add")
    public Result<Resource> add(@RequestBody Map<String,?> map){
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Resource resource = resourceService.add(resourceCode, resourcePath, resourceType, flag, description);
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
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Resource resource = resourceService.update(id, resourceCode, resourcePath, resourceType, flag, description);
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
