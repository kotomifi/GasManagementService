package org.whut.iccard.mapper;

import org.whut.iccard.entity.LoginSession;

public interface LoginSessionMapper {
	public LoginSession findByTicket(String ticket);
	public LoginSession findBySessionId(String loginSession);
	public void add(LoginSession loginSession);
	public void update(LoginSession loginSession);
}
