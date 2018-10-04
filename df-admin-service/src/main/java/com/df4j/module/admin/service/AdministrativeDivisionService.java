package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
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
    public AdministrativeDivision add(String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag){
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
    public AdministrativeDivision update(Long id, String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag){
        AdministrativeDivision administrativeDivision = administrativeDivisionMapper.selectByPrimaryKey(id);
        setObject(administrativeDivision,divisionCode,divisionName,parentId,parentDivisionCode,divisionLevel,levelAdjust,divisionType,orderNum,flag);
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
    public List<AdministrativeDivision> list(Long id,String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,divisionCode,divisionName,parentId,parentDivisionCode,divisionLevel,levelAdjust,divisionType,orderNum,flag,createTime,updateTime);
        return administrativeDivisionMapper.selectByExample(example);
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
    public AdministrativeDivision listOne(Long id,String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,divisionCode,divisionName,parentId,parentDivisionCode,divisionLevel,levelAdjust,divisionType,orderNum,flag,createTime,updateTime);
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
    public int delete(Long id,String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,divisionCode,divisionName,parentId,parentDivisionCode,divisionLevel,levelAdjust,divisionType,orderNum,flag,createTime,updateTime);
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
    private void setObject(AdministrativeDivision administrativeDivision, String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag){
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
    private Example getExample(Long id,String divisionCode,String divisionName,Long parentId,String parentDivisionCode,Integer divisionLevel,Integer levelAdjust,String divisionType,Integer orderNum,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<AdministrativeDivision> sqls = WeekendSqls.<AdministrativeDivision>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(AdministrativeDivision::getId, id);
        }
        if(ValidateUtils.isNotEmptyString(divisionCode)) {
            sqls.andEqualTo(AdministrativeDivision::getDivisionCode, divisionCode);
        }
        if(ValidateUtils.isNotEmptyString(divisionName)) {
            sqls.andEqualTo(AdministrativeDivision::getDivisionName, divisionName);
        }
        if(ValidateUtils.notNull(parentId)) {
            sqls.andEqualTo(AdministrativeDivision::getParentId, parentId);
        }
        if(ValidateUtils.isNotEmptyString(parentDivisionCode)) {
            sqls.andEqualTo(AdministrativeDivision::getParentDivisionCode, parentDivisionCode);
        }
        if(ValidateUtils.notNull(divisionLevel)) {
            sqls.andEqualTo(AdministrativeDivision::getDivisionLevel, divisionLevel);
        }
        if(ValidateUtils.notNull(levelAdjust)) {
            sqls.andEqualTo(AdministrativeDivision::getLevelAdjust, levelAdjust);
        }
        if(ValidateUtils.isNotEmptyString(divisionType)) {
            sqls.andEqualTo(AdministrativeDivision::getDivisionType, divisionType);
        }
        if(ValidateUtils.notNull(orderNum)) {
            sqls.andEqualTo(AdministrativeDivision::getOrderNum, orderNum);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(AdministrativeDivision::getFlag, flag);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(AdministrativeDivision::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(AdministrativeDivision::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(AdministrativeDivision.class).where(sqls).build();
        return example;
    }
}
