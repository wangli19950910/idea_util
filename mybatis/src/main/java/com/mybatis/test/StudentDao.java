package com.mybatis.test;

import java.util.List;

public interface StudentDao {

    //查询某个学生有的多少门课程
    public List<SC> findById(int student_id);
}
