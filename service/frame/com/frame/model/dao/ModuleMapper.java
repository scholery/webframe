package com.frame.model.dao;

import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ModuleMapper {
    int countByExample(ModuleCriteria example);

    int deleteByExample(ModuleCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExampleWithBLOBsWithRowbounds(ModuleCriteria example, RowBounds rowBounds);

    List<Module> selectByExampleWithBLOBs(ModuleCriteria example);

    List<Module> selectByExampleWithRowbounds(ModuleCriteria example, RowBounds rowBounds);

    List<Module> selectByExample(ModuleCriteria example);

    Module selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Module record, @Param("example") ModuleCriteria example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleCriteria example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKeyWithBLOBs(Module record);

    int updateByPrimaryKey(Module record);
}