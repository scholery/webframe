package com.frame.model.dao;

import com.frame.model.bean.Role;
import com.frame.model.bean.RoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleMapper {
    int countByExample(RoleCriteria example);

    int deleteByExample(RoleCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExampleWithBLOBsWithRowbounds(RoleCriteria example, RowBounds rowBounds);

    List<Role> selectByExampleWithBLOBs(RoleCriteria example);

    List<Role> selectByExampleWithRowbounds(RoleCriteria example, RowBounds rowBounds);

    List<Role> selectByExample(RoleCriteria example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Role record, @Param("example") RoleCriteria example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleCriteria example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKeyWithBLOBs(Role record);

    int updateByPrimaryKey(Role record);
}