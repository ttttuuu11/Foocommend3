package com.school.foocommend.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.User;

import com.school.foocommend.member.vo.UserDto;


public interface MemberService {
	List<Map<String, Object>> selectBoardList() throws Exception;
	
	UserDto selectMember(String username) throws Exception;
	
	void insertMember(UserDto member) throws Exception;
	
	void updateProfileImage(Map<String,Object> profileImage) throws Exception;
	
	String selectMemberProfileImage(String username) throws Exception;
	
	void updateUid(UserDto member) throws Exception;
	
	Map<String,Object> selectUidMember(String uid) throws Exception;
	
	void updateMailAuth(String username) throws Exception;
	
	String selectMemberEmail(String username) throws Exception;
}
