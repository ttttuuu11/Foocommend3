package com.school.foocommend.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.member.vo.LoginUser;
import com.school.foocommend.member.vo.UserDto;

import java.util.Arrays;

import javax.annotation.Resource;

@Service
public class UserDetailService implements UserDetailsService {
		
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "MemberService")
	private MemberService memberService;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = null;
		try {
			userDto = memberService.selectMember(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (userDto == null) {
            throw new UsernameNotFoundException("UsernameNotFound [" + username + "]");
        }
        LoginUser user = createUser(userDto);
        return user;
    }

    private LoginUser createUser(UserDto userDto) {
        LoginUser loginUser = new LoginUser(userDto);
        if (loginUser.getIsAdmin().equals("Y")) {
            loginUser.setRoles(Arrays.asList("ROLE_ADMIN"));
        } else {
            loginUser.setRoles(Arrays.asList("ROLE_USER"));
        }
        return loginUser;
    }
}

