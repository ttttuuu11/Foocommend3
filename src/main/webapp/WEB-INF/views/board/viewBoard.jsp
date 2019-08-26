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
	</div>
</body>
</html>