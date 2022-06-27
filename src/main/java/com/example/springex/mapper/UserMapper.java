package com.example.springex.mapper;

import com.example.springex.entitiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();
    User findUserById(long id);
    List<User> findUserByName(@Param("name") String name);
    User findUserByNameAndAge(@Param("name") String name, @Param("age") int age);
    void insertUser(@Param("user") User user);
    void updateUser(@Param("id") long id, @Param("user") User user);
    void deactivateUserById(long id);
    User findLoanDataById(long id);

}
