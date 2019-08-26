package com.school.foocommend.member.vo;

import lombok.Data;


@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String isadmin;
    private String isEnabled;
    private String authNum;
    private String email;
    private String nickname;
    private String profile_img;
    private String uid;
}
