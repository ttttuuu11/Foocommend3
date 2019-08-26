package com.school.foocommend.recommend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.school.foocommend.board.vo.RestaurantDto;

//    resources/mybatis/mappers/member_sql 에서 구현됨.

@Mapper
public interface RecommendDao {
	public Map<String, Object> selectRestaurantList(String idx) throws Exception;
	
	public RestaurantDto selectRestaurantScrap(String restaurantIdx) throws Exception;

	public List<Map<String,Object>> selectRecommendRestaurant(String username) throws Exception;

	public void insertRecommendRestaurant(Map<String,String> map) throws Exception;
	
	Map<String,Object> selectRestaurantResult(Map<String,Object> map) throws Exception;
}
