package com.cn.melville.repository;

import com.cn.melville.model.User;

import java.util.List;

public interface UserRepository {

    User findById(Integer id);
    List<User> findAll();
    int save(User user);
    int update(User user);
    int deleteById(Integer id);

}
