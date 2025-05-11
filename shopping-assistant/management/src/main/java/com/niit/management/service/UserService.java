package com.niit.management.service;

import com.niit.management.entity.User;

import java.util.List;

public interface UserService {

    //查全部
    List<User> getAllUser();

    //添加
    void addUser(User user);

    //按id查询
    User getUserById(Integer id);

    //更新信息
    void updateUser(User user);

    //按id删除
    void deleteUserById(Integer id);


}
