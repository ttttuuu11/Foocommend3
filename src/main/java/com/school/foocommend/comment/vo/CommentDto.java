package com.school.foocommend.comment.vo;


import java.sql.Date;

import lombok.Data;

@Data
public class CommentDto {
	private long comment_idx;
	private String username;
	private String comment_content;
	private long restaurant_idx;
	private String isdelete;
	private String profile_img;	
	private Date create_date;
	private int count_reply_comment;
	private String reply_username;
}
