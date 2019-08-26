<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-link.jspf"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.mainSurvey {
	border-radius: 15px;
	border: 5px solid #ff6666;
}

.surveyTitle {
	text-align: center;
}

.thumb-crop {
	position: relative;
	width: 800px;
	height: 400px;
	overflow: hidden;
}

.thumb-crop img {
	position: absolute;
	left: 50%;
	top: 50%;
	height: 100%;
	width: auto;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.thumb-crop .portrait {
	width: 100%;
	height: auto;
}

.thumb-crop .auto {
	height: auto;
}

.foodImageSet {
	text-align: center;
}
#loadingImg{
	text-align: center;
}
</style>

<body>
	<!-- test -->
	<!-- 011010101100000 -->
	
	
	<!-- 변수 -->
	<input type="hidden" id="upValue" value="${upValue}">

	<!-- loading Image javascript -->
	<script>
		function test() {
			LoadingWithMask('loading.gif');
			setTimeout("closeLoadingWithMask()", 15000);
		}

		function LoadingWithMask(gif) {
			//화면의 높이와 너비
			var maskHeight = $(document).height();
			var maskWidth = window.document.body.clientWidth;

			//화면에 출력할 마스크를 설정
			var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
			var loadingImg = '';

			//loadingImg += " <img src='"
			//		+ "<c:url value='/resources/ui_image/"+gif+"'/> "
			//			+ "' style='position: absolute; display: block; margin: 0px auto; left:60; top:40;'/>";

			//화면에 레이어 추가
			$('body').append(mask)

			//전체 화면
			$('#mask').css({
				'width' : maskWidth,
				'height' : maskHeight,
				'opacity' : '0.5'
			});

			$('#mask').show();

			//로딩중 이미지 표시
			$('#loadingImg').append(loadingImg);
			$('#loadingImg').show();
		}

		function closeLoadingWithMask() {
			$('#mask, #loadingImg').hide();
			$('#mask, #loadingImg').empty();
		}
	</script>



	<div class="row justify-content-center">
		<div class=" col-xl-8 col-lg-8 col-md-10 col-sm-10 col-10">
			<h2 class="surveyTitle">Preference Survey</h2>
			<div class="mainSurvey mt-3">

				<!-- food survey button -->
				<script>
					$(function() {
						$("#likeBTN").click(
								function() {
									var tempMenu = $("#myMenu").val();
									$("#myMenu").val(
											tempMenu
													+ $(".foodImageSet:first")
															.children(":eq(0)")
															.val().slice(0, -4)
													+ " ");
								});
						$("#hateBTN").click(function() {

						});
						$(".nextImage")
								.click(
										function() {
											$(".foodImageSet:first").remove();
											$(".foodImageSet:first").show();

											var valeur = 0;
											var upValue = parseInt($("#upValue")
													.val());

											valeur = $('.progress-bar').attr(
													'aria-valuenow');
											$('.progress-bar')
													.attr(
															'aria-valuenow',
															String((parseInt(valeur) + upValue)));
											if ($('.progress-bar').attr(
													'aria-valuenow') > 100) {
												$('.progress-bar').css('width',
														'100%');
											} else {
												$('.progress-bar')
														.css(
																'width',
																String((parseInt(valeur) + upValue))
																		+ '%');
											}
											if ($(".foodImageSet").length < 1) { //존재하지 않을 경우 로딩 이미지 실행 
												$('.progress-bar').css('width',
												'100%');
												test();
											
												var menu = $("#myMenu").val();
												$("#myMenuForm")
														.attr("action",
																"/foocommend/recommend/recommendRestaurant?myMenu="+menu);
												$("#myMenuForm").attr("method",
														"post");
												$("#myMenuForm").submit();
											}
										});
					});
				</script>
				<form role="form" id="myMenuForm">
					<input type="hidden" value="" id="myMenu" name="myMenu" />
				</form>
				<div class="loadingImg" id="loadingImg">
					<h1 class="mt-6">추천 중...</h1>
				</div>
				<c:forEach var="item" items="${filesNameList }">
					<div class="mt-2 mb-2 foodImageSet thumb-crop">
						<input type="hidden" value="${item}" /> <img class="foodImage"
							src="<c:url value='/resources/survey_image/${item}'/>"
							width="600" height="300">
					</div>
				</c:forEach>
				<div class="row justify-content-center mb-3">
					<button type="button" id="likeBTN"
						class="btn btn-outline-primary mr-5 nextImage">좋아요</button>
					<button type="button" id="hateBTN"
						class="btn btn-outline-warning ml-5 nextImage">별로에요</button>
				</div>
			</div>
			<div class="progress mt-5">
				<div
					class="progress-bar bg-warning progress-bar-striped progress-bar-animated"
					role="progressbar" aria-valuenow="0" aria-valuemin="0"
					aria-valuemax="100" style="width: 0%"></div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$(".foodImageSet").hide();
			$(".foodImageSet:first").show();
			$("#loadingImg").hide()
		});
	</script>
</body>
</html>