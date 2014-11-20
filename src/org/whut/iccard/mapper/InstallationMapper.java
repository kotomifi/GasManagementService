package org.whut.iccard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.whut.iccard.entity.Installation;

public interface InstallationMapper {
	public List<Installation> findByUser(
			@Param("userName")String userName, 
			@Param("postDate")String postDate, 
			@Param("isComplete")boolean isComplete);
	public void add(Installation installation);
	public void update(Installation installation);
	public Installation findById(long id);
}
