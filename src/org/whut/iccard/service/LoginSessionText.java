package org.whut.iccard.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.whut.iccard.entity.Installation;
import org.whut.iccard.entity.LoginSession;
import org.whut.iccard.mapper.InstallationMapper;
import org.whut.iccard.mapper.LoginSessionMapper;

public class LoginSessionText {

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
        SqlSession sqlSession = getSessionFactory().openSession();  
        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);  
        LoginSession loginSession = new LoginSession();
        loginSession.setJSESSIONID("123");
        loginSession.setUserName("root");
        loginSession.setTicket("234");
        loginSessionMapper.add(loginSession);
        System.out.println(String.format("name:%s,password:%s", loginSession.getUserName(), loginSession.getTicket())) ;
        //System.out.println(user.getName());  
        
        LoginSession loginSession2 = loginSessionMapper.findByTicket("3");
        System.out.println(loginSession2.getUserName());
        sqlSession.commit();
        
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        String t = sdf.format(new Date());
        
        
        InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
        
        Date d = new Date();
        List<Installation> tasks = installationMapper.findByUser("root", t, false);
        Installation i1 = tasks.get(0);
        System.out.println(i1.getAddress());
        System.out.println(tasks.size());
        
        Installation i2 = new Installation();
        i2.setAddress("wuhan");
        i2.setUserName("root");
        i2.setPostDate(new Date());
        installationMapper.add(i2);
        sqlSession.commit();
    }  
}
