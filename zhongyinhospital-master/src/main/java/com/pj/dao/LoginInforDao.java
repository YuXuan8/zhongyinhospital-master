package com.pj.dao;

import com.pj.entity.LoginInfor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 登录信息表(LoginInfor)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-18 11:10:49
 */
public interface LoginInforDao {
    @Select("SELECT li.*,u.username FROM login_infor li\n" +
            "JOIN `user`u on u.id=li.user_id\n" +
            "where  u.username\n" +
            "  like  concat('%',#{username},'%' )" +
            " ORDER BY create_datetime DESC")
    List<LoginInfor> mohu(String username);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LoginInfor queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param loginInfor 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<LoginInfor> queryAllByLimit(LoginInfor loginInfor, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param loginInfor 查询条件
     * @return 总行数
     */
    long count(LoginInfor loginInfor);

    /**
     * 新增数据
     *
     * @param loginInfor 实例对象
     * @return 影响行数
     */
    int insert(LoginInfor loginInfor);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LoginInfor> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LoginInfor> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LoginInfor> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LoginInfor> entities);

    /**
     * 修改数据
     *
     * @param loginInfor 实例对象
     * @return 影响行数
     */
    int update(LoginInfor loginInfor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

