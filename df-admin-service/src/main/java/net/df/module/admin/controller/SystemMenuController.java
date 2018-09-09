package net.df.module.admin.controller;

import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import net.df.module.admin.model.SystemMenu;
import net.df.module.admin.service.SystemMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class SystemMenuController {

    private Logger logger = LoggerFactory.getLogger(SystemMenuController.class);

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/menu/list")
    public Result<List<SystemMenu>> list(@RequestBody Map<String,?> map){
        String parentMenu = MapUtils.getStringFromMap(map,"parentMenu",null);
        String menuCode = MapUtils.getStringFromMap(map,"menuCode",null);
        List<SystemMenu> list = systemMenuService.list(null, null, null, null, null, parentMenu, null, null, null, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/menu/add")
    public Result<SystemMenu> add(@RequestBody Map<String,?> map){
        String menuCode = MapUtils.getStringFromMapNotNull(map, "menuCode");
        String menuName = MapUtils.getStringFromMap(map, "menuName",null);
        Integer menuType = MapUtils.getIntegerFromMapNotNull(map, "menuType");
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon",null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu",null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath",null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", 0);
        SystemMenu systemMenu = systemMenuService.add(menuCode, menuName, menuType, menuIcon, parentMenu, jumpPath, orderNum, 0);
        return ResultUtils.success(systemMenu);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/menu/update")
    public Result<SystemMenu> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String menuCode = MapUtils.getStringFromMapNotNull(map, "menuCode");
        String menuName = MapUtils.getStringFromMap(map, "menuName",null);
        Integer menuType = MapUtils.getIntegerFromMapNotNull(map, "menuType");
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon",null);
        //String parentMenu = MapUtils.getStringFromMap(map, "parentMenu",null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath",null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", 0);
        SystemMenu systemMenu = systemMenuService.update(id, menuCode, menuName, menuType, menuIcon, null, jumpPath, orderNum, 0);
        return ResultUtils.success(systemMenu);
    }

    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/menu/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = systemMenuService.delete(id);
        return ResultUtils.success(null);
    }

}
