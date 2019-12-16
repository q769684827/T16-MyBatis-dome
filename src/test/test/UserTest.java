package test.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.User;
import test.dao.UserDao;
import util.MyBatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 */
public class UserTest {
    //日志
    Logger log=Logger.getLogger(UserTest.class);

    @Test
    public void getUserCount(){
        /*//1、定义一个核心配置文件名称
        String resource="mybatis-configuration.xml";
        //2、定义一个变量接收用户记录数
        Integer count=0;
        //3、获取sqlSession对象从而从数据库中读取数据
        SqlSession sqlSession=null;
        //步骤1：加载流
        InputStream is=null;
        try {
            is= Resources.getResourceAsStream(resource);
            //步骤2：读取相应的流，获取SqlSessionFactory
            SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(is);
            //步骤3：获取sqlSession
            sqlSession=ssf.openSession();
            //步骤4：执行相应的方法
            count=sqlSession.selectOne("test.dao.UserDao.getUserCount");*/
        int count= MyBatisUtil.getSession().selectOne("test.dao.UserDao.getUserCount");
        log.info("用户总记录数有："+count);
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    //测试2
    @Test
    public void getUserList1(){
        //查询出所有用户信息
        List<User> list=MyBatisUtil.getSession().selectList("test.dao.UserDao.getUserList");
        for(User user:list){
            log.info(user.getUserName()+"--"+ user.getUserPassword());
        }
    }
    //测试3，使用mapper接口的方式
    @Test
    public void getUserList2(){
        List<User> list=MyBatisUtil.getSession().getMapper(UserDao.class).getUserList();
        for(User user:list){
            log.info(user.getUserName()+"--"+ user.getUserPassword());
        }
    }
    //测试4、单条件模糊查询
    @Test
    public void getUserListLikeByName(){
        String name="张";
        List<User> list=MyBatisUtil.getSession()
                .getMapper(UserDao.class).getUserListLikeByName(name);
        for(User user:list){
            log.info(user.getUserName()+"--"+ user.getUserPassword());
        }
    }
    //测试4、多条件模糊查询(以对象传参)
    @Test
    public void getUserListParametersByUser(){
        String name="张";
        Integer role=3;
        //封装参数
        User u=new User();
        u.setUserName(name);
        u.setUserRole(role);

        List<User> list=MyBatisUtil.getSession()
                .getMapper(UserDao.class).getUserListParametersByUser(u);
        for(User user:list){
            log.info(user.getUserName()+"--"+ user.getUserRole());
        }
    }
    //测试5、多条件模糊查询(以Map传参)
    @Test
    public void getUserListParametersByMap(){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("A","张");
        map.put("B",3);

        List<User> list=MyBatisUtil.getSession()
                .getMapper(UserDao.class).getUserListParametersByMap(map);
        for(User user:list){
            log.info(user.getUserName()+"--"+ user.getUserRole());
        }
    }

}
