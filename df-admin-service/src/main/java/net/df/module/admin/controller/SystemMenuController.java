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
import net.df.module.admin.model.SystemMenu;
import net.df.module.admin.service.SystemMenuService;


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
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        Integer menuType = MapUtils.getIntegerFromMap(map, "menuType", null);
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<SystemMenu> list = systemMenuService.list(id, menuCode, menuName, menuType, menuIcon, parentMenu, jumpPath, orderNum, flag, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/menu/add")
    public Result<SystemMenu> add(@RequestBody Map<String,?> map){
        String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        Integer menuType = MapUtils.getIntegerFromMap(map, "menuType", null);
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        SystemMenu systemMenu = systemMenuService.add(menuCode, menuName, menuType, menuIcon, parentMenu, jumpPath, orderNum, flag);
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
        String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        Integer menuType = MapUtils.getIntegerFromMap(map, "menuType", null);
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        SystemMenu systemMenu = systemMenuService.update(id, menuCode, menuName, menuType, menuIcon, parentMenu, jumpPath, orderNum, flag);
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
