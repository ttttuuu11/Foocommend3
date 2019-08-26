package com.school.foocommend.board.controller;

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

import com.school.foocommend.board.service.RestaurantService;

@Controller
@RequestMapping("/board")
public class DetailController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "RestaurantService")
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/likeRestaurant", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> likeRestaurant(@RequestBody Map<String, Object> commandMap)
			throws Exception {
		log.info(commandMap.get("restaurantIdx").toString());
		log.info(commandMap.get("username").toString());
		
		String msg="이미 좋아요한 식당입니다.";
		int overlap = restaurantService.overlapLike(commandMap);
		if(overlap==0) {
			restaurantService.likeRestaurant(commandMap);
			msg="좋아요.";
		}
		int countL = restaurantService.countLikeRestaurant(commandMap.get("restaurantIdx").toString());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg",msg);
		result.put("countL",countL);
		return result;
	}
}
