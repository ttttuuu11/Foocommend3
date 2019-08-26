package com.school.foocommend.board.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.foocommend.board.dao.RestaurantDao;
import com.school.foocommend.board.service.RestaurantService;
import com.school.foocommend.board.vo.RestaurantDto;
import com.school.foocommend.recommend.dao.RecommendDao;

@Service("RestaurantService")
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantDao restaurantDao;
	
	
	@Override
	public RestaurantDto selectRestaurant(String restaurantIdx) throws Exception {
		restaurantDao.hitRestaurant(restaurantIdx);
		return restaurantDao.selectRestaurant(restaurantIdx);
	}

	@Override
	public RestaurantDto selectRestaurantScrap(String restaurantIdx) throws Exception {
		return restaurantDao.selectRestaurantScrap(restaurantIdx);
	}

	@Override
	public void restaurantScrap(Map<String, Object> commandMap) throws Exception {
		restaurantDao.insertRestaurantScrap(commandMap);
	}

	@Override
	public void likeRestaurant(Map<String, Object> commandMap) throws Exception {			
		restaurantDao.likeRestaurant(commandMap);
	}

	@Override
	public int countLikeRestaurant(String restaurantIdx) throws Exception {
		return restaurantDao.countLikeRestaurant(restaurantIdx);
	}

	@Override
	public int overlapLike(Map<String, Object> commandmap) throws Exception {
		return restaurantDao.overlapLike(commandmap);
	}

	@Override
	public List<RestaurantDto> selectRestaurantListCategory(Map<String, Object> commandMap) throws Exception {
		return restaurantDao.selectRestaurantListCategory(commandMap);
	}

	@Override
	public List<RestaurantDto> selectRestaurantListCategoryInfiniteDown(Map<String, Object> commandMap) throws Exception {
		return restaurantDao.selectRestaurantListCategoryInfiniteDown(commandMap);
	}
}
