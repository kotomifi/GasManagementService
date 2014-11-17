package org.whut.iccard.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.whut.iccard.entity.User;
import org.whut.iccard.mapper.UserMapper;
import org.whut.iccard.utils.MD5Encoder;

public class UserTest {
	private static SqlSessionFactory getSessionFactory() {  
        SqlSessionFactory sessionFactory = null;  
        String resource = "configuration.xml";  
        try {  
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
                    .getResourceAsReader(resource));  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return sessionFactory;  
    }  
	
	public static void main(String[] args) {  
		System.out.println(MD5Encoder.GetMD5Code("admin"));
		System.out.println(MD5Encoder.GetMD5Code("admin").length());
        SqlSession sqlSession = getSessionFactory().openSession();  
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
        User user = userMapper.findByName("root");  
        MD5Encoder md = new MD5Encoder();
        String a = md.GetMD5Code("root");
        System.out.println("md5:" + a);
        System.out.println(String.format("name:%s,password:%s,sex:%s", user.getUserName(), user.getPassword(),
        		user.getSex())) ;
        //System.out.println(user.getName());  
        
        // getAllUser() test
        List<User> userList = userMapper.getAllUser();
        for (int i = 0; i < userList.size(); i++) {
        	User u = userList.get(i);
        	printUser(u);
        }
    }  
	
	private static void printUser(User user) {
		System.out.println(String.format(
				"用户名:%s,名字：%s,密码：%s,电话：%s", user.getUserName(),
				user.getName(), user.getPassword(),user.getPhoneNum()));
	}
}
