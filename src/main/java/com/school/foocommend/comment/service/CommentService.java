package com.school.foocommend.comment.service;

import java.util.List;
import java.util.Map;

import com.school.foocommend.comment.vo.CommentDto;


public interface CommentService {

	List<Map<String,Object>> selectCommentList(String restaurantIdx) throws Exception;;

	void insertComment(Map<String, Object>  comment) throws Exception;

	void deleteComment(String comment_idx) throws Exception;
	
	void updateContentComment(CommentDto comment) throws Exception;
	
	List<Map<String,Object>> selectReplyCommentList(String parent_comment_idx) throws Exception;
	
}
