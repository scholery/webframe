package com.frame.model.dao;

import com.frame.model.bean.RoleGroupRelate;
import com.frame.model.bean.RoleGroupRelateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleGroupRelateMapper {
    int countByExample(RoleGroupRelateCriteria example);

    int deleteByExample(RoleGroupRelateCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleGroupRelate record);

    int insertSelective(RoleGroupRelate record);

    List<RoleGroupRelate> selectByExampleWithBLOBsWithRowbounds(RoleGroupRelateCriteria example, RowBounds rowBounds);

    List<RoleGroupRelate> selectByExampleWithBLOBs(RoleGroupRelateCriteria example);

    List<RoleGroupRelate> selectByExampleWithRowbounds(RoleGroupRelateCriteria example, RowBounds rowBounds);

    List<RoleGroupRelate> selectByExample(RoleGroupRelateCriteria example);

    RoleGroupRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleGroupRelate record, @Param("example") RoleGroupRelateCriteria example);

    int updateByExampleWithBLOBs(@Param("record") RoleGroupRelate record, @Param("example") RoleGroupRelateCriteria example);

    int updateByExample(@Param("record") RoleGroupRelate record, @Param("example") RoleGroupRelateCriteria example);

    int updateByPrimaryKeySelective(RoleGroupRelate record);

    int updateByPrimaryKeyWithBLOBs(RoleGroupRelate record);

    int updateByPrimaryKey(RoleGroupRelate record);
}