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
import org.whut.iccard.entity.LoginSession;
import org.whut.iccard.entity.Repair;
import org.whut.iccard.mapper.LoginSessionMapper;
import org.whut.iccard.mapper.RepairMapper;

@Path("repairService")
public class RepairService {

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
	@Path("/getRepairTasks")
	@Produces(MediaType.APPLICATION_XML)
	public List<Repair> getRepariTask(
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
			RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
	        List<Repair> tasks = repairMapper.findByUser(userName, false); 
	        
	        return tasks;
		} 
		return null;
	}
	
	@POST
	@Path("/postRepairTasks")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
	public String postRepairTask(
			@FormParam("id") String id, 
			@FormParam("type") String type, 
			@FormParam("description") String description, 
			@FormParam("isUpdate") String isUpdate, 
			@FormParam("oldBarCode") String oldBarCode, 
			@FormParam("oldIndication") String oldIndication, 
			@FormParam("newBarCode") String newBarCode, 
			@FormParam("newIndication") String newIndication, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		
		SqlSession sqlSession = getSessionFactory().openSession();  
		// 根据SessionID 查询登陆用户名
		String JSESSIONID = request.getSession().getId();
		System.out.println("任务： " +JSESSIONID);
		LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
		LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
		String userName = loginSession.getUserName();
		if (loginSession == null)
			return "NOT LOGIN";
		
		// 更新
		RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
		Repair re = repairMapper.findById(Long.parseLong(id));
		re.setIsComplete(1);
		re.setUploadFlag(1);
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = sd.format(d);
		re.setCompleteDate(time);
		if (description != null) {
			re.setDescription(description);
		}
		re.setType(type);
		if (isUpdate.equals("1")) {
			re.setOldBarCode(oldBarCode);
			re.setOldIndication(Integer.parseInt(oldIndication));
			re.setNewBarCode(newBarCode);
			re.setNewIndication(Integer.parseInt(newIndication));
			re.setIsUpdate(Integer.parseInt(isUpdate));
		}
		repairMapper.update(re);
		sqlSession.commit();
		sqlSession.close();
		
		return "SUCCESS";
	}
}
