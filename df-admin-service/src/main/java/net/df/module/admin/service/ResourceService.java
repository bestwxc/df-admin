package net.df.module.admin.service;

import net.df.base.utils.ValidateUtils;
import net.df.module.admin.mapper.ResourceMapper;
import net.df.module.admin.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import java.util.Date;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;



    /**
     * 新增
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @return
     */
    public Resource add(String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description){
        Resource resource = new Resource();
        setObject(resource,resourceName,resourceCode,resourcePath,resourceType,flag,description);
        Date now = new Date();
        resource.setCreateTime(now);
        resource.setUpdateTime(now);
        resourceMapper.insert(resource);
        return resource;
    }


    /**
     * 更新
     * @param id
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @return
     */
    public Resource update(Long id, String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description){
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        setObject(resource,resourceName,resourceCode,resourcePath,resourceType,flag,description);
        Date now = new Date();
        resource.setUpdateTime(now);
        resourceMapper.updateByPrimaryKey(resource);
        return resource;
    }

    /**
     * 查询
     * @param id
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @param createTime
     * @param updateTime
     * @return
     */
    public List<Resource> list(Long id,String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description,Date createTime,Date updateTime){
        Example example = this.getExample(id,resourceName,resourceCode,resourcePath,resourceType,flag,description,createTime,updateTime);
        return resourceMapper.selectByExample(example);
    }

    /**
     * 查询一个
     * @param id
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @param createTime
     * @param updateTime
     * @return
     */
    public Resource listOne(Long id,String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description,Date createTime,Date updateTime){
        Example example = this.getExample(id,resourceName,resourceCode,resourcePath,resourceType,flag,description,createTime,updateTime);
        return resourceMapper.selectOneByExample(example);
    }


    /**
     * 删除
     * @param id
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @param createTime
     * @param updateTime
     * @return
     */
    public int delete(Long id,String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description,Date createTime,Date updateTime){
        Example example = this.getExample(id,resourceName,resourceCode,resourcePath,resourceType,flag,description,createTime,updateTime);
        return resourceMapper.deleteByExample(example);
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
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        List<Resource> list = this.list(null, null, resource.getResourceCode(), null, null, null, null, null, null);
        Resource maxResource = list.stream().max((resource1, resource2) -> resource1.getFlag() - resource2.getFlag()).get();
        this.update(id, null, null, null, null, maxResource.getFlag() + 1, null);
        return 1;
    }


    /**
     * 组装更新数据
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @return
     */
    private void setObject(Resource resource, String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description){
        if(ValidateUtils.isNotEmptyString(resourceName)){
            resource.setResourceName(resourceName);
        }
        if(ValidateUtils.isNotEmptyString(resourceCode)){
            resource.setResourceCode(resourceCode);
        }
        if(ValidateUtils.isNotEmptyString(resourcePath)){
            resource.setResourcePath(resourcePath);
        }
        if(ValidateUtils.notNull(resourceType)){
            resource.setResourceType(resourceType);
        }
        if(ValidateUtils.notNull(flag)){
            resource.setFlag(flag);
        }
        if(ValidateUtils.isNotEmptyString(description)){
            resource.setDescription(description);
        }
    }

    /**
     * 组装Example
     * @param id
     * @param resourceName
     * @param resourceCode
     * @param resourcePath
     * @param resourceType
     * @param flag
     * @param description
     * @param createTime
     * @param updateTime
     * @return
     */
    private Example getExample(Long id,String resourceName,String resourceCode,String resourcePath,Integer resourceType,Integer flag,String description,Date createTime,Date updateTime){
        WeekendSqls<Resource> sqls = WeekendSqls.<Resource>custom();
        if(ValidateUtils.notNull(id)) {
            sqls.andEqualTo(Resource::getId, id);
        }
        if(ValidateUtils.isNotEmptyString(resourceName)) {
            sqls.andEqualTo(Resource::getResourceName, resourceName);
        }
        if(ValidateUtils.isNotEmptyString(resourceCode)) {
            sqls.andEqualTo(Resource::getResourceCode, resourceCode);
        }
        if(ValidateUtils.isNotEmptyString(resourcePath)) {
            sqls.andEqualTo(Resource::getResourcePath, resourcePath);
        }
        if(ValidateUtils.notNull(resourceType)) {
            sqls.andEqualTo(Resource::getResourceType, resourceType);
        }
        if(ValidateUtils.notNull(flag)) {
            sqls.andEqualTo(Resource::getFlag, flag);
        }
        if(ValidateUtils.isNotEmptyString(description)) {
            sqls.andEqualTo(Resource::getDescription, description);
        }
        if(ValidateUtils.notNull(createTime)) {
            sqls.andEqualTo(Resource::getCreateTime, createTime);
        }
        if(ValidateUtils.notNull(updateTime)) {
            sqls.andEqualTo(Resource::getUpdateTime, updateTime);
        }
        Example example = new Example.Builder(Resource.class).where(sqls).build();
        return example;
    }
}
