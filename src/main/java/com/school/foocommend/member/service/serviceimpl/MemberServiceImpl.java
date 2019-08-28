package com.school.foocommend.member.service.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.foocommend.member.dao.MemberDao;
import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.member.vo.UserDto;


@Service("MemberService")
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<Map<String, Object>> selectBoardList() throws Exception {
		return memberDao.selectBoardList();
	}

	@Override
	public UserDto selectMember(String username) throws Exception {

		return memberDao.selectMember(username);
	}

	@Override
	public void insertMember(UserDto member) throws Exception {
		memberDao.insertMember(member);
	}

	@Override
	public void updateProfileImage(Map<String,Object> profileImage) throws Exception {
		memberDao.updateProfileImage(profileImage);
	}

	@Override
	public String selectMemberProfileImage(String username) throws Exception {
		return memberDao.selectMemberProfileImage(username);
	}

	@Override
	public void updateUid(UserDto member) throws Exception {
		memberDao.updateUid(member);
	}

	@Override
	public Map<String,Object> selectUidMember(String uid) throws Exception {
		return memberDao.selectUidMember(uid);
	}

	@Override
	public void updateMailAuth(String username) throws Exception {
		memberDao.updateMailAuth(username);
	}

	@Override
	public String selectMemberEmail(String username) throws Exception {
		return memberDao.selectMemberEmail(username);
	}
	
}
