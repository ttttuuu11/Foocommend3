package com.school.foocommend.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.school.foocommend.board.service.RestaurantService;
import com.school.foocommend.board.vo.RestaurantDto;
import com.school.foocommend.comment.service.CommentService;
import com.school.foocommend.comment.vo.CommentDto;
import com.school.foocommend.mail.MailSend;
import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.member.vo.UserDto;
import com.school.foocommend.recommend.service.RecommendService;
import com.school.foocommend.socket.Client;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
@SessionAttributes({"username","profile_img"})
public class BoardController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "RestaurantService")
	private RestaurantService restaurantService;
	
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Resource(name = "CommentService")
	private CommentService commentService;
	
	@Resource(name = "RecommendService")
	private RecommendService recommendService;

	

	@RequestMapping(value = "/main")
	public ModelAndView BoardList(Map<String, Object> commandMap, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("/board/viewBoard");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		String password = userDetails.getPassword();

		UserDto member = memberService.selectMember(username);
		log.info(member.getProfile_img());
		String profile_img = member.getProfile_img();
		model.addAttribute("profile_img",profile_img);
		model.addAttribute("username", username);

		log.info(username);
		log.info(password);
		
		List<Map<String,Object>> popularRestaurantList = restaurantService.selectPopularRestaurant();
		
		mv.addObject("popularRestaurantList",popularRestaurantList);
		mv.addObject("username",username);
		mv.addObject("password",password);
		
		return mv;
	}

	@RequestMapping(value = "/survey")
	public ModelAndView SurveyRecommend(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/board/surveyRecommend");

		File folder = new File("D:\\workspace\\resources\\foocommend\\survey_image");
		File[] listOfFiles = folder.listFiles();

		List<String> filesNameList = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				filesNameList.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		mv.addObject("filesNameList", filesNameList);
		mv.addObject("upValue", Math.ceil(100 / filesNameList.size()));
		return mv;
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView RestaurantDetail(Map<String, Object> commandMap,  @RequestParam(value = "restaurantIdx", required = false) String restaurantIdx ) throws Exception {
		ModelAndView mv = new ModelAndView("/board/viewDetail");
		RestaurantDto restaurant = restaurantService.selectRestaurant(restaurantIdx);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		
		commandMap.put("restaurantIdx",restaurantIdx);
		commandMap.put("username",username);
		
		restaurantService.insertViewRestaurant(commandMap);
		
		String tempMenu = restaurant.getRestaurant_menu();
		int countL = restaurantService.countLikeRestaurant(restaurantIdx);
		
		if(tempMenu.indexOf(" ")>0) {
			restaurant.setRestaurant_menu(tempMenu.replaceAll(" ", "<br>-"));
		}		

		List<Map<String,Object>> commentList = commentService.selectCommentList(restaurantIdx);

		mv.addObject("commentList",commentList);
		mv.addObject("restaurant",restaurant);
		mv.addObject("countL", countL);
		return mv;
	}
	
	@RequestMapping(value = "/scrap", method = RequestMethod.POST)
	public @ResponseBody Boolean RestaurantScrap(@RequestBody Map<String, Object> commandMap)
			throws Exception {
		restaurantService.restaurantScrap(commandMap);
		
		return true;
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView test(Map<String, Object> commandMap,  @RequestParam(value = "restaurantIdx", required = false) String restaurantIdx ) throws Exception {
		ModelAndView mv = new ModelAndView("/mail/mailContent");
		
		
		return mv;
	}

	@RequestMapping(value = "/restaurantList")
	public ModelAndView restaurantList(Map<String, Object> commandMap,  @RequestParam(value = "restaurantFoodKind", required = false) String restaurantFoodKind ) throws Exception {
		ModelAndView mv = new ModelAndView("/board/viewRestaurantList");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		log.info(username);
		
		Map<String,Object> foodKind = new HashMap<String,Object>();
		foodKind.put("restaurant_food_kind",restaurantFoodKind);
		// 내게 추천된 식당을 가져옴
		List<RestaurantDto> categoryRestaurantList = restaurantService.selectRestaurantListCategory(foodKind);
		List<Map<String, Object>> recommentRestaurantList = new ArrayList<Map<String,Object>>();
		for(RestaurantDto item : categoryRestaurantList) {
		// 내게 추천된 식당들에 스크랩 된 것들을 표시함.
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("restaurant_idx",item.getRestaurant_idx());
			log.debug("1");
			recommentRestaurantList.add(recommendService.selectRestaurantResult(map));
		}
		log.debug(recommentRestaurantList.toString());

		mv.addObject("username",username);
		mv.addObject("CATEGORY_IDX",restaurantFoodKind);
		mv.addObject("restaurantScrapList",recommentRestaurantList);

		
		return mv;
	}
	
	@RequestMapping(value = "/infiniteScrollRestaurant", method = RequestMethod.POST)
	public @ResponseBody List<RestaurantDto> infiniteScrollRestaurant(@RequestBody Map<String, Object> commandMap)
			throws Exception {
		Integer start_no = (Integer.parseInt((String) commandMap.get("bno"))) + 1;

		commandMap.put("start_no", start_no);

		List<RestaurantDto> listAll = restaurantService.selectRestaurantListCategoryInfiniteDown(commandMap);

		return listAll;
	}

}

