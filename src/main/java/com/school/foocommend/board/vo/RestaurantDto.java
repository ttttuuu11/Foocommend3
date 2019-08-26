package com.school.foocommend.board.vo;

import lombok.Data;

@Data
public class RestaurantDto {

	//http://localhost:8081/foocommend/recommend/recommendRestaurant?myMenu=%EA%B9%80%EB%B0%A5%20%EB%83%89%EB%A9%B4%20%EB%90%9C%EC%9E%A5%EC%B0%8C%EA%B0%9C%20%EB%96%A1%EB%B3%B6%EC%9D%B4%20%EB%9D%BC%EB%A9%B4%20%EB%A7%A5%EC%A3%BC%20%EB%B3%B4%EC%8C%88%20%EB%B9%84%EB%B9%94%EB%B0%A5%20%EC%82%BC%EA%B2%B9%EC%82%B4
	private Integer restaurant_idx;
	private String restaurant_area;
	private String restaurant_title;
	private String restaurant_food_kind;
	private String restaurant_land_add;
	private String restaurant_street_add;
	private float restaurant_latitude;
	private float restaurant_longitude;
	private String restaurant_menu;
	private String user_name;
	private long restaurant_like;
	private long restaurant_hit;
	private long restaurant_comment;
}
