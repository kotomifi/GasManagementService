package org.whut.iccard.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.whut.iccard.entity.User;
import org.whut.iccard.entity.Users;

public class JerseyClient {
	public static void main(String[] args) {
		List<User> mList = new ArrayList<User>();
		User user = new User();
		user.setName("master");
		user.setUserName("yu");
		user.setSex("male");
		user.setPassword("123");
		user.setWorkId(4);
		user.setPhoneNum("123456");
		
		User user2 = new User();
		user2.setName("master");
		user2.setUserName("yuu");
		user2.setSex("male");
		user2.setPassword("123");
		user2.setWorkId(4);
		user2.setPhoneNum("123456");
		
		mList.add(user);
		mList.add(user2);
		
		Users users = new Users();
		users.setUsers(mList);
		
		Client client = ClientBuilder.newClient();
		
		String url = "http://localhost:8080/ICCard/rest/userService/send";
		WebTarget target = client.target(url);
		Response response = target.request().buildPost(Entity.entity(users, MediaType.APPLICATION_XML)).invoke();   
		response.close(); 
	}
	
}
