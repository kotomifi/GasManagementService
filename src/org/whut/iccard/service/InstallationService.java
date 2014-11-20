package org.whut.iccard.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.whut.iccard.entity.Installation;
import org.whut.iccard.entity.LoginSession;
import org.whut.iccard.mapper.InstallationMapper;
import org.whut.iccard.mapper.LoginSessionMapper;



@Path("installationService")
public class InstallationService {

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
	@Path("/getInstallationTasks")
	@Produces(MediaType.APPLICATION_XML)
	public List<Installation> getInstallationTask(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {

		SqlSession sqlSession = getSessionFactory().openSession();  
		// 根据SessionID 查询登陆用户名
		String JSESSIONID = request.getSession().getId();
		System.out.println("任务： " +JSESSIONID);
		LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
		LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
		String userName = loginSession.getUserName();
		if (loginSession != null) {
			InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
			String t = sdf.format(new Date());
			List<Installation> tasks = installationMapper.findByUser(userName, t, false); 
			Installation is = tasks.get(0);
			System.out.println("-----" + is.getPostDate());

			return tasks;
		} 

		return null;
	}

	@POST
	@Path("/postInstallationTasks")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public String postInstallationTask(
			@FormParam("id") String id, 
			@FormParam("barCode") String barCode, 
			@FormParam("indication") String indication, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {

		System.out.println("------------");
		System.out.println(id);
		System.out.println(barCode);
		System.out.println(indication);
		System.out.println("------------");
		SqlSession sqlSession = getSessionFactory().openSession();  
		// 根据SessionID 查询登陆用户名
		String JSESSIONID = request.getSession().getId();
		System.out.println("任务： " +JSESSIONID);
		LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
		LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
		String userName = loginSession.getUserName();
		if (loginSession == null)
			return "NOT LOGIN";

		// 判断数据是否合格
		Installation installation = new Installation();
		installation.setUploadFlag(1);

		InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
		Installation st2 = installationMapper.findById(2);
		st2.setBarCode(barCode);
		st2.setIndication(Integer.parseInt(indication));
		st2.setUploadFlag(1);
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = sd.format(d);
		st2.setCompleteDate(time);
		st2.setIsComplete(1);
		System.out.println(st2.toString());
		installationMapper.update(st2);
		sqlSession.commit();
		sqlSession.close();
		
		return "SUCCESS";
	}
}
