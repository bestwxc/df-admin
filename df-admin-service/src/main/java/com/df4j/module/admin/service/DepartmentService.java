package com.df4j.module.admin.service;

import com.df4j.base.utils.ValidateUtils;
import com.df4j.boot.mybatis.utils.WeekendSqlsUtils;
import com.df4j.base.form.Field;
import com.df4j.module.admin.mapper.DepartmentMapper;
import com.df4j.module.admin.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    private WeekendSqlsUtils<Department> sqlsUtils = new WeekendSqlsUtils<>();



    /**
     * 新增
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public Department add(String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag){
        Department department = new Department();
        setObject(department,departmentCode,departmentName,parentDepartmentCode,description,orderNum,flag);
        Date now = new Date();
        department.setCreateTime(now);
        department.setUpdateTime(now);
        departmentMapper.insert(department);
        return department;
    }


    /**
     * 更新
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public Department update(Long id, String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag){
        Department department = departmentMapper.selectByPrimaryKey(id);
        setObject(department,departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag);
        Date now = new Date();
        department.setUpdateTime(now);
        departmentMapper.updateByPrimaryKey(department);
        return department;
    }

    /**
     * 查询
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Department> list(Long id, String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, createTime, updateTime);
        return departmentMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Department> list(Field<Long> id, Field<String> departmentCode, Field<String> departmentName, Field<String> parentDepartmentCode, Field<String> description, Field<Integer> orderNum, Field<Integer> flag, Field<Date> createTime, Field<Date> updateTime){
        Example example = this.getExample(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, createTime, updateTime);
        return departmentMapper.selectByExample(example);
    }

    /**
     * 查询
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    public List<Department> list(String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag){
        return this.list(null, departmentCode , departmentName , parentDepartmentCode , description , orderNum , flag  ,null, null);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Department listOne(Long id){
        return departmentMapper.selectByPrimaryKey(id);
    }
    /**
     * 查询一个
     * @param departmentCode
     * @return
     */
    public Department listOne(String departmentCode){
        return listOne(null, departmentCode, null, null, null, null, 0, null, null);
    }

    /**
     * 查询一个
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public Department listOne(Long id, String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, createTime, updateTime);
        return departmentMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id, String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag, Date createTime, Date updateTime){
        Example example = this.getExample(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, createTime, updateTime);
        return departmentMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        Department department = departmentMapper.selectByPrimaryKey(id);
        List<Department> list = this.list(null, department.getDepartmentCode(), null, null, null, null, null, null, null);
        Department maxDepartment = list.stream().max((department1, department2) -> department1.getFlag() - department2.getFlag()).get();
        this.update(id, null, null, null, null, null, maxDepartment.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @return
     */
    private void setObject(Department department, String departmentCode, String departmentName, String parentDepartmentCode, String description, Integer orderNum, Integer flag){
        if(ValidateUtils.isNotEmptyString(departmentCode)){
            department.setDepartmentCode(departmentCode);
        }
        if(ValidateUtils.isNotEmptyString(departmentName)){
            department.setDepartmentName(departmentName);
        }
        if(ValidateUtils.isNotEmptyString(parentDepartmentCode)){
            department.setParentDepartmentCode(parentDepartmentCode);
        }
        if(ValidateUtils.isNotEmptyString(description)){
            department.setDescription(description);
        }
        if(ValidateUtils.notNull(orderNum)){
            department.setOrderNum(orderNum);
        }
        if(ValidateUtils.notNull(flag)){
            department.setFlag(flag);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param description
     * @param orderNum
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Object id,Object departmentCode,Object departmentName,Object parentDepartmentCode,Object description,Object orderNum,Object flag,Object createTime,Object updateTime){
        WeekendSqls<Department> sqls = WeekendSqls.<Department>custom();
        sqlsUtils.appendSql(sqls, Department::getId, id);
        sqlsUtils.appendSql(sqls, Department::getDepartmentCode, departmentCode);
        sqlsUtils.appendSql(sqls, Department::getDepartmentName, departmentName);
        sqlsUtils.appendSql(sqls, Department::getParentDepartmentCode, parentDepartmentCode);
        sqlsUtils.appendSql(sqls, Department::getDescription, description);
        sqlsUtils.appendSql(sqls, Department::getOrderNum, orderNum);
        sqlsUtils.appendSql(sqls, Department::getFlag, flag);
        sqlsUtils.appendSql(sqls, Department::getCreateTime, createTime);
        sqlsUtils.appendSql(sqls, Department::getUpdateTime, updateTime);
        Example example = new Example.Builder(Department.class).where(sqls).build();
        return example;
    }
}
