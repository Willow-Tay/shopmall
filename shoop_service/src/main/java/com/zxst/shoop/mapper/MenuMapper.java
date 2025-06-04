package com.zxst.shoop.mapper;

import com.zxst.shoop.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {

    List<Menu> getInfoByPid(@Param("pid") Integer  pid);

    @Select("select id from t_menu where link_url is not null and id in(SELECT per_fk FROM `role_menu` where role_fk=#{roleid})")
    List<Integer> getIdByRoleId(@Param("roleid") Integer roleid);
}
