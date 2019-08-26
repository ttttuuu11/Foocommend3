package com.school.foocommend.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.school.foocommend.board.vo.RestaurantDto;

@Mapper
public interface RestaurantDao {
	public RestaurantDto selectRestaurant(String restaurantIdx) throws Exception;
	
	public RestaurantDto selectRestaurantScrap(String restaurantIdx) throws Exception;
	
	public void insertRestaurantScrap(Map<String,Object> commandMap) throws Exception;
	
	public void hitRestaurant(String restaurantIdx) throws Exception;
	
	public void likeRestaurant(Map<String,Object> commandMap) throws Exception;
	
	public int countLikeRestaurant(String restaurantIdx) throws Exception;
	
	public int overlapLike(Map<String,Object> commandmap) throws Exception;
	
	public List<RestaurantDto> selectRestaurantListCategory(Map<String,Object> commandMap) throws Exception;
	
	public List<RestaurantDto> selectRestaurantListCategoryInfiniteDown(Map<String,Object> commandMap) throws Exception;
	
}
