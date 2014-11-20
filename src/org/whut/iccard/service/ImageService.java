package org.whut.iccard.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("images")
public class ImageService {

	@POST
	@Path("/export")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public void export(MultivaluedMap<String, String> map) throws Exception {
		
	}
}
