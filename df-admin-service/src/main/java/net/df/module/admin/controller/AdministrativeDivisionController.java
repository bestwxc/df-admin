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
import net.df.module.admin.model.AdministrativeDivision;
import net.df.module.admin.service.AdministrativeDivisionService;


@RestController
@RequestMapping("/api/admin")
public class AdministrativeDivisionController {

    private Logger logger = LoggerFactory.getLogger(AdministrativeDivisionController.class);

    @Autowired
    private AdministrativeDivisionService administrativeDivisionService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/division/list")
    public Result<List<AdministrativeDivision>> list(@RequestBody Map<String,?> map){
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String divisionCode = MapUtils.getStringFromMap(map, "divisionCode", null);
        String divisionName = MapUtils.getStringFromMap(map, "divisionName", null);
        Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        String parentDivisionCode = MapUtils.getStringFromMap(map, "parentDivisionCode", null);
        Integer divisionLevel = MapUtils.getIntegerFromMap(map, "divisionLevel", null);
        Integer levelAdjust = MapUtils.getIntegerFromMap(map, "levelAdjust", null);
        String divisionType = MapUtils.getStringFromMap(map, "divisionType", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<AdministrativeDivision> list = administrativeDivisionService.list(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, null, null);
        return ResultUtils.success(list);
    }




}
