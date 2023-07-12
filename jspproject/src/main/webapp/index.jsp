<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />


<title>영화 리뷰</title>
<link rel="stylesheet" href="./css/style.css" />
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/album-rtl/" />




</head>
<body>
	<c:if test="${movieList == null && error == null}">
		<jsp:forward page="list" />
	</c:if>
	<table class="movie_list">
		<section class="py-5 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<h1 class="header">영화 리뷰</h1>

				</div>
			</div>
		</section>
		
		
		<div class="bt-write" style="width: 150px; height: 30px; background-color: aqua; margin-left:20px; text-align: center; position: relative; " >
			<a href="write" style="color: black; margin: 0 auto; text-decoration-line: none; "  >글쓰기</a>
		</div>
		
		
		<div class="container">
			<c:forEach var="movie" items="${movieList}" varStatus="status">
				<div class="col">
					<div class="card shadow-sm">
					
						<div class="cont">
							<div class="inla" >
								<img src="${movie.img}" alt="업로드 이미지" style="width: 278px; height: 180px; margin: 5px 0 0 5px; display: inline;">
								<p style="display: inline; margin: 0 0 0 20px"> ${movie.content}</p>
							</div>
						</div>
						<div class="card-body">

							<div class="d-flex justify-content-between align-items-center" style="display: inline;">
								<div>
									<p class="card-text" style="font-size: 20px;">${movie.title} </p>
								</div>
								<div class="bt_wrap" style="font-size: 15px;" >
							
									 <a href="edit?movie_no=${movie.movie_no}">수정</a> 
									<a  onclick="chkDelete(${movie.movie_no}); return false;">삭제하기</a>
									<p class="card-text" style="text-align: right;">${movie.reg_date}</p>
								</div>


							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</table>

		

	<!-- JavaScript Bundle with Popper -->

	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
	<!-- 제이쿼리 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<!-- js -->
	<script type="text/javascript" src="./script.js"></script>
	<script>
  	<c:if test="${param.error != null}">
	alert("${param.error}");
</c:if>
<c:if test="${error != null}">
	alert("${error}");
</c:if>
	</script>
</body>
</html>
