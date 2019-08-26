package com.school.foocommend.recommend.service;

import java.util.List;
import java.util.Map;

import com.school.foocommend.board.vo.RestaurantDto;


public interface RecommendService {
	List<Map<String, Object>> selectRestaurantList(List<Map<String, Object>> resultRecommendList) throws Exception;
	
	List<RestaurantDto> selectRestaurantScrap(List<Map<String, Object>> resultRecommendList) throws Exception;
	
	List<Map<String,Object>> selectRecommendRestaurant(String username) throws Exception;
	
	void insertRecommendRestaurant(String restaurantIdx,String username) throws Exception;

	Map<String,Object> selectRestaurantResult(Map<String,Object> map) throws Exception;
}
