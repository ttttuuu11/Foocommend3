<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-link.jspf"%>
<%@ include file="/WEB-INF/include/chat.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.recommend-card {
	border-bottom: 2px solid gold;
}

.category-btn {
	background-color: #f8f9fa;
	margin: 3px;
	height: 200px
}

.category-text {
	margin-top: 50%;
}

.card-columns { @include media-breakpoint-only(lg) { column-count:4;
	
}
@
include






media-breakpoint-only






(
xl






){
column-count






5;
}
}
</style>


</head>
<body>
	<!-- value -->
	<input type="hidden" value="${username }" id="username">



	<div class="row">
		<!-- foodCategorys -->
		<input type="hidden" value="${CATEGORY_IDX }" id="CATEGORY_IDX">
		<div
			class="col-sm-3 col-md-3 col-lg-3 col-xl-3 d-none d-sm-block bg-light sidebar justify-content-center navbar navbar-expand">
			<h3 style="text-align: center">CATEGORY</h3>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<input type="hidden" value="0">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/rice.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>김밥/도시락</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<input type="hidden" value="1">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/cheers.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>선술집</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<input type="hidden" value="2">
				<div class="mt-2 mb-2">
					<img
						src="<c:url value='/resources/food_category/tikka-masala.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>외국음식</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/tea-cup.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>카페</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/sushi.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>일식</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/ramen.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>중식</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/marlin.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>생선회</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/food-truck.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>이동요리</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/bread2.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>제과점</b></span>
				</div>
			</div>
		</div>
		<!-- recommend menu -->
		<div class="card-columns col-sm-12 col-md-12 col-lg-9 col-xl-9">
			<c:forEach var="restaurantItem" items="${restaurantScrapList }"
				varStatus="status">
				<div class="card p-3 shadow">
					<input type="hidden" value="${restaurantItem.restaurant_idx}"
						data-bno="${restaurantItem.restaurant_idx}" class="rIdx" /> <img
						class="card-img-top restaurantCardImage"
						src="<c:url value='/resources/store_image/${restaurantItem.restaurant_idx}.jpg'/>"
						alt="X">
					<div class="card-body restaurantCard">
						<h5 class="card-title">${restaurantItem.restaurant_title }</h5>
						<small>${restaurantItem.restaurant_street_add}</small>
						<hr>
						<p class="card-text">${restaurantItem.restaurant_menu }</p>
					</div>
					<div class="row flex-row-reverse mr-2">
						<c:if test="${empty restaurantItem.user_name }">
							<a class="scrapBTN"><img
								src="<c:url value='/resources/ui_image/star_.png'/>"
								width="30px" height="30px"></a>
							<!-- <button type="button"	
								class="btn btn-outline-warning ml-2 scrapBTN">스크랩</button>
							 -->
						</c:if>
						<c:if test="${!empty restaurantItem.user_name }">
							<a class="scrapBTN"><img
								src="<c:url value='/resources/ui_image/star.png'/>" width="30px"
								height="30px"></a>
							<!-- <button type="button"
								class="btn btn-outline-warning ml-2 scrapBTN">스크랩</button>
							-->
						</c:if>
						<a class="likeBTN mr-4"><img
							src="<c:url value='/resources/ui_image/like_.png'/>" width="30px"
							height="30px"></a>
						<!-- <button type="button" class="btn btn-outline-primary likeBTN">좋아요</button> -->
					</div>
				</div>
				<input type="hidden" class="scrolling">

			</c:forEach>
		</div>
		<div class="col">
			<button type="button" class="btn btn-outline-primary scrollBTN col-12">더보기</button>
		</div>
	</div>
</body>
	<!-- menu click -->
	<script>
		$(function() {
			$(".restaurantCard")
					.on(
							"click",
							function() {
								restaurantIdx = $(this).parent().children(
										":eq(0)").val();
								location.href = "/foocommend/board/detail?restaurantIdx="
										+ restaurantIdx;
							});
			$(".restaurantCardImage")
					.on(
							"click",
							function() {
								restaurantIdx = $(this).parent().children(
										":eq(0)").val();
								location.href = "/foocommend/board/detail?restaurantIdx="
										+ restaurantIdx;
							});
			$(".restaurantCard").hover(function() {
				$(this).parent().css("border", "solid 3px #ffaaaa");
			}, function() {
				$(this).parent().css("border", "solid 0px");
			});
			$(".restaurantCardImage").hover(function() {
				$(this).parent().css("border", "solid 3px #ffaaaa");
			}, function() {
				$(this).parent().css("border", "solid 0px");
			});
		});
	</script>

	<!-- scrap ajax -->
	<script>
		$(function() {
			$(".scrapBTN")
					.on(
							"click",
							function() {
								$(this)
										.children(":eq(0)")
										.attr("src",
												"<c:url value='/resources/ui_image/star.png'/>")
								var restaurantIdx_ = $(this).parent().parent()
										.children(":eq(0)").val();
								var userName_ = $("#username").val()
								$
										.ajax({
											url : '/foocommend/board/scrap',
											headers : {
												"Content-Type" : "application/json; charset=UTF-8",
												"X-HTTP-Method-Override" : "POST"
											},
											dataType : 'json',
											type : 'post',
											data : JSON.stringify({ // 서버로 보낼 데이터 명시
												restaurantIdx : restaurantIdx_,
												userName : userName_
											}),
											success : function() {
												//데이터 전송 성공
												const Toast = Swal.mixin({
													toast : true,
													position : 'top-end',
													showConfirmButton : false,
													timer : 3000
												});

												Toast.fire({
													type : 'success',
													title : '스크랩 성공!'
												})
											}
										});
							});

			$(".scrollBTN")
					.on(
							"click",
							function() {
								var lastbno = $(".rIdx:last").attr("data-bno");
								var cIdx = $("#CATEGORY_IDX").val();

								$
										.ajax({
											type : 'post',
											url : '/foocommend/board/infiniteScrollRestaurant',
											headers : {
												"Content-Type" : "application/json",
												"X-HTTP-Method-Override" : "POST"
											},
											dataType : 'json',
											data : JSON.stringify({
												bno : lastbno,
												restaurant_food_kind : cIdx
											}),
											success : function(data) {
												console.log(data);
												var str = "";
												console.log("data1" + this);
												// 5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다.
												if (data != "") {
													//6. 서버로부터 받아온 data가 list이므로 이 각각의 원소에 접근하려면 each문을 사용한다.
													$(data)
															.each(
																	// 7. 새로운 데이터를 갖고 html코드형태의 문자열을 만들어준다.
																	function() {
																		str += '<div class="card p-3 shadow">'
																				+ '<input type="hidden" value="'+this.restaurant_idx+'"'
																				+ 'data-bno="'+this.restaurant_idx+'"'+' class="rIdx" /> <img'
																				+ 'class="card-img-top restaurantCardImage"'
																				+ 'src="<c:url value="'
																				+'/resources/store_image/'
																				+ this.restaurant_idx
																				+'.jpg"/>"'
																				+ 'alt="X">'
																				+ '<div class="card-body restaurantCard">'
																				+ '<h5 class="card-title">'
																				+ this.restaurant_title
																				+ '</h5>'
																				+ '<small>'
																				+ this.restaurant_street_add
																				+ '</small>'
																				+ '<hr>'
																				+ '<p class="card-text">'
																				+ this.restaurant_menu
																				+ '</p>'
																				+ '</div>'
																				+ '<div class="row flex-row-reverse mr-2">'
																				+ '<c:if test="${empty '+this.user_name+'}">'
																				+ '<a class="scrapBTN"><img'
																				+ 'src="<c:url value="'+'/resources/ui_image/star_.png"/>"'
																				+ 'width="30px" height="30px"></a>'
																				+ '</c:if>'
																				+ '<c:if test="${!empty '
																				+ this.user_name
																				+ '}">'
																				+ '<a class="scrapBTN"><img'
																				+ 'src="<c:url value="'+'/resources/ui_image/star.png"/>" width="30px"'
																				+ 'height="30px"></a>'
																				+ '</c:if>'
																				+ '<a class="likeBTN mr-4"><img'
																				+ 'src="<c:url value="'+'/resources/ui_image/like_.png"/>" width="30px"'
																				+ 'height="30px"></a>'
																				+ '</div>'
																				+ '</div>'
																				+ '<input type="hidden" class="scrolling">';
																	});
													$(".scrolling:last").after(
															str);
												}// if : data!=null
												else { // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..
													console
															.log("더 불러올 데이터가 없습니다.");
												}// else
											}// success
										});// ajax	
							});
		});
	</script>
</html>