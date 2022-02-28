package com.vinspier.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinspier.template.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName: UserMapper
 * @Description:
 * @Author:
 * @Date: 2020/3/19 11:51
 * @Version V1.0
 **/

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from tb_user where id = #{id}")
    User getById(@Param("id") Long id);

    @Select("select * from tb_user where name = #{username}")
    User getByUsername(@Param("username")String username);

}
