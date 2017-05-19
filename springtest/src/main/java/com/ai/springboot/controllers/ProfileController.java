package com.ai.springboot.controllers;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ai.springboot.exceptions.ImageTooSmallException;
import com.ai.springboot.exceptions.InvalidFileException;
import com.ai.springboot.model.FileInfo;
import com.ai.springboot.model.Profile;
import com.ai.springboot.model.SiteUser;
import com.ai.springboot.service.FileService;
import com.ai.springboot.service.ProfileService;
import com.ai.springboot.service.UserService;
import com.ai.springboot.status.PhotoUploadStatus;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PolicyFactory htmlPolicy;

	@Value("${photo.upload.invalid}")
	private String photoStatusInvalid;
	
	@Value("${photo.upload.ioexception}")
	private String photoStatusIOException;
	
	@Value("${photo.upload.toosmall}")
	private String photoStatusTooSmall;
	
	@Value("${photo.upload.ok}")
	private String photoStatusOk;
	
	private SiteUser getUser(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return userService.getUser(email);
		
	}

	@RequestMapping("/profile")
	ModelAndView showProfile(ModelAndView modelAndView) {
		
		SiteUser user = getUser();		
		Profile profile = profileService.getProfile(user);
		
		if(profile == null){
			
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}
		
		Profile webProfile = new Profile();
		webProfile.safeCopy(profile);
		
		
		modelAndView.getModel().put("profile", webProfile);
		modelAndView.setViewName("app.profile");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.GET)
	ModelAndView editProfileAbout(ModelAndView modelAndView) {
		
		SiteUser user = getUser();
		
		Profile profile = profileService.getProfile(user);
		
		Profile webProfile = new Profile();
		webProfile.safeCopy(profile);
		modelAndView.getModel().put("profile", webProfile);
		
		modelAndView.setViewName("app.editprofileabout");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.POST)
	ModelAndView saveProfileAbout(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {
		
		modelAndView.setViewName("app.editprofileabout");
		
		SiteUser user = getUser();
		Profile profile = profileService.getProfile(user);
		
		profile.safeMergeFrom(webProfile, htmlPolicy);
		if(!result.hasErrors()){
			
			profileService.save(profile);
			modelAndView.setViewName("redirect:/profile");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/upload-profile-photo", method=RequestMethod.POST)
	@ResponseBody 
	ResponseEntity<PhotoUploadStatus> handlePhotoUpload(@RequestParam("file") MultipartFile file) {
		
		
		
		SiteUser user = getUser();
		Profile profile = profileService.getProfile(user);
		
		Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);
		
		PhotoUploadStatus status = new PhotoUploadStatus(photoStatusOk);
		
		try {
			FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "p"+user.getId(), 100,100);
			
			profile.setPhotoDetails(photoInfo);
			profileService.save(profile);
			
			if(oldPhotoPath !=null) {
				Files.delete(oldPhotoPath);
			}
			
		} catch (InvalidFileException e) {
			status.setMessage(photoStatusInvalid);
			e.printStackTrace();
		} catch (IOException e) {
			status.setMessage(photoStatusIOException);
			e.printStackTrace();
		} catch (ImageTooSmallException e) {
			status.setMessage(photoStatusTooSmall);
			e.printStackTrace();
		}
		return new ResponseEntity<>(status,org.springframework.http.HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/profilephoto", method=RequestMethod.GET)
	@ResponseBody
	ResponseEntity<InputStreamResource> servePhoto() throws IOException {
		
		SiteUser user = getUser();
		Profile profile = profileService.getProfile(user);
		
		Path photoPath = Paths.get(photoUploadDirectory, "default","test.jpg");
		
		if(profile !=null && profile.getPhoto(photoUploadDirectory) !=null) {
			
			photoPath = profile.getPhoto(photoUploadDirectory);
		}
		
		return ResponseEntity
				.ok()
				.contentLength(Files.size(photoPath))
				.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
				.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
	}
}
