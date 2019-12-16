package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用于创建sqlSession对象
 */
public class MyBatisUtil {
    private static SqlSessionFactory ssf=null;
    private static SqlSession sqlSession=null;
    private static String resource="mybatis-configuration.xml";
    private static InputStream is=null;
    //1、创建sqlSessionFactory
    static {
        try {
            is= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ssf= new SqlSessionFactoryBuilder().build(is);
        //关闭流
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2、创建sqlSession
    public static SqlSession getSession(){
        sqlSession=ssf.openSession();
        return sqlSession;
    }

}
