package com.school.foocommend.comment.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.foocommend.comment.dao.CommentDao;
import com.school.foocommend.comment.service.CommentService;
import com.school.foocommend.comment.vo.CommentDto;

@Service("CommentService")
@Transactional
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Map<String,Object>> selectCommentList(String restaurantIdx) throws Exception {
		return commentDao.selectCommentList(restaurantIdx);
	}

	@Override
	public void insertComment(Map<String, Object>  commentMap) throws Exception {
		if(String.valueOf(commentMap.get("parent_comment_idx")).equals("-1")) {
			commentDao.insertComment(commentMap);
		}else {
			int reply_comment_idx = commentDao.countReplyComment(commentMap.get("parent_comment_idx").toString());
			commentDao.updateCountReplyComment(commentMap.get("parent_comment_idx").toString());
			commentMap.put("reply_comment_idx", reply_comment_idx);
			commentDao.insertReplyComment(commentMap);
		}
	}

	@Override
	public void deleteComment(String comment_idx) throws Exception {
		commentDao.deleteComment(comment_idx);
	}

	@Override
	public void updateContentComment(CommentDto comment) throws Exception {
		commentDao.updateContentComment(comment);
	}

	@Override
	public List<Map<String,Object>> selectReplyCommentList(String parent_comment_idx) throws Exception {
		return commentDao.selectReplyCommentList(parent_comment_idx);
	}

	
}
