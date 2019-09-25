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
.profileImage {
	border-radius: 50%;
}
</style>

	<!-- border: solid 3px #fff000; -->
<body>

	

	<div class="row">
		<!-- foodCategorys -->
		<nav
			class="col-sm-3 col-md-3 col-lg-3 col-xl-3 d-none d-sm-block bg-light sidebar justify-content-center">
			<h3 style="text-align: center">CATEGORY</h3>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/rice.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>김밥/도시락</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
				<div class="mt-2 mb-2">
					<img src="<c:url value='/resources/food_category/cheers.png'/>"
						width="50px" height="50px"><span class="ml-2"><b>선술집</b></span>
				</div>
			</div>
			<div
				class="d-flex justify-content-center mt-3 flex-fill p-1 shadow food_category">
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
		</nav>
		<!-- end -->

		<div class="card  col-sm-12 col-md-12 col-lg-9 col-xl-9 mt-2 shadow">
			<div class="row">
				<div class="mt-3 ml-4 mr-3">
					<img src="<c:url value='/resources/ui_image/find-my-friend.png'/>"
						width="70px" height="70px">
				</div>
				<div>
					<h2 class="mt-4">마이페이지</h2>
				</div>
			</div>
			<div class="row justify-content-center mt-4">
				<div class="col-md-5 text-center">
					<img class="profileImage shadow"
						src="<c:url value='/resources/userProfile/${member.profile_img }'/>"
						width="200px" height="200px">
					<!-- 유저 프로필 이미지 넣기  -->
					<br>
					<button id="changeProfileImgBTN" class="btn btn-outline-info mt-4">프로필
						변경</button>
				</div>
				<div class="col-md-5" style="vertical-align: middle">
					<h2 class="mt-3">${username}</h2>
					<h5>
						<small><img
							src="<c:url value='/resources/ui_image/calendar.png'/>" width="15px"
							height="15px"> 가입일 &nbsp;&nbsp; <img
							src="<c:url value='/resources/ui_image/fork.png'/>"
							width="15px" height="15px"> 활동점수&nbsp;&nbsp; </small>
					</h5>
					<button id="mailAuthBTN" class="btn btn-outline-info mt-4">메일인증</button>
				</div>
			</div>
		</div>

	</div>
</body>

<!-- mail auth -->
	<script>
		$(function() {
			$("#mailAuthBTN").on("click", function() {
				swal.fire(
						{
							title : '이메일을 입력하세요',
							html : '<input type="text" name="emailForm" id="emailForm" class="form-control">'
						}).then(function() {
							var emailForm = $("#emailForm").val();
							var check ="";
							$.ajax({
								url : '/foocommend/member/mailCheck',
								headers : {
									"Content-Type" : "application/json; charset=UTF-8",
									"X-HTTP-Method-Override" : "POST"
								},
								dataType : 'json',
								type : 'post',
								data : JSON.stringify({ // 서버로 보낼 데이터 명시
									email: emailForm
								}),
								success : function(data) {
									$(data).each(function() {
										check = String(this.check)
									});
									console.log(check)
									if(check=="F"){
										//const Toast = Swal.mixin({
										//	toast : true,
										//	showConfirmButton : false,
										//	timer : 3000
										//});
	
										swal.fire({
											type : 'warning',
											title : '메일주소가 잘못되었습니다.',
											html:'회원가입 시 작성한 메일주소가 맞는 지 확인해주세요.'
										})
									}else{
										$.ajax({
											url : '/foocommend/member/mail',
											headers : {
												"Content-Type" : "application/json; charset=UTF-8",
												"X-HTTP-Method-Override" : "POST"
											},
											dataType : 'json',
											type : 'post',
											data : JSON.stringify({ // 서버로 보낼 데이터 명시
												
											}),
											success : function() {
											
											}
										});
										swal.fire({
											type : 'success',
											title : '메일을 확인해주세요'
										})
									}
								}
							});
				});
			});

			$("#changeProfileImgBTN")
					.on(
							"click",
							function() {
								swal
										.fire(
												{
													title : '이미지 선택',
													html : '<form id="imageForm" method="post" action="/foocommend/upload/uploadForm" enctype="multipart/form-data"><input type="file" name="file"></form>'
												}).then(function() {
											$("#imageForm").submit();
											alert("파일 업로드 됨");
										});
							});
		})

		function post_to_url(path, params, method) {
			console.log("post_to_url임");
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", path);
			form.setAttribute("enctype", "multipart/form-data");
			for ( var key in params) {
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "file");
				hiddenField.setAttribute("name", key);
				hiddenField.setAttribute("value", params[key]);
				form.appendChild(hiddenField);
			}
			document.body.appendChild(form);
			form.submit();
		}
	</script>
</html>