package com.school.foocommend.recommend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.school.foocommend.board.vo.RestaurantDto;
import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.recommend.service.RecommendService;
import com.school.foocommend.socket.Client;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "RecommendService")
	private RecommendService recommendService;
	
	@RequestMapping(value = "/recommendRestaurant", method = RequestMethod.POST)
	public ModelAndView recommendRestaurant(Map<String, Object> menuMap, @RequestParam(value = "myMenu", required = false) String myMenu)
			throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/recommend/viewRecommendRestaurant");

		int comIdx = myMenu.indexOf(",");
		String menu = myMenu.substring(0,comIdx);
		log.info(menu);
				
		Client client = new Client(menu);

		List<Map<String, Object>> resultRecommendList = client.getResult();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		log.info(username);
		
		for( Map<String, Object> item : resultRecommendList ){
			recommendService.insertRecommendRestaurant(item.get("store_number").toString(),username);
		}
		return mv;
	}
	
	@GetMapping("/viewRecommendRestaurant")
	public ModelAndView viewRecommendRestaurant() throws Exception {
		ModelAndView mv = new ModelAndView("/board/viewRecommend");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		log.info(username);
		
		// 내게 추천된 식당을 가져옴
		List<Map<String,Object>> recommendRestaurant = recommendService.selectRecommendRestaurant(username);
		List<Map<String, Object>> recommentRestaurantList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> item : recommendRestaurant) {
		// 내게 추천된 식당들에 스크랩 된 것들을 표시함.
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("restaurant_idx",item.get("restaurant_idx"));
			log.debug("1");
			recommentRestaurantList.add(recommendService.selectRestaurantResult(map));
		}
		log.debug(recommentRestaurantList.toString());

		mv.addObject("username",username);
		mv.addObject("restaurantScrapList",recommentRestaurantList);

		
		return mv;
	}
}
