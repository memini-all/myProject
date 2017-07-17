<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax Test Page</title>

<style type="text/css">
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

.pagination {
	width: 100%;
}

.pagination li {
	list-style: none;
	float: left;
	padding: 3px;
	border: 1px solid blue;
	margin: 3px;
}

.pagination li a {
	margin: 3px;
	text-decoration: none;
}
</style>

<script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>

</head>

<body>

	<h2>Ajax Test Page</h2>

	<!-- 댓글 작성 부분 -->
	<div>
		<div>
			작성자 : <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			댓글 : <input type='text' name='replytext' id='newReplyText'>
		</div>
		<br>
		<button id="replyAddBtn">댓글 등록</button>
	</div>
	<!-- end 댓글 작성 -->

	<!-- 댓글 수정,삭제 -->
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<br>
			<button type="button" id="replyModBtn">수정</button>
			<button type="button" id="replyDelBtn">삭제</button>
			<button type="button" id='closeBtn'>닫기</button>
		</div>
	</div>
	<!-- end 댓글 수정,삭제 -->

	<!-- 댓글이 보여지는 곳 -->
	<ul id="replies">
	</ul>

	<!-- 페이지 처리 -->
	<ul class='pagination'>
	</ul>


	<!-- jQuery 2.1.4 -->
	<script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>

	<script>
		var brdno = 65;
		// 댓글 1페이지	
		//getPageList(1);

		// 특정 게시물의 댓글 전체 조회하는 함수
		//function getAllList(){
		$.getJSON("/replies/all/" + brdno, function(data) { // data : JSON 데이터 , 댓글목록(list) 및 PageMaker 객체

			var str = "";
			console.log("getAllList() 댓글 개수 : " + data.length);

			$(data).each(

					function() {
						str += "<li data-rno='"+this.repno+"' class='replyLi'>"
								+ this.repno + " : " + this.rcontent
								+ "<button>MOD</button></li>";
					});

			$("#replies").html(str);

		});
		//}
		// end getAllList()
	</script>

	<script type="text/javascript">

	</script>


</body>
</html>

