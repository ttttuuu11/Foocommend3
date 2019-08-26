package com.school.foocommend.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.school.foocommend.comment.vo.CommentDto;

@Mapper
public interface CommentDao {
	
	public List<Map<String,Object>> selectCommentList(String restaurantIdx) throws Exception;;

	public void insertComment(Map<String, Object>  comment) throws Exception;

	public void deleteComment(String comment_idx) throws Exception;
	
	public void updateContentComment(CommentDto comment) throws Exception;
	
	public void insertReplyComment(Map<String, Object> comment) throws Exception;
	
	public List<Map<String,Object>> selectReplyCommentList(String parent_comment_idx) throws Exception;
	
	public int countReplyComment(String parent_comment_idx) throws Exception;
	
	public void updateCountReplyComment(String comment_idx) throws Exception;
}
