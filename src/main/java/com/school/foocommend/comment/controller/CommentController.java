package com.school.foocommend.comment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.foocommend.board.vo.RestaurantDto;
import com.school.foocommend.comment.service.CommentService;
import com.school.foocommend.comment.vo.CommentDto;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "CommentService")
	private CommentService commentService;

	@RequestMapping(value = "/insertComment", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> insertComment(@RequestBody Map<String, Object> commandMap) throws Exception {

		commentService.insertComment(commandMap);
		
		log.info(commandMap.get("restaurantIdx").toString());
		List<Map<String,Object>> result = commentService.selectCommentList(commandMap.get("restaurantIdx").toString());

		return result;
	}

	@RequestMapping(value = "/selectReplyComment", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> selectReplyComment(@RequestBody Map<String, Object> commandMap)
			throws Exception {

		log.info("셀렉트");
		List<Map<String,Object>> result = commentService.selectReplyCommentList(commandMap.get("parent_comment_idx").toString());
		return result;
	}
}
