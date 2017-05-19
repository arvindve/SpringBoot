package com.ai.springboot;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ai.springboot.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class FileServiceTest {
	
	@Autowired
	private FileService fileService;
	
	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
	@Test
	public void testGetExtension() throws Exception {
		
		Method method = FileService.class.getDeclaredMethod("getFileExtention", String.class);	
		method.setAccessible(true);
		
		assertEquals("should be png", "png", (String)method.invoke(fileService, "test.png"));
		assertEquals("should be doc", "doc", (String)method.invoke(fileService, "abc.doc"));
		assertEquals("should be jpeg", "jpeg", (String)method.invoke(fileService, "file.jpeg"));
		assertNull("png", (String)method.invoke(fileService, "xyz"));
		
	}
	
	@Test
	public void testImageExtension() throws Exception {
		
		Method method = FileService.class.getDeclaredMethod("isImageExtension", String.class);	
		method.setAccessible(true);
		
		assertTrue("png should be valid", (Boolean)method.invoke(fileService, "png"));
		assertTrue("PNG should be valid", (Boolean)method.invoke(fileService, "PNG"));
		assertTrue("jpg should be valid", (Boolean)method.invoke(fileService, "jpg"));
		assertTrue("JPEG should be valid", (Boolean)method.invoke(fileService, "JPEG"));
		assertTrue("GIF should be valid", (Boolean)method.invoke(fileService, "GIF"));
		assertTrue("gif should be valid", (Boolean)method.invoke(fileService, "gif"));
		
		assertFalse("gif1 should be invalid", (Boolean)method.invoke(fileService, "gif1"));
		assertFalse("gif3 should be invalid", (Boolean)method.invoke(fileService, "gif3"));
	}
	
	@Test
	public void testCreateDirectory() throws Exception {
		
		Method method = FileService.class.getDeclaredMethod("makeSubdirectory", String.class, String.class);	
		method.setAccessible(true);
		
		for(int i=0;i<10000;i++){
			
			File created = (File)method.invoke(fileService, photoUploadDirectory , "photo");
			
			assertTrue("Directory should exist"+ created.getAbsolutePath(), created.exists());
		}
		
	}
	
	
	

}
