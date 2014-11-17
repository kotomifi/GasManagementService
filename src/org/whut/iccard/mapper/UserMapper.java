package org.whut.iccard.mapper;

import java.util.List;

import org.whut.iccard.entity.User;

public interface UserMapper {
	public User findById(String id); 
	public User findByName(String name);
	public void add(User user);
	public List<User> getAllUser();
}
