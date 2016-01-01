package com.frame.model.dao;

import com.frame.model.bean.SysProperty;
import com.frame.model.bean.SysPropertyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SysPropertyMapper {
    int countByExample(SysPropertyCriteria example);

    int deleteByExample(SysPropertyCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysProperty record);

    int insertSelective(SysProperty record);

    List<SysProperty> selectByExampleWithBLOBsWithRowbounds(SysPropertyCriteria example, RowBounds rowBounds);

    List<SysProperty> selectByExampleWithBLOBs(SysPropertyCriteria example);

    List<SysProperty> selectByExampleWithRowbounds(SysPropertyCriteria example, RowBounds rowBounds);

    List<SysProperty> selectByExample(SysPropertyCriteria example);

    SysProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysProperty record, @Param("example") SysPropertyCriteria example);

    int updateByExampleWithBLOBs(@Param("record") SysProperty record, @Param("example") SysPropertyCriteria example);

    int updateByExample(@Param("record") SysProperty record, @Param("example") SysPropertyCriteria example);

    int updateByPrimaryKeySelective(SysProperty record);

    int updateByPrimaryKeyWithBLOBs(SysProperty record);

    int updateByPrimaryKey(SysProperty record);
}