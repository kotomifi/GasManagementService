package org.whut.iccard.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("files")
public class FileResource {

	private static final String BASE_PATH = "C:/temp/";

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDisposition)
			throws FileNotFoundException, IOException {

		String fileName = fileDisposition.getFileName();
		System.out.println("***** fileName " + fileDisposition.getFileName());
		String filePath = BASE_PATH + fileName;
		try (OutputStream fileOutputStream = new FileOutputStream(filePath)) {
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = fileInputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, read);
			}
		}

		return "File Upload Successfully !!";
	}
}
