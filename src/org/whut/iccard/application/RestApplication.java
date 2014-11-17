package org.whut.iccard.application;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.filter.LoggingFilter;

public class RestApplication extends ResourceConfig  {
	public RestApplication() {
		// 服务类所在包
		packages("org.whut.iccard.service");
		
		register(LoggingFilter.class);
		// 注册JSON转换器
		register(JacksonJsonProvider.class);  
		register(ApplicationConfig.class);
	}
}
