package com.example.springbootjpa.dao;

import com.example.springbootjpa.bean.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询接口
 *
 * created by dbx on 2019/1/14
 */

@Repository
public interface UserDao extends JpaRepository<UserDO,Long> {

    //自定义查询方法
    UserDO findByAccount(String account);

    UserDO findByAccountAndPwd(String account, String pwd);

    List<UserDO> findAllByIdGreaterThan(Long id);

    @Query("SELECT O FROM UserDO O WHERE O.name = :name1  OR O.name = :name2 ")
    List<UserDO> findTwoName(@Param("name1") String name1, @Param("name2") String name2);

    // @Query 注解中增加一个 nativeQuery = true 的属性，就可以采用原生 SQL 语句的方式来编写查询。
    @Query(nativeQuery = true, value = "SELECT * FROM AUTH_USER WHERE name = :name1  OR name = :name2 ")
    List<UserDO> findSQL(@Param("name1") String name1, @Param("name2") String name2);

}
