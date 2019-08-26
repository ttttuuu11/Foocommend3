package com.school.foocommend.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.school.foocommend.common.CommandMap;
import com.school.foocommend.mail.MailSend;
import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.member.vo.UserDto;

/**
 *  
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Autowired
	MailSend mailSend;
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage() throws Exception {
		ModelAndView mv = new ModelAndView("/member/viewLogin");
		
		return mv;
	}

	@RequestMapping(value = "/join")
	public ModelAndView joinPage() throws Exception {
		ModelAndView mv = new ModelAndView("/member/viewJoin");
		
		return mv;
	}
	
	@RequestMapping(value = "/loginMem", method = RequestMethod.POST)
	public ModelAndView loginMem(@RequestParam(value = "error", required = false) String error,
								@RequestParam(value = "logout", required = false) String logout) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (error != null) {
			mv.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			mv.addObject("msg", "You've been logged out successfully.");
		}
		mv.setViewName("/board/main");
		return mv;
	}

	@RequestMapping(value = "/joinMem")
	public ModelAndView joinMem(UserDto member) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/member/login");
		memberService.insertMember(member);
		return mv;
	}
	
	@RequestMapping(value = "/mypage")
	public ModelAndView myPage() throws Exception {
		ModelAndView mv = new ModelAndView("/member/viewMypage");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		
		UserDto member = memberService.selectMember(username);
		
		mv.addObject("member",member);
		return mv;
	}
	

	@RequestMapping(value = "/mail")
	public void mailsending() throws Exception {
		UUID uid = UUID.randomUUID();
		
		UserDto member = new UserDto();
		member.setUid(uid.toString());
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		
		member.setUsername(username);
		memberService.updateUid(member); 
		mailSend.MailSend(uid.toString());
	}

	@RequestMapping(value = "/mailAuth")
	public void mailAuth(@RequestParam(value = "uid", required = false) String uid) throws Exception {		
		Map<String,Object> memberUid = new HashMap<String,Object>();
		memberUid = memberService.selectUidMember(uid);
		if(Integer.parseInt(memberUid.get("cnt").toString())>0) {
			memberService.updateMailAuth(memberUid.get("username").toString());
		}
	}
}
