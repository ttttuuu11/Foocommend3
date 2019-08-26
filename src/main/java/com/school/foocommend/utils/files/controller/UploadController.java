package com.school.foocommend.utils.files.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.member.vo.UserDto;
import com.school.foocommend.utils.files.UploadFileUtils;
import com.school.foocommend.utils.media.MediaUtils;

@Controller
@RequestMapping("/upload/*")
public class UploadController {

	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	private String uploadPath = "D:\\workspace\\resources\\foocommend\\userProfile";
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadFormGET() {
		return "/upload/uploadForm";
	}
	
	//Post 방식 파일 업로드
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public ModelAndView uploadFormPOST(MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/member/mypage");

		log.info("uploadFormPost");
		
		if(file != null) {
			log.info("originalName:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
			log.info("ContentType:" + file.getContentType());
		}
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		log.info(savedName);
	

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		
		
		String deleteFileName = memberService.selectMemberProfileImage(username);
		String path = "D:/workspace/resources/foocommend/userProfile/"+deleteFileName;
		File deletefile = new File(path);

		if(deletefile.exists() == true){
			deletefile.delete();
		}
		
		Map<String,Object> profileImage = new HashMap<String,Object>();
		profileImage.put("username",username);
		profileImage.put("profile_img",savedName);
		memberService.updateProfileImage(profileImage);
		
		return mv; 
	}
	
	//업로드된 파일을 저장하는 함수
	private String uploadFile(String originalName, byte[] fileDate) throws IOException {
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
				
		FileCopyUtils.copy(fileDate, target);
		
		return savedName;
		
	}
	
	//Ajax 파일 업로드
	@RequestMapping(value="/sample/upload/uploadAjax", method = RequestMethod.GET)
	public String uploadAjaxGET() {
		return "/upload/uploadAjax";
	}
	
	
	//Ajax 파일 업로드 produces는 한국어를 정상적으로 전송하기 위한 속성
	@ResponseBody
	@RequestMapping(value="/sample/upload/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile file) throws Exception {
		
		log.info("originalName:" + file.getOriginalFilename());
		log.info("size:" + file.getSize());
		log.info("contentType:" + file.getContentType());
				
		//HttpStatus.CREATED : 리소스가 정상적으로 생성되었다는 상태코드.
		//return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	//화면에 저장된 파일을 보여주는 컨트롤러 /년/월/일/파일명 형태로 입력 받는다.
	// displayFile?fileName=/년/월/일/파일명
	@ResponseBody
	@RequestMapping(value="/sample/upload/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		log.info("File name: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}// else
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}// displayFile
	
	
	//업로드된 파일 삭제 처리
	//@ResponseBody ResponseEntity<String>
	@RequestMapping(value="/upload/deleteFile", method = RequestMethod.POST)
	public void deleteFile(String fileName) throws Exception {
		
		log.info("delete file:" + fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}//if
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		//return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}