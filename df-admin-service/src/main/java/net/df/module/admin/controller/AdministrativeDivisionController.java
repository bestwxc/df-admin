package net.df.module.admin.controller;

import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import net.df.module.admin.model.AdministrativeDivision;
import net.df.module.admin.service.AdministrativeDivisionService;
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
public class AdministrativeDivisionController {

    private Logger logger = LoggerFactory.getLogger(AdministrativeDivisionController.class);

    @Autowired
    private AdministrativeDivisionService administrativeDivisionService;

    private Integer ENABLED = 0;

    /**
     * 查询行政区域
     * @param map
     * @return
     */
    @RequestMapping("/division/list")
    public Result<List<AdministrativeDivision>> list(@RequestBody Map<String,?> map){
        Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        String parentDivisionCode = MapUtils.getStringFromMap(map, "parentDivisionCode", null);
        String divisionCode = MapUtils.getStringFromMap(map, "divisionCode", null);
        List<AdministrativeDivision> result = administrativeDivisionService.list(null, divisionCode, null, parentId, parentDivisionCode, null, null,null,null,ENABLED, null,null);
        return ResultUtils.success(result);
    }

}
