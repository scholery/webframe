package com.frame.model.dao;

import com.frame.model.bean.UserRoleRelate;
import com.frame.model.bean.UserRoleRelateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserRoleRelateMapper {
    int countByExample(UserRoleRelateCriteria example);

    int deleteByExample(UserRoleRelateCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelate record);

    int insertSelective(UserRoleRelate record);

    List<UserRoleRelate> selectByExampleWithBLOBsWithRowbounds(UserRoleRelateCriteria example, RowBounds rowBounds);

    List<UserRoleRelate> selectByExampleWithBLOBs(UserRoleRelateCriteria example);

    List<UserRoleRelate> selectByExampleWithRowbounds(UserRoleRelateCriteria example, RowBounds rowBounds);

    List<UserRoleRelate> selectByExample(UserRoleRelateCriteria example);

    UserRoleRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleRelate record, @Param("example") UserRoleRelateCriteria example);

    int updateByExampleWithBLOBs(@Param("record") UserRoleRelate record, @Param("example") UserRoleRelateCriteria example);

    int updateByExample(@Param("record") UserRoleRelate record, @Param("example") UserRoleRelateCriteria example);

    int updateByPrimaryKeySelective(UserRoleRelate record);

    int updateByPrimaryKeyWithBLOBs(UserRoleRelate record);

    int updateByPrimaryKey(UserRoleRelate record);
}