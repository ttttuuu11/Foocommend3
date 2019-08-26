package com.school.foocommend.recommend.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.foocommend.board.vo.RestaurantDto;
import com.school.foocommend.member.dao.MemberDao;
import com.school.foocommend.member.service.MemberService;
import com.school.foocommend.recommend.dao.RecommendDao;
import com.school.foocommend.recommend.service.RecommendService;


@Service("RecommendService")
@Transactional
public class RecommendServiceImpl implements RecommendService{

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@Resource(name = "memberDAO")
//	private MemberDAO memberDAO;
	
	@Autowired
	private RecommendDao recommendDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectRestaurantList(List<Map<String, Object>> resultRecommendList) throws Exception{
		List<Map<String, Object>> restaurantIntroList = new ArrayList<Map<String, Object>>();
		 for( Map<String, Object> restaurantItem : resultRecommendList ){
			 Integer idx = Integer.parseInt(restaurantItem.get("store_number").toString())+2;
			 restaurantIntroList.add((Map<String, Object>) recommendDao.selectRestaurantList(idx.toString()));
		 }
		return restaurantIntroList;
	}
	@Override
	public List<RestaurantDto> selectRestaurantScrap(List<Map<String, Object>> resultRecommendList) throws Exception {
		List<RestaurantDto> restaurantScrapList = new ArrayList<RestaurantDto>();
		 for( Map<String, Object> restaurantItem : resultRecommendList ){
			 Integer idx = Integer.parseInt(restaurantItem.get("store_number").toString())+2;
			restaurantScrapList.add( recommendDao.selectRestaurantScrap(idx.toString()));
		 }
		return restaurantScrapList;	
		}
	
	@Override
	public List<Map<String, Object>> selectRecommendRestaurant(String username) throws Exception {
		return recommendDao.selectRecommendRestaurant(username);
	}
	@Override
	public void insertRecommendRestaurant(String restaurantIdx, String username) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("restaurantIdx",restaurantIdx);
		map.put("username", username);
		
		recommendDao.insertRecommendRestaurant(map);
	}
	@Override
	public Map<String, Object> selectRestaurantResult(Map<String, Object> map) throws Exception {
		log.debug("2");

		return recommendDao.selectRestaurantResult(map);
	}
}
