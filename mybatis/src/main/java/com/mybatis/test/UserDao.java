package com.mybatis.test;

import java.util.List;

public interface UserDao {

    //最简单查询
    public List<User> findAll();
}
