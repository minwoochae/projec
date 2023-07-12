<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<style>
@charset "utf-8";

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.col{
	margin: 15px 0;
	width: 1000px;
	height: 300px
}

.card-body  button {
	width:50px;
	height:30px;
	border: none;
	margin: 0 6px;
}

.header {
	padding-bottom:15px;
	border-bottom: 1px solid black;
}



/* 초기화 태그 */

* {
  margin: 0;
  padding: 0;
}

html {
  font-size: 10px;
}

ul,
li {
  list-style: none;
}

a {
  text-decoration: none;
  color: inherit;
  cursor:pointer;
}

/* 공통 */

.bt_wrap {
  margin-top: 30px;
  text-align: center;
  font-size: 0;
}

.bt_wrap a {
  display: inline-block;
  min-width: 80px;
  margin-left: 10px;
  padding: 10px;
  border: 1px solid #000;
  border-radius: 2px;
  font-size: 1.4rem;
  text-align: center;
}

.bt_wrap a:hover {
  text-decoration: underline;
}

.bt_wrap a.on {
  background: #000;
  color: #fff;
}

/* list.html */

.wrap {
  width: 1000px;
  margin: 0 auto;
}
.movie_list {
  width: 100%;
  border-top: 1px solid #444444;
  border-collapse: collapse;
  font-size: 1.5rem;
}
.movie_list caption {
  padding: 30px;
}
.movie_list th,
.movie_list td {
  /* border-spacing: 0; */
  border-bottom: 1px solid #444444;
  padding: 10px;
  text-align: center;
}

.movie_list td.title > a:hover {
  text-decoration: underline;
}

.movie_page {
  margin-top: 30px;
  text-align: center;
  display: flex;
  justify-content: center;
}

.movie_page a {
  display: inline-block;
  width: 32px;
  height: 32px;
  box-sizing: border-box;
  border-left: 0;
  line-height: 32px;
}

.movie_page a:hover {
  text-decoration: underline;
}

.movie_page a.bt {
  /* padding-top: 10px; */
  font-size: 1.2rem;
  letter-spacing: -1px;
}

.movie_page a.num {
  font-size: 1.4rem;
}

.movie_page a.num.on {
  border-color: #000;
  background: #000;
  color: #fff;
}

/* view.html, write.html */

.movie_wrap {
  width: 1000px;
  margin: 0 auto;
}

.movie_title {
  margin: 30px 10px;
}

.movie_title strong {
  font-size: 3rem;
}

.movie_title p {
  margin-top: 5px;
  font-size: 1.4rem;
}

/* view.html */

.movie_view {
  width: 100%;
  border-top: 2px solid #000;
}

.movie_view .title {
  padding: 20px 15px;
  border-bottom: 1px dashed #ddd;
  font-size: 2rem;
}

.movie_view .info {
  padding: 15px;
  border-bottom: 1px solid #999;
  font-size: 0;
}

.movie_view .info dl {
  position: relative;
  display: inline-block;
  padding: 0 20px;
}

.movie_view .info dl:first-child {
  padding-left: 0;
}

.movie_view .info dl::before {
  content: "";
  position: absolute;
  top: 1px;
  left: 0;
  display: block;
  width: 1px;
  height: 13px;
  background: #ddd;
}

.movie_view .info dl:first-child::before {
  display: none;
}

.movie_view .info dl dt,
.movie_view .info dl dd {
  display: inline-block;
  font-size: 1.4rem;
}

.movie_view .info dl dd {
  margin-left: 10px;
  color: #777;
}

.movie_view .cont {
  padding: 15px;
  border-bottom: 1px solid #000;
  line-height: 160%;
  font-size: 1.4rem;
}

.bt_list {
  text-align: right;
}

/* write.html */

.movie_write {
  border-top: 2px solid #000;
}

.movie_write .title,
.movie_write .info {
  padding: 15px;
}

.movie_write .info {
  border-top: 1px dashed #ddd;
  border-bottom: 1px solid #000;
  font-size: 0;
}

.movie_write .title dl {
  font-size: 0;
}

.movie_write .info dl {
  display: inline-block;
  width: 50%;
  vertical-align: middle;
}

.movie_write .title dt,
.movie_write .title dd,
.movie_write .info dt,
.movie_write .info dd {
  display: inline-block;
  vertical-align: middle;
  font-size: 1.4rem;
}

.movie_write .title dt,
.movie_write .info dt {
  width: 100px;
}

.movie_write .title dd {
  width: calc(100% - 100px);
}

.movie_write .title input[type="text"],
.movie_write .info input[type="text"],
.movie_write .info input[type="password"] {
  padding: 10px;
  box-sizing: border-box;
}

.movie_write .title input[type="text"] {
  width: 80%;
}

.movie_write .cont {
  border-bottom: 1px solid #000;
}

.movie_write .cont textarea {
  display: block;
  width: 100%;
  height: 300px;
  padding: 15px;
  box-sizing: border-box;
  border: 0;
  resize: vertical;
  font-size: 1.4rem;
}

/* 768~1000 */
@media all and (max-width: 1000px) {
  .wrap {
    width: 100vw;
  }
  .movie_wrap {
    width: 100vw;
  }
}

/* 0~767 */
@media all and (max-width: 767px) {
  .wrap {
    width: 95vw;
    margin: 0 auto;
  }

  .movie_list th:nth-of-type(1),
  .movie_list td:nth-of-type(1) {
    display: none;
  }
  .movie_list th:nth-of-type(3),
  .movie_list td:nth-of-type(3) {
    display: none;
  }
  .movie_list th:nth-of-type(4),
  .movie_list td:nth-of-type(4) {
    display: none;
  }

  .movie_wrap {
    width: 100vw;
  }

  .movie_write .title dt {
    width: 70px;
  }

  .movie_write .info dt {
    width: 70px;
    margin-bottom: 10px;
  }

  .movie_write .title input[type="text"] {
    width: 100%;
  }

  .movie_write .info input[type="text"],
  .movie_write .info input[type="password"] {
    width: 80%;
  }

  .movie_view .info dl {
    padding: 0 10px;
  }

  .movie_view .info dl dt,
  .movie_view .info dl dd {
    display: inline-block;
    font-size: 1rem;
  }

  .movie_view .info dl::before {
    display: none;
  }

  .movie_view .info dl:nth-of-type(1),
  .movie_view .info dl:nth-of-type(3) {
    display: none;
  }
}



</style>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="movie_wrap">
		<div class="movie_title">
			<strong>자유게시판</strong>
			<p>자유게시판 입니다.</p>
		</div>
		<div class="movie_write_wrap">
			<form name="frm" method="post" action="update?movie_no=${movie.movie_no}" enctype="multipart/form-data">
				<div class="movie_write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" name="title" maxlength="50"
									 value="${movie.title}"/>
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>글쓴이</dt>
							<dd>
								<input type="text" name="user_id" maxlength="10" value="${movie.user_id}"/>
							</dd>
						</dl>
					</div>
					<div class="cont">
						<textarea name="content">${movie.content}</textarea>
					</div>
					<div style="padding-top: 10px">
						<label style="font-size: 1.4rem; padding-right: 20px;" for="file">이미지
							업로드</label> <input type="file" name="file" id="file" />
					</div>
				</div>
			</form>
			<div class="bt_wrap">
				<a onclick="chkForm(); return false;" class="on">수정</a>
				<a href="list">취소</a>
			</div>
		</div>

	</div>
	

	<script type="text/javascript" src="./script.js"></script>
	
</body>
</html>