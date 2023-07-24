package com.pj.dao;

import com.pj.dto.UserDto;
import com.pj.entity.User;
import com.pj.util.MenuDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 医护人员信息表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-03 15:14:09
 */
public interface UserDao {
    //根据姓名模糊查询
    @Select("SELECT r.*, d.name as department " +
            "FROM user AS r\n" +
            "LEFT JOIN department AS d ON r.department_id = d.id\n" +
            "WHERE r.username like  concat('%',#{username},'%' ) and r.del_flag!=2")
    List<User> mohu(String username);
    //根据用户名查询用户信息
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
    //查询当前用户的菜单权限及角色
    @Select("SELECT m.menu_id, m.menu_name, m.parent_id, m.menu_type,m.perms, r.role,m.icon, m.url\n" +
            "FROM menu m\n" +
            "JOIN role_menu rm ON rm.menu_id = m.menu_id\n" +
            "JOIN role r ON r.id = rm.role_id\n" +
            "JOIN user_role ur ON ur.role_id = r.id\n" +
            "JOIN user u ON u.id = ur.uid\n" +
            "WHERE u.email = #{email}\n" +
            "GROUP BY m.menu_id\n" +
            "ORDER BY m.menu_id ASC;")
    List<MenuDto> role(String email);
    //注册
    int insert(User user);
    //注册时修改用户信息
    int update(User user);
    //根据科室id和类型 查询用户信息
    @Select("select * from user where department_id=#{departmentId} and department_type=#{departmentType}")
    @Results({
            @Result(property = "doctor",column = "username"),
            @Result(property = "price",column = "treatment_price"),
            @Result(property = "doctorId",column = "id")
    })
    List<UserDto> departmentId(@Param("departmentId") int departmentId, @Param("departmentType") int departmentType);
    //根据id查询数据
    User queryById(Integer id);
}

