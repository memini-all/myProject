<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap Core CSS -->
    <link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">
	<link href="/resources/css/sb-admin-add.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery -->
    <script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
	
	<!-- Conmmon JavaScript -->
    <script src="/resources/js/common.js"></script>
	
	<!-- handlebars JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!--  
    <title></title>
    <link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="css/sb-admin/font-awesome.min.css" rel="stylesheet" type="text/css">

    

    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/project9.js"></script>    
  --> 


</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<!-- 페이지 가운데 흰부분 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">나의 활동</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="panel panel-default">
				<div class="panel-body">
				
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#myArticle" 
							onclick="fn_getArticleList('/board/user/'+${userVO.userno}+'/1');">등록한 게시글</a>
						</li>
					 	<li>
					 		<a data-toggle="tab" href="#myReply" 
					 			onclick="fn_getReplyList('/replies/user/'+${userVO.userno}+'/1');">등록한 댓글</a>
					 	</li>
					</ul>
					
					<div class="tab-content">
						<!-- tab1 등록한 게시글 -->
						<div id="myArticle" class="tab-pane fade in active">
							<br>
							<div class="pull-left field100">
								<input type="checkbox" id="allArticleCheck" /> 전체선택
							</div>
						
							<button type="button" class="btn btn-outline btn-default pull-right" id="artiDelBtn">
								<i class="fa fa-trash-o fa-fw"></i> 삭제
							</button> 
						
							<h4>&nbsp;&nbsp;</h4>
						
							<div class="boardHead">
								<div class="listHiddenField pull-left field60">선택</div>
								<div class="listHiddenField pull-left field100">No.</div>
								<div class="listHiddenField pull-right field60">첨부</div>
								<div class="listHiddenField pull-right field60">조회수</div>
								<div class="listHiddenField pull-right field100">작성일</div>
								<div class="listTitle">제목</div>
							</div>
							
							<!-- 작성글 목록 -->
							<div id="articleListDiv">	
							</div>

							<!-- 페이지 번호 부분 -->
							<div class='text-center'>
								<ul id="articlePage" class="pagination pagination-sm no-margin "></ul>
							</div>
						</div>
						<!-- /tab1 등록한 게시글 -->	
						
						<!-- tab2 등록한 댓글 -->	
						<div id="myReply" class="tab-pane fade">
					    	<br>
					    	<div class="pull-left field100">
								<input type="checkbox" id="allReplyCheck" /> 전체선택
							</div>
						
							<button type="button" class="btn btn-outline btn-default pull-right" id="repDelBtn">
								<i class="fa fa-trash-o fa-fw"></i> 삭제
							</button> 
						
							<h4>&nbsp;&nbsp;</h4>
					    	
					    	<div class="boardHead">
								<div class="listHiddenField pull-left field60">선택</div>
								<div class="listHiddenField pull-left field100">No.</div>
								<div class="listHiddenField pull-right field100">작성일</div>
								<div class="listHiddenField pull-right field130">&nbsp;&nbsp;</div>
								<div class="listTitle">댓글 내용</div>
							</div>
							
							<!-- 작성 댓글 목록 -->
							<div id="replyListDiv">	
							</div>

							<!-- 페이지 번호 부분 -->
							<div class='text-center'>
								<ul id="replyPage" class="pagination pagination-sm no-margin"></ul>
							</div>
					    	
					    	
						</div>
						<!-- /tab2 등록한 댓글 -->	
							
					</div>		
					<!-- /.tab-content -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel panel-default -->

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	
	<!-- 내가쓴글 템플릿 -->
	<script id="articleTemplate" type="text/x-handlebars-template">
	{{#each .}}
	<div class="articleItm listBody">
		<div class="listHiddenField pull-right field60">
			{{#isFile fileCnt}}
				<i class="fa fa-download fa-fw" title="{{fileCnt}}개 파일"></i>
			{{/isFile}}	
		</div>
								
		<div class="listHiddenField pull-left field60">
			<input type="checkbox" name="article_CheckBox" value="{{brdno}}" >
		</div> 
								
		<div class="listHiddenField pull-left field100">{{brdno}}</div> 
		<div class="listHiddenField pull-right field60 textCenter">{{viewcnt}}</div>
		<div class="listHiddenField pull-right field100 textCenter">{{regdate}}</div>
							
		<div class="listTitle">
			{{#isNotice notice}}
				<img src="/resources/image/notice-icon3.png">
				<a href="/board/detail?brdno={{brdno}}" class="notice">{{title}}</a>	
			{{else}}
				<a href="/board/detail?brdno={{brdno}}" >{{title}}</a>	
			{{/isNotice}}	

			{{#isReplyCnt replycnt}}	
				({{replycnt}})
			{{/isReplyCnt}}	
		</div>
														 
		<!-- 화면 축소시 보여질 부분 -->
		<div class="showField text-muted small">
			{{regdate}}
			<i class="fa fa-eye fa-fw"></i>{{viewcnt}}								
		</div>
	</div> 
	{{/each}}
	</script> 
	
	
	<!-- 댓글 템플릿 -->
	<script id="replyTemplate" type="text/x-handlebars-template">
	{{#each .}}
	<div class="replyItm listBody">
								
		<div class="listHiddenField pull-left field60">
			<input type="checkbox" name="reply_CheckBox" value="{{repno}}">
		</div> 
								
		<div class="listHiddenField pull-left field100">{{repno}}</div> 
		<div class="listHiddenField pull-right field100 textCenter">{{regdate}}</div>	
		<div class="listHiddenField pull-right field130 textCenter">
			<span class="btn btn-outline btn-default btn-xs" onclick="fn_moveArticle({{brdno}})">원문보기</span>
		</div>		
		<div class="listTitle">{{rcontent}}</div>
														 
		<!-- 화면 축소시 보여질 부분 -->
		<div class="showField text-muted small">
			{{regdate}}						
		</div>
	</div> 
	{{/each}}
	</script> 

	
	<script type="text/javascript">

	
		/************** articleTemplate registerHelper **************/
	
		// 첨부 파일이 있는지 체크
		Handlebars.registerHelper('isFile', function(fileCnt, options) {	   
			return fileCnt > 0 ? options.fn(this) : options.inverse(this);
		});
		
		// 댓글이 있는지 체크
		Handlebars.registerHelper('isReplyCnt', function(replycnt, options) {	   
			return replycnt > 0 ? options.fn(this) : options.inverse(this);
		});
		
		
		// 공지사항 체크
		Handlebars.registerHelper('isNotice', function(notice, options) {	   
			return notice == "Y" ? options.fn(this) : options.inverse(this);
		});
	
		/**********************************************************/

		
		var userno = ${userVO.userno};	// 회원번호
		var articlePage = 1;	// 게시글 시작페이지
		var replyPage = 1;		// 댓글 시작 페이지
		

		fn_getArticleList("/board/user/"+userno+"/1");

		
		/*
		 *	작성한 게시글 목록을 가져온다.
		 */
		function fn_getArticleList(pageURI) {
			
			$("#allArticleCheck").prop("checked",false); 
			//$(".replyItm").remove();
			
			$.getJSON(pageURI, function(data) {		
				
				console.log(data);
				console.log(data.articleList.length);
				
				fn_printTemplate(data.articleList, $("#articleListDiv"), $('#articleTemplate'), $(".articleItm"));
				fn_printPage(data.pageCalculate, $("#articlePage"));
			});
		}


		/*
		 *	작성한 댓글 목록을 가져온다.
		 */
		function fn_getReplyList(pageURI) {

			$("#allReplyCheck").prop("checked",false); 
			//$(".articleItm").remove();
			
			$.getJSON(pageURI, function(data) {		
				
				console.log(data);
				
				fn_printTemplate(data.replyList, $("#replyListDiv"), $('#replyTemplate'), $(".replyItm"));
				fn_printPage(data.pageCalculate, $("#replyPage"));
			});
		}
		

		/*
		 *	배열형식 데이터, 타겟(div), 템플릿 스크립트, 
		 *	삭제할 Element를 인자로 받아 템플릿 생성 
		 */
	 	var fn_printTemplate = function(dataArr, target, templateObject, delElmt){
	 		
	 		var template = Handlebars.compile(templateObject.html());
	 		var html = template(dataArr);
	 		delElmt.remove();
	 		target.after(html);
	 	}
		

		/*
		 *	페이지 생성
		 */
		var fn_printPage = function(pageCalculate, target) {

			var str = "";

			if (pageCalculate.prev) {
				str += "<li><a href='" 
					+ (pageCalculate.startPage - 1)
					+ "'> << </a></li>";
			}

			for (var i = pageCalculate.startPage, len = pageCalculate.endPage; i <= len; i++) {
				
				var strClass = pageCalculate.cri.page == i ? 'class = active' : '';
				
				str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
			}

			if (pageCalculate.next) {
				str += "<li><a href='" 
					+ (pageCalculate.endPage + 1)
					+ "'> >> </a></li>";
			}

			target.html(str);
		};
		
		
		/*
		 *	페이징 처리 이벤트 - 페이지 번호 클릭시 해당 페이지로 이동
		 *	게시글 페이지 처리
		 */
		$("#articlePage").on("click", "li a", function(event){
	
			event.preventDefault(); // 일단 페이지 이동을 멈춤 - <a href> 태그 정지
		
			articlePage = $(this).attr("href");  // li 태그 아래의 a 태그에서 href 속성 값을 가져온다.
			fn_getArticleList("/board/user/"+userno+"/"+articlePage);
		});
		
		
		/*
		 *	댓글 페이지 처리
		 */
		$("#replyPage").on("click", "li a", function(event){
			
			event.preventDefault(); // 일단 페이지 이동을 멈춤 - <a href> 태그 정지
		
			replyPage = $(this).attr("href");  // li 태그 아래의 a 태그에서 href 속성 값을 가져온다.
			fn_getReplyList("/replies/user/"+userno+"/"+replyPage);
		});
		
		
		/*
		 *	내가 쓴 댓글에서 원문보기 클릭 시 해당 글로 이동한다.
		 */
		function fn_moveArticle(brdno){
			self.location = "/board/detail?brdno="+brdno;	
		}
		
		
		/*
		 *	게시글 전체선책 - 체크 시 모든 글의 체크박스가 체크된다.
		 */
		$("#allArticleCheck").click(function(){
			//만약 전체 선택 체크박스가 체크된상태일경우
			if($("#allArticleCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다 
				$("input[name=article_CheckBox]").prop("checked",true); 
				
			// 전체선택 체크박스가 해제된 경우 
			} else { 
				//해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[name=article_CheckBox]").prop("checked",false); 
			}
		});

		
		/*
		 *	댓글 전체선책 - 체크 시 모든 댓글의 체크박스가 체크된다.
		 */
		$("#allReplyCheck").click(function(){
			
			if($("#allReplyCheck").prop("checked")){
				$("input[name=reply_CheckBox]").prop("checked",true); 
			} else { 
				$("input[name=reply_CheckBox]").prop("checked",false); 
			}
		});
		
		
		/*
		 * 게시글 삭제버튼 클릭
		 */
		$("#artiDelBtn").click(function(){
			
			var checkboxValues = [];
			
			// 체크된 체크박스 값을 가져온다
			$("input[name=article_CheckBox]:checked").each(function() {
				 
				checkboxValues.push($(this).val());
			});
			
			console.log("체크박스 checkboxValues : "+checkboxValues);
			
			// 데이터 전송
			$.ajax({
				url : "/board/user/delete",
				data : {	delBrdnoArr : checkboxValues },
				success : function(result){
					if (result == 'SUCCESS'){
						alert("선택한 게시물이 정상적으로 삭제되었습니다.");
						
						fn_getArticleList("/board/user/"+userno+"/1");
					}
				},
				error : function(request,status,error){
					fn_errorPage(request.status);
				}    
			});
			
		});
		
		
		
		/*
		 * 댓글 삭제버튼 클릭
		 */
		$("#repDelBtn").click(function(){
			
			var checkboxValues = [];
			
			// 체크된 체크박스 값을 가져온다
			$("input[name=reply_CheckBox]:checked").each(function() {
				 
				checkboxValues.push($(this).val());
			});
			
			console.log("댓글 체크박스 checkboxValues : "+checkboxValues);
			
			// 데이터 전송
			$.ajax({
				url : "/replies/user/delete",
				type : 'post',
				data : { delRepnoArr : checkboxValues },
				success : function(result){
					if (result == 'SUCCESS'){
						alert("선택한 댓글이 정상적으로 삭제되었습니다.");
						
						fn_getReplyList('/replies/user/'+userno+'/1');
					}
				},
				error : function(request,status,error){
					fn_errorPage(request.status);
				}    
			});
		});
		
		
	</script>
	
</body>

</html>