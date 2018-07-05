package com.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;


public class UserTest{

    private static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void testUser() throws Exception{
        String resource = "SqlMapperConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);

        //SqlSession 执行器
        SqlSession sqlSession = sqlSessionFactory.openSession();



        //mybatis也是实现了interceptor的接口信息

        /**
         * pageHelp小实例 有先后的执行顺序
         */
        PageHelper.startPage(1,6);

        List<User> list = sqlSession.selectList("com.mybatis.test.UserDao.findAll");

        PageInfo pageInfo = new PageInfo<User>(list);


        System.out.println(pageInfo.getList().size());



//        for(int i= 0;i<30;i++){
//            User u = new User();
//            u.setUsername("user"+i);
//            u.setPassword("user"+i);
//
//            sqlSession.insert("com.mybatis.test.UserDao.save",u);
//        }
//
//        //事务手动提交
//        sqlSession.commit();
    }

    /*配置到数据库logger日志文件信息*/
    @Test
    public void testSC() throws  Exception{
        logger.info("123");
        String resource = "SqlMapperConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<SC> scs = sqlSession.selectList("com.mybatis.test.StudentDao.findById",2014014002);
        for(SC sc:scs){
            System.out.println(sc.toString());
        }

    }
}
