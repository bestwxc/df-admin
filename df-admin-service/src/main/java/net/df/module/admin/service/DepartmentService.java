package net.df.module.admin.service;

import net.df.base.utils.ValidateUtils;
import net.df.module.admin.mapper.DepartmentMapper;
import net.df.module.admin.model.Department;
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



    /**
     * 新增
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param flag
     * @return
     */
    public Department add(String departmentCode,String departmentName,String parentDepartmentCode,Integer flag){
        Department department = new Department();
        setObject(department,departmentCode,departmentName,parentDepartmentCode,flag);
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
     * @param flag
     * @return
     */
    public Department update(Long id, String departmentCode,String departmentName,String parentDepartmentCode,Integer flag){
        Department department = departmentMapper.selectByPrimaryKey(id);
        setObject(department,departmentCode,departmentName,parentDepartmentCode,flag);
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
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Department> list(Long id,String departmentCode,String departmentName,String parentDepartmentCode,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,departmentCode,departmentName,parentDepartmentCode,flag,createTime,updateTime);
        return departmentMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public Department listOne(Long id,String departmentCode,String departmentName,String parentDepartmentCode,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,departmentCode,departmentName,parentDepartmentCode,flag,createTime,updateTime);
        return departmentMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,String departmentCode,String departmentName,String parentDepartmentCode,Integer flag,Date createTime,Date updateTime){
        Example example = this.getExample(id,departmentCode,departmentName,parentDepartmentCode,flag,createTime,updateTime);
        return departmentMapper.deleteByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.delete( id, null, null, null, null, null, null);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int logicDelete(Long id){
        Department department = departmentMapper.selectByPrimaryKey(id);
        List<Department> list = this.list(null, department.getDepartmentCode(), null, null, null, null, null);
        Department maxDepartment = list.stream().max((department1, department2) -> department1.getFlag() - department2.getFlag()).get();
        this.update(id, null, null, null, maxDepartment.getFlag() + 1);
        return 1;
    }


    /**
     * 组装更新数据
     * @param departmentCode
     * @param departmentName
     * @param parentDepartmentCode
     * @param flag
     * @return
     */
    private void setObject(Department department, String departmentCode,String departmentName,String parentDepartmentCode,Integer flag){
        if(ValidateUtils.isNotEmptyString(departmentCode)){
            department.setDepartmentCode(departmentCode);
        }
        if(ValidateUtils.isNotEmptyString(departmentName)){
            department.setDepartmentName(departmentName);
        }
        if(ValidateUtils.isNotEmptyString(parentDepartmentCode)){
            department.setParentDepartmentCode(parentDepartmentCode);
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
     * @param flag
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String departmentCode,String departmentName,String parentDepartmentCode,Integer flag,Date createTime,Date updateTime){
        WeekendSqls<Department> sqls = WeekendSqls.<Department>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(Department::getId, id);
        }
        if(ValidateUtils.isNotEmptyString(departmentCode)) {
            sqls.andEqualTo(Department::getDepartmentCode, departmentCode);
        }
        if(ValidateUtils.isNotEmptyString(departmentName)) {
            sqls.andEqualTo(Department::getDepartmentName, departmentName);
        }
        if(ValidateUtils.isNotEmptyString(parentDepartmentCode)) {
            sqls.andEqualTo(Department::getParentDepartmentCode, parentDepartmentCode);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(Department::getFlag, flag);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(Department::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(Department::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(Department.class).where(sqls).build();
        return example;
    }
}
