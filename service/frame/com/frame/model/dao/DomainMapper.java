package com.frame.model.dao;

import com.frame.model.bean.Domain;
import com.frame.model.bean.DomainCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DomainMapper {
    int countByExample(DomainCriteria example);

    int deleteByExample(DomainCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Domain record);

    int insertSelective(Domain record);

    List<Domain> selectByExampleWithBLOBsWithRowbounds(DomainCriteria example, RowBounds rowBounds);

    List<Domain> selectByExampleWithBLOBs(DomainCriteria example);

    List<Domain> selectByExampleWithRowbounds(DomainCriteria example, RowBounds rowBounds);

    List<Domain> selectByExample(DomainCriteria example);

    Domain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Domain record, @Param("example") DomainCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Domain record, @Param("example") DomainCriteria example);

    int updateByExample(@Param("record") Domain record, @Param("example") DomainCriteria example);

    int updateByPrimaryKeySelective(Domain record);

    int updateByPrimaryKeyWithBLOBs(Domain record);

    int updateByPrimaryKey(Domain record);
}