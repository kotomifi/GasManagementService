package org.whut.iccard.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.whut.iccard.entity.LoginSession;
import org.whut.iccard.entity.User;
import org.whut.iccard.entity.Users;
import org.whut.iccard.mapper.LoginSessionMapper;
import org.whut.iccard.mapper.UserMapper;
import org.whut.iccard.utils.MD5Encoder;

@Path("userService")
public class UserService {

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
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello world";
	}
	
	@POST
	@Path("/getCurrentUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getCurrentUser(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		
		String JSESSIONID = request.getSession().getId(); 
		SqlSession sqlSession = getSessionFactory().openSession();  
		
		LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
		LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
		
		if (loginSession == null) {
			System.out.println("null");
			User u = new User();
			return u;
		}
		String username = loginSession.getUserName();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByName(username);
        return user;
	}
	
	/**
	 * 返回 ticket
	 * ticket = md5(name, time)
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/getTicket")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public String getUserText(
			@FormParam("username") String username, 
			@FormParam("password") String password,
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		
		/////////////////////////////////////////////////
		System.out.println("init name:" + username);
		System.out.println("init password:" +password);
		/////////////////////////////////////////////////
		
		if (username == null || username.trim().equals("")|| password==null|| password.trim().equals("")) {
			 System.out.println("getTicket----->");
			return null;
		}
		SqlSession sqlSession = getSessionFactory().openSession();  
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
        User user = userMapper.findByName(username);  
        String passwd = MD5Encoder.GetMD5Code(password);
        System.out.println("md5:" + passwd);
        System.out.println(user.getPassword());
        if (passwd.equals(user.getPassword())) {
        	
        	
        	// 返回ticket
        	Date now = new Date();
        	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        	String time = formatter.format(now);
        	String mTicket = MD5Encoder.GetMD5Code(username + time);
        	System.out.println("----" + username);
        	
        	// 保存JSESSIONID
        	String JSESSIONID = request.getSession().getId(); 
        	System.out.println("登陆:" + JSESSIONID);
        	
        	LoginSession newLoginSession = new LoginSession();
        	newLoginSession.setJSESSIONID(JSESSIONID);
        	newLoginSession.setUserName(username);;
        	newLoginSession.setTicket(mTicket);
        	
        	LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);  
        	LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
        	if (loginSession != null) {
        		loginSessionMapper.update(newLoginSession);
        	} else {
        		loginSessionMapper.add(newLoginSession);
        	}
        	
        	sqlSession.commit();
            sqlSession.close();
            
            //////////////////////
            System.out.println("getTicket----->" + mTicket);
        	return mTicket;
        } 
        System.out.println("getTicket----->" + "no");
        return null;
	}
	
	@GET
	@Path("getSessionId")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public String getSessionId(
			@QueryParam("ticket") String ticket, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		System.out.println("init ticket:" + ticket);
		if(request == null || ticket.equals("") || ticket == null){
            return null;
        }
		
		// 查询JSESSIONID
		String JSESSIONID = request.getSession().getId();
		SqlSession sqlSession = getSessionFactory().openSession();
		LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
		LoginSession loginSession = loginSessionMapper.findByTicket(ticket);
		if (loginSession == null || !(loginSession.getJSESSIONID().equals(JSESSIONID))) {
			return null;
		}
		
		
		System.out.println(JSESSIONID);
		Cookie cookid = new Cookie("JSESSIONID", JSESSIONID);
		response.addCookie(cookid);
		sqlSession.close();
		return "SUCCESS";
	}
	
	@GET
	@Path("getAllUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public List<User> getAllUser() {
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> mList = userMapper.getAllUser();
		sqlSession.close();
		return mList;
	}
	
	@POST
	@Path("addUser")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public String addUser(
			@FormParam("username") String username, 
			@FormParam("password") String password) {
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		try {
			User user = new User();
			String passwd = MD5Encoder.GetMD5Code(password);
			user.setUserName(username);
			user.setPassword(passwd);
			userMapper.add(user);
			sqlSession.commit();
			sqlSession.close();
			return "SUCCESS";
		} catch (Exception e) {
			return "FAIL";
		}
	}
	
	@POST
	@Path("send")
	@Consumes(MediaType.APPLICATION_XML)
	public String consumeXML(
			Users users) {
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		for (int i = 0; i < users.getUsers().size(); i++) {
			User user = users.getUsers().get(i);
			userMapper.add(user);
		}
		sqlSession.commit();
		sqlSession.close();
		return "ok";
	}
}
