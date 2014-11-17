package org.whut.iccard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.whut.iccard.entity.Repair;

public interface RepairMapper {
	public List<Repair> findByUser(@Param("userName")String userName);
	public void add(Repair repair);
	public void update(Repair repair);
}
