<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-link.jspf"%>
<%@ include file="/WEB-INF/include/chat.jspf"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
.content {
	font-size: 13px;
}

.commentMenu {
	font-size: 10px;
	color: #6E6E6E;
}

.comment_username {
	font-weight: bold;
	font_size: 14px;
	color: #2E2E2E;
}

.comment_date {
	font_size: 10px;
	color: #6E6E6E;
}

.wrap {
	width: 500px;
}

.wrap textarea {
	width: 100%;
	resize: none;
	overflow-y: hidden;
	padding: 0.5em;
	padding-bottom: 0.2em;
	line-height: 1.6;
}
</style>


<body>
	

	<div class="row">
		<!-- foodCategorys -->
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
		<!-- end -->

		<div class="card  col-sm-12 col-md-12 col-lg-9 col-xl-9 mt-2 shadow">
			<div class="row">
				<div class="mt-3 ml-4 mr-3">
					<img src="<c:url value='/resources/ui_image/store.png'/>"
						width="70px" height="70px">
				</div>
				<div>
					<input type="hidden" value="${restaurant.restaurant_idx}"
						id="restaurantIdx">
					<h2 class="mt-3">${restaurant.restaurant_title }</h2>
					<h5>
						<small><img
							src="<c:url value='/resources/ui_image/view.png'/>" width="15px"
							height="15px"> ${restaurant.restaurant_hit }&nbsp;&nbsp; <img
							src="<c:url value='/resources/ui_image/comment.png'/>"
							width="15px" height="15px"> ${restaurant.restaurant_comment }&nbsp;&nbsp;
						</small>
					</h5>
				</div>
			</div>
			<div class="ml-4">
				<!-- 
				<h6 class="mt-2">분류</h6>
				<p>${restaurant.restaurant_food_kind }</p>
				 -->
				<h6 class="mt-2">주소</h6>
				<p>${restaurant.restaurant_street_add}</p>
				<h6 class="mt-2">
					<a data-toggle="collapse" href="#collapseExample"
						aria-expanded="false" aria-controls="collapseExample">
						메뉴&nbsp;< </a>
				</h6>
				<div class="collapse ml-1" id="collapseExample">
					<p>-${restaurant.restaurant_menu }</p>
				</div>
			</div>
			<div
				class="col-sm-11 col-md-11 col-lg-11 col-xl-11 mt-5	 justify-content-center">
				<div id="map" style="width: 600px; height: 300px;"></div>
				<br>
				<div class="container text-center mt-5">
					<a id="likeBTN"> <img
						src="<c:url value='/resources/ui_image/like_hand.png'/>"
						width="60px" height="60px">
					</a>
					<h5 class="mt-1 likeCount">${countL}</h5>
				</div>

				<hr>
				<div class="container mt-2 commentArea">
					<div class="removeCommentArea">
						<c:forEach var="item" items="${commentList }">
							<div class="comment">
								<div class="row">
									<img
										src="<c:url value='/resources/userProfile/${item.profile_img }'/>"
										width="50px" height="50px">
									<p class="mt-3 ml-2 comment_username">${item.username }</p>
									<p class="mt-3 ml-1 comment_date">
										<small>${item.create_date }</small>
									</p>
								</div>
								<div class="row">
									<div class="col-lg-11 col-md-11 mb-1">
										<p class="content">${item.comment_content }</p>
									</div>
								</div>
								<div class="row justify-content-between mt-2">
									<input type="hidden" id="comment_idx"
										value="${item.comment_idx }">
									<c:if test="${0 ne item.count_reply_comment}">
										<a class="commentMenu ml-2 showReplyCommentBTN">답글보기</a>
									</c:if>
									<c:if test="${0 eq item.count_reply_comment}">
										<a class="commentMenu"></a>
									</c:if>
									<div class="row justify-content-end mt-1">
										<a class="commentMenu mr-1 reply">답글</a> <a
											class="commentMenu mr-1" href="#">수정</a> <a
											class="commentMenu mr-1" href="#">삭제</a>

									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="commentForm" id="commentForm">
						<input type="hidden" id="restaurant_idx" name="restaurant_idx"
							value="${restaurant.restaurant_idx}" /> <input type="hidden"
							id="username" name="username" value="${username }" /> <input
							type="hidden" id="profile_img" name="profile_img"
							value="${profile_img }" /> <input type="hidden"
							id="parent_comment_idx" value="-1"> <input type="hidden"
							id="reply_username" value="-1">

						<div class="row">
							<img src="/foocommend/resources/userProfile/${profile_img}"
								width="50px" height="50px">
							<p class="mt-3 ml-2 comment_username">${username }</p>
						</div>
						<div class="row">
							<div class="col-lg-11 col-md-11 mb-1 mt-1 wrap">
								<textarea id="comment_content" name="comment_content" rows="1"
									placeholder="댓글을 작성하세요"></textarea>
							</div>
							<button class="col-md-1 mb-2 btn btn-primary"
								id="insertCommentBTN" name="insertCommentBTN">등록</button>
						</div>
					</div>
				</div>

			</div>
		</div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1802d71b18ddcfcd0598b78bd8711b62"></script>
			
		<script>
			// 지도 크기 변경
			//var maxWidth = document.body.scrollWidth-130
			
			var container = document.getElementById('map');
			//container.style.width=(String(maxWidth)+"px");
			var options = {
				center : new kakao.maps.LatLng(${restaurant.restaurant_latitude}, ${restaurant.restaurant_longitude}),
				level : 3
			};
			var map = new kakao.maps.Map(container, options);
			
			// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
			var mapTypeControl = new kakao.maps.MapTypeControl();
	
			// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
			// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
			map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
			// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
			var zoomControl = new kakao.maps.ZoomControl();
			map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
			
			var markerPosition  = new kakao.maps.LatLng(${restaurant.restaurant_latitude}, ${restaurant.restaurant_longitude}); 
			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			
			// map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);    
		</script>
	</div>
</body>
<!-- BTN -->
	<script>
		$(function(){
		$("#likeBTN").on("click",function(){
			var restaurantIdx= $("#restaurantIdx").val();
			$.ajax({
						type : 'post', // 요청 method 방식
						url : '/foocommend/board/likeRestaurant',// 요청할 서버의 url
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "POST"
						},
						dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
						data : JSON.stringify({ // 서버로 보낼 데이터 명시
							username : '<%=session.getAttribute("username")%>',
							restaurantIdx : restaurantIdx
						}),
						success : function(data) {// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
							console.log(data);
							if (data != "") {
								$(data).each(function() {
									const Toast = Swal.mixin({
										toast : true,
										position : 'top-end',
										showConfirmButton : false,
										timer : 3000
									});
									
									if($(".likeCount").text()==this.countL){
										Toast.fire({
											type : 'warning',
											title : this.msg
										});
									}else{
										Toast.fire({
											type : 'success',
											title : this.msg
										});
										$(".likeCount").text(this.countL);
									}
								});
							}// if : data!=null
						}// success
					});// ajax
		});
		$("#insertCommentBTN").on("click",function(){
			var restaurant_idx= $("#restaurant_idx").val();
			var username= $("#username").val();
			var comment_content= $("#comment_content").val();
			var profile_img = $("#profile_img").val();	
			var reply_username=$("#reply_username").val();
			var parent_comment_idx=$("#parent_comment_idx").val();
						
			$("#comment_content").val("");
				$.ajax({
					type : 'post', // 요청 method 방식
					url : '/foocommend/comment/insertComment',// 요청할 서버의 url
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시
					data : JSON.stringify({ // 서버로 보낼 데이터 명시
						username : username,
						restaurantIdx : restaurant_idx,
						commentContent : comment_content,
						profileImg : profile_img,
						reply_username : reply_username,
						parent_comment_idx : parent_comment_idx
					}),	
					success : function(data) {
						var str = '';	
						if(data!=''){	
							$(data).each(function() {
									str+= '<div class="comment">'
									+'<div class="row">'
									+'<img src="/foocommend/resources/userProfile/'+this.profile_img+'"'
										+'width="50px" height="50px">'
									+'<p class="mt-3 ml-2 comment_username">'+this.username+'</p>'
									+'<p class="mt-3 ml-1 comment_date">'
										+'<small>'+this.create_date+'</small>'
									+'</p>'
								+'</div>'
								+'<div class="row">'
									+'<div class="col-lg-11 col-md-11 mb-1 mt-1">'
										+'<p class="content">'+this.comment_content+'</p>'
									+'</div>'
								+'</div>'
								+'<div class="row justify-content-end mt-1">'
									+'<a class="commentMenu mr-1 reply">답글</a>'
										+'<a class="commentMenu mr-1" href="#">수정</a>'
										+'<a class="commentMenu mr-1" href="#">삭제</a>'
								+'</div>'
							+'</div>';
							});
						}
						$(".removeCommentArea").remove();
						$(".commentArea").after(str);
						
						var commentForm = $("#commentForm").detach();
						var commentReplyForm = $(this).parent().parent().parent();
						$(".comment:last").after(commentForm);
						const Toast = Swal.mixin({
							toast : true,
							position : 'top-end',
							showConfirmButton : false,
							timer : 3000
						});
						Toast.fire({
								type : 'success',
								title : '댓글을 등록했습니다.'
						});
						console.log(data);
						if (data != "") {
							$(data).each(function() {
								console.log(this.comment_idx)
							});
						}
					}// success
				});// ajax
		});
		
		$(".reply").each(function(i) {
			$(this).on("click",function() {
				var commentForm = $("#commentForm").detach();
				var commentReplyForm = $(this).parent().parent().parent();
				commentReplyForm.after(commentForm);
				commentForm.children(":eq(3)").val($(this).parent().parent().children(":eq(0)").val());
				commentForm.children(":eq(4)").val($(this).parent().parent().parent().children(":eq(0)").children(":eq(1)").text());
				$('#commentForm').addClass('ml-5');
				
			});
		});
		
		$(".showReplyCommentBTN").on("click",function(){
			var table = $(this).parent();
			var comment_idx = $(this).parent().children(":eq(0)").val();
			$('.showReplyCommentBTN').removeClass('showReplyCommentBTN');

			$.ajax({
				type : 'post', // 요청 method 방식
				url : '/foocommend/comment/selectReplyComment',// 요청할 서버의 url
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
				data : JSON.stringify({ // 서버로 보낼 데이터 명시
					parent_comment_idx : comment_idx					
				}),	
				success : function(data) {// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
					var str = '';	
					console.log(data);
					if(data!=''){	
						$(data).each(function() {
								str+= '<div class="replyComment'+this.parent_comment_idx+'">'
								+'<div class="comment ml-5">'
								+'<div class="row">'
								+'<img src="/foocommend/resources/userProfile/'+this.profile_img+'"'
									+'width="50px" height="50px">'
								+'<p class="mt-3 ml-2 comment_username">'+this.username+'</p>'
								+'<p class="mt-3 ml-1 comment_date">'
									+'<small>'+this.create_date+'</small>'
								+'</p>'
							+'</div>'
							+'<div class="row">'
								+'<div class="col-lg-11 col-md-11 mb-1 mt-1">'
									+'<p class="content">'+this.comment_content+'</p>'
								+'</div>'
							+'</div>'
							+'<div class="row justify-content-end mt-1">'
								+'<a class="commentMenu mr-1 reply">답글</a>'
									+'<a class="commentMenu mr-1" href="#">수정</a>'
									+'<a class="commentMenu mr-1" href="#">삭제</a>'
							+'</div>'
						+'</div></div>';
						});
					}
					
					$(".replyComment"+comment_idx).remove();
					table.after(str);
				}// success
			});// ajax
		});
	})
	</script>
	<script>
	    $(document).ready(function() {
	     	 $('.wrap').on( 'keyup', 'textarea', function (e){
	    		$(this).css('height', 'auto' );
	      		$(this).height( this.scrollHeight );
	     	 });
	     	 $('.wrap').find( 'textarea' ).keyup();
	    });
  </script>
</html>