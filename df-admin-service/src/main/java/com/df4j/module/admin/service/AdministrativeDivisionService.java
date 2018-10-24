package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.AdministrativeDivisionMapper;
import com.df4j.module.admin.model.AdministrativeDivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class AdministrativeDivisionService {

    @Autowired
    private AdministrativeDivisionMapper administrativeDivisionMapper;

    private WeekendSqlsUtils<AdministrativeDivision> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @return
     */
    public AdministrativeDivision add(String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag){
        AdministrativeDivision administrativeDivision = new AdministrativeDivision();
        setObject(administrativeDivision,divisionCode,divisionName,parentId,parentDivisionCode,divisionLevel,levelAdjust,divisionType,orderNum,flag);
        Date now = new Date();
        administrativeDivision.setCreateTime(now);
        administrativeDivision.setUpdateTime(now);
        administrativeDivisionMapper.insert(administrativeDivision);
        return administrativeDivision;
    }


    /**
     * 更新
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @return
     */
    public AdministrativeDivision update(Long id, String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag){
        AdministrativeDivision administrativeDivision = administrativeDivisionMapper.selectByPrimaryKey(id);
        setObject(administrativeDivision,divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag);
        Date now = new Date();
        administrativeDivision.setUpdateTime(now);
        administrativeDivisionMapper.updateByPrimaryKey(administrativeDivision);
        return administrativeDivision;
    }

    /**
     * 查询
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<AdministrativeDivision> list(Long id, String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, createTime, updateTime);
        return administrativeDivisionMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<AdministrativeDivision> list(Field<Long> id, Field<String> divisionCode, Field<String> divisionName, Field<Long> parentId, Field<String> parentDivisionCode, Field<Integer> divisionLevel, Field<Integer> levelAdjust, Field<String> divisionType, Field<Integer> orderNum, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, createTime, updateTime);
        return administrativeDivisionMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @return
     */
    public List<AdministrativeDivision> list(String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag){
        return this.list(null, divisionCode , divisionName , parentId , parentDivisionCode , divisionLevel , levelAdjust , divisionType , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public AdministrativeDivision listOne(Long id){
        return administrativeDivisionMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param parentDivisionCode
     * @param divisionCode
     * @return
     */
    public AdministrativeDivision listOne(String parentDivisionCode, String divisionCode){
        return listOne(null, divisionCode, null, null, parentDivisionCode, null, null, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public AdministrativeDivision listOne(Long id, String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, createTime, updateTime);
        return administrativeDivisionMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, createTime, updateTime);
        return administrativeDivisionMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        AdministrativeDivision administrativeDivision = administrativeDivisionMapper.selectByPrimaryKey(id);
        List<AdministrativeDivision> list = this.list(null, administrativeDivision.getDivisionCode(), null, null, administrativeDivision.getParentDivisionCode(), null, null, null, null, null, null, null);
        AdministrativeDivision maxAdministrativeDivision = list.stream().max((administrativeDivision1, administrativeDivision2) -> administrativeDivision1.getFlag() - administrativeDivision2.getFlag()).get();
        this.update(id, null, null, null, null, null, null, null, null, maxAdministrativeDivision.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(AdministrativeDivision administrativeDivision, String divisionCode, String divisionName, Long parentId, String parentDivisionCode, Integer divisionLevel, Integer levelAdjust, String divisionType, Integer orderNum, Integer flag){
        if(ValidateUtils.isNotEmptyString(divisionCode)){
            administrativeDivision.setDivisionCode(divisionCode);
        }
        if(ValidateUtils.isNotEmptyString(divisionName)){
            administrativeDivision.setDivisionName(divisionName);
        }
        if(ValidateUtils.notNull(parentId)){
            administrativeDivision.setParentId(parentId);
        }
        if(ValidateUtils.isNotEmptyString(parentDivisionCode)){
            administrativeDivision.setParentDivisionCode(parentDivisionCode);
        }
        if(ValidateUtils.notNull(divisionLevel)){
            administrativeDivision.setDivisionLevel(divisionLevel);
        }
        if(ValidateUtils.notNull(levelAdjust)){
            administrativeDivision.setLevelAdjust(levelAdjust);
        }
        if(ValidateUtils.isNotEmptyString(divisionType)){
            administrativeDivision.setDivisionType(divisionType);
        }
        if(ValidateUtils.notNull(orderNum)){
            administrativeDivision.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            administrativeDivision.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param divisionCode
     * @param divisionName
     * @param parentId
     * @param parentDivisionCode
     * @param divisionLevel
     * @param levelAdjust
     * @param divisionType
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object divisionCode,Object divisionName,Object parentId,Object parentDivisionCode,Object divisionLevel,Object levelAdjust,Object divisionType,Object orderNum,Object flag,Object createTime,Object updateTime){
        WeekendSqls<AdministrativeDivision> sqls = WeekendSqls.<AdministrativeDivision>custom();
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getId, id);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getDivisionCode, divisionCode);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getDivisionName, divisionName);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getParentId, parentId);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getParentDivisionCode, parentDivisionCode);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getDivisionLevel, divisionLevel);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getLevelAdjust, levelAdjust);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getDivisionType, divisionType);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getFlag, flag);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, AdministrativeDivision::getUpdateTime, updateTime);
        Example example = new Example.Builder(AdministrativeDivision.class).where(sqls).build();
        return example;
    }
}
