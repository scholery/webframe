package com.frame.model.dao;

import com.frame.model.bean.RoleModuleRelate;
import com.frame.model.bean.RoleModuleRelateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleModuleRelateMapper {
    int countByExample(RoleModuleRelateCriteria example);

    int deleteByExample(RoleModuleRelateCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleModuleRelate record);

    int insertSelective(RoleModuleRelate record);

    List<RoleModuleRelate> selectByExampleWithBLOBsWithRowbounds(RoleModuleRelateCriteria example, RowBounds rowBounds);

    List<RoleModuleRelate> selectByExampleWithBLOBs(RoleModuleRelateCriteria example);

    List<RoleModuleRelate> selectByExampleWithRowbounds(RoleModuleRelateCriteria example, RowBounds rowBounds);

    List<RoleModuleRelate> selectByExample(RoleModuleRelateCriteria example);

    RoleModuleRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleModuleRelate record, @Param("example") RoleModuleRelateCriteria example);

    int updateByExampleWithBLOBs(@Param("record") RoleModuleRelate record, @Param("example") RoleModuleRelateCriteria example);

    int updateByExample(@Param("record") RoleModuleRelate record, @Param("example") RoleModuleRelateCriteria example);

    int updateByPrimaryKeySelective(RoleModuleRelate record);

    int updateByPrimaryKeyWithBLOBs(RoleModuleRelate record);

    int updateByPrimaryKey(RoleModuleRelate record);
}