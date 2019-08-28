package com.school.foocommend.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import com.school.foocommend.member.vo.UserDto;

//    resources/mybatis/mappers/member_sql 에서 구현됨.
@Mapper
public interface MemberDao {
	public List<Map<String, Object>> selectBoardList() throws Exception;
	
	public UserDto selectMember(String username) throws Exception;

	public boolean insertMember(UserDto member) throws Exception;
	
	public void updateProfileImage(Map<String,Object> profileImage) throws Exception;
	
	public String selectMemberProfileImage(String username) throws Exception;
	
	public void updateUid(UserDto member) throws Exception;
	
	public Map<String,Object> selectUidMember(String uid) throws Exception;
	
	public void updateMailAuth(String username) throws Exception;
	
	public String selectMemberEmail(String username) throws Exception;
}

