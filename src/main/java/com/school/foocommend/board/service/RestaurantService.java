package com.school.foocommend.board.service;

import java.util.List;
import java.util.Map;

import com.school.foocommend.board.vo.RestaurantDto;

public interface RestaurantService {
	RestaurantDto selectRestaurant(String restaurantIdx) throws Exception;
	
	RestaurantDto selectRestaurantScrap(String restaurantIdx) throws Exception;
	
	void restaurantScrap(Map<String, Object> commandMap) throws Exception;
	
	void likeRestaurant(Map<String,Object> commandMap) throws Exception;
	
	int countLikeRestaurant(String restaurantIdx) throws Exception;

	int overlapLike(Map<String,Object> commandmap) throws Exception;
	
	List<RestaurantDto> selectRestaurantListCategory(Map<String,Object> commandMap) throws Exception;
	
	List<RestaurantDto> selectRestaurantListCategoryInfiniteDown(Map<String,Object> commandMap) throws Exception;
}
