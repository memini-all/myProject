<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

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
  


  	<script type="text/javascript">
 
 	 $(document).ready(function(){
		
 		var formObj = $("form[role='form']");
 		 
 		console.log(formObj);

		// 목록버튼
		$("#listBtn").on("click", function(){
			formObj.attr("action", "/board/list");  // 다시 목록으로 돌아가게 처리
			formObj.attr("method", "get");
			formObj.submit();
		});

		// 수정버튼
		$("#modifyBtn").on("click", function(){
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get");		
			formObj.submit();
		});
			
		// 삭제버튼
		$("#removeBtn").on("click", function() {
			formObj.attr("action", "/board/remove")		
			formObj.submit();
		});
	});
 	 
  	</script>
  
</head>

<body>

    <div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> <c:out value="${bgInfo.bgname}"/></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
				<div class="panel panel-default">
				
				
					<form role="form" method="post">
					
						<input type='hidden' name='brdno' value="${boardVO.brdno}"> 
						<input type='hidden' name='page' value="${cri.page}">
						<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
						
						<!-- 검색조건 -->
						<input type='hidden' name='searchType' value="${cri.searchType}">
						<input type='hidden' name='keyword' value="${cri.keyword}">
					</form>
				
				
					<!-- 제목 -->
                    <div class="panel-heading">
                        	<c:out value="${boardVO.title}"/>
                        <span class="pull-right text-muted">
                        	[ <a href="#">작성자</a> ]  [<c:out value="${boardVO.regdate}"/>]
                        	<i class="fa fa-eye fa-fw"></i> <c:out value="${boardVO.viewcnt}"/>
                        </span>
                    </div>
                    <!-- 내용 -->
                    <div class="panel-body">
                        <p><c:out value="${boardVO.content}" escapeXml="false"/></p>
                    </div>
                    
                    <!-- 첨부파일 있을경우 파일 다운로드 표시 -->
                    <c:if test="${listview.size()>0}">
		                <div class="panel-footer">
		                	<c:forEach var="listview" items="${listview}" varStatus="status">	
		           				<a href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>"> 							 
								<c:out value="${listview.filename}"/></a> <c:out value="${listview.size2String()}"/><br/>
							</c:forEach>
	                     </div>
                    </c:if>
                    
                </div>
                
                <button class="btn btn-outline btn-primary" id="listBtn" >목록</button>
				<c:if test='${boardInfo.userno==sessionScope.userno}' >
					<button class="btn btn-outline btn-primary" id="modifyBtn" >수정</button>
	                <button class="btn btn-outline btn-primary" id="removeBtn" >삭제</button>
				</c:if>

				<!-- 			                               
				<p>&nbsp;</p>
				<input type="hidden" id="brdno" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
				 -->
				 
				<!-- 댓글등록여부 확인 
				<c:if test="${bgInfo.bgreply=='Y'}"> </c:if>
				-->
				<!-- 댓글 입력창 -->
                <div class="panel panel-default">
					<div class="panel-body">
						<div class="col-lg-6">
							<textarea class="form-control" id="replyContent" name="rcontent" maxlength="500" placeholder="댓글을 달아주세요."></textarea>
						</div>
						<div class="col-lg-6">
							<button class="btn btn-outline btn-primary" id="replyAddBtn">저장</button>
						</div>
					</div>
				</div>
					
					<!-- 댓글목록 -->	
					<div id="replieListDiv"> 
					</div>
					
				 	<!-- 페이지 번호 부분 -->
					<div class='text-center'>
						<ul id="pagination" class="pagination pagination-sm no-margin ">

						</ul>
					</div>


				<!-- 댓글 수정창 -->	
				<div id="replyModDiv" style="width: 99%; display:none">
						<input type="hidden" id="brdno2" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
						<input type="hidden" id="modRepeno" name="repeno">
						<div class="col-lg-6">
							<textarea class="form-control" id="modContent" name="rcontent" rows="3" maxlength="500"></textarea>
						</div>
						<div class="col-lg-2 pull-left">
               				<button class="btn btn-outline btn-primary" id="replyModBtn">수정</button>
               				<button class="btn btn-outline btn-primary" onclick="replyModCancel()" id="ModCancelBtn">취소</button>
						</div>
				</div>
				
				<!-- 댓글 답변창 -->
				<div id="replyDialog" style="width: 99%; display:none">
						<input type="hidden" id="reno3" name="reno"> 
						<input type="hidden" id="replyGroup" name="rgroup"> 
						<input type="hidden" id="replyParent" name="rparent">
						<div class="col-lg-6">
							<textarea class="form-control" id="repReplyContent" name="rcontent" rows="3" maxlength="500"></textarea>
						</div>
						<div class="col-lg-2 pull-left">
               				<button class="btn btn-outline btn-primary" id="replyReplyBtn">저장</button> <!--  onclick="replyReplySave()" -->
               				<button class="btn btn-outline btn-primary" onclick="fn_replyReplyCancel()">취소</button>
						</div>
				</div>								
           
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
 


	<!-- 댓글 템플릿 : 화면에서 하나의 댓글을 구성 -->
	<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<div class="panel panel-default replyItm" id="replyItem{{repno}}" style="margin-left: {{depth rparent}}px;">
		<input type="hidden" id="hiddenParent{{repno}}" value="{{rparent}}">
		<input type="hidden" id="hiddenGroup{{repno}}" value="{{rgroup}}">
		<div class="panel-body">             
			<div class="pull-left photoOutline">
				<c:choose>
					<c:when test="${replylist.photo==null}">
						<a href="" class="img-circle">
							<i class="glyphicon glyphicon-user noPhoto"></i>
						</a>
					</c:when>
					<c:otherwise>
					<img class="img-circle" src="fileDownload?downname=<c:out value="${replylist.photo}"/>" title="<c:out value="${replylist.rewriter}"/>"/>
					</c:otherwise>
				</c:choose>
			</div>	
														
			<div class="photoTitle">
				<div> 
					{{userno}} {{regdate}}
					{{#isView this.rdeletat}}
					{{else}}
						<a href="javascript:fn_replyRemove({{repno}})" title="삭제" ><span class="text-muted"><i class="fa fa-times fa-fw"></i></span></span></a>
						<a href="javascript:fn_replyModify({{repno}})" title="수정" ><span class="text-muted"><i class="fa fa-edit fa-fw"></i></span></a>
						<a href="javascript:fn_replyReply({{repno}}, {{userno}})" title="답글" ><span class="text-muted"><i class="fa fa-comments fa-fw"></i></span></a>
					{{/isView}}
				</div>
					<!--<div>[삭제된 댓글의 댓글입니다.]</div>-->
					{{#isDeleteAt}}
						<div id="reply{{repno}}"><font color="red">{{rcontent}}</font></div>
					{{else}}
     				 	<div id="reply{{repno}}">{{breaklines rcontent}}</div>
   					{{/isDeleteAt}}

			</div>
			<br><font color="red">글번호 : {{repno}} / 그룹 : {{rgroup}} / 부모 : {{rparent}}</font>
		</div>
	</div>			
	{{/each}}
	</script> 
 
 
  	<script type="text/javascript">
 
	Handlebars.registerHelper('isView', function(delAt, options) {	   

		// 최상위 댓글이 삭제되어있을경우, 삭제,수정,답글 버튼 생성x
		return delAt == "Y" ? options.fn(this) : options.inverse(this);
	});
	
	Handlebars.registerHelper('isDeleteAt', function(options) {	   

		// this는 replyVO이다. 	
		var parent = this.rparent;
		var delAt = this.rdeletat;

		// 최상위 댓글이 삭제되어있을경우
		if(parent == 0){		
			return delAt == "Y" ? options.fn(this) : options.inverse(this);
		}
		else{
			return options.inverse(this);
		}
	
	});
	
  	
	// registerHelper()를 이용해서 필요한 기능 구현
	// 댓글내용 줄바꿈 처리
	Handlebars.registerHelper('breaklines', function(text) {
	   
		text = Handlebars.Utils.escapeExpression(text);
	   //text = text.replace(/(\r\n|\n|\r)/gm, '<br>');
	
	    text = text.replace(/</gi,"&lt;");
	    text = text.replace(/>/gi,"&gt;");
	    text = text.replace(/\"/gi,"&quot;");
	    text = text.replace(/\'/gi,"&#39;");
	    text = text.replace(/ /gi, "&nbsp;");
	    text = text.replace(/\n/gi,"<br />");
	    
	   // console.log("registerHelper 결과 : " + text);
	    
	    return new Handlebars.SafeString(text);
	});
	
	Handlebars.registerHelper('depth', function(rparent) {	   
		if(rparent > 0)	return 40		
	});
	
  	
  	
 	// 글번호와 초기 보여줄 페이지
	var brdno = ${boardVO.brdno};
	var replyPage = 1;
	
	fn_getReplyList("/replies/"+brdno+"/1");
	
	// 댓글목록과 댓글페이지를 만든다.
	function fn_getReplyList(pageURI) {

		$.getJSON(pageURI, function(data) {
			
			fn_printTemplate(data.replyList, $("#replieListDiv"), $('#template'));
			fn_printPage(data.pageCalculate, $(".pagination"));
			
		});
	}
	// end getReplyList()

	
	// 배열형식 댓글데이터, 타겟(div), 템플릿 스크립트를 인자로 받아 템플릿 생성
 	var fn_printTemplate = function(replyArr, target, templateObject){

 		var template = Handlebars.compile(templateObject.html());
 		var html = template(replyArr);
 		$(".replyItm").remove();
 		target.after(html);
 	}

	
	// 댓글 페이지 생성
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
	// end printPage();
  	
	// 댓글 페이징 처리 이벤트 - 페이지 번호 클릭시 해당 페이지로 이동
	$(".pagination").on("click", "li a", function(event){

		event.preventDefault(); // 일단 페이지 이동을 멈춤 - <a href> 태그 정지
	
		replyPage = $(this).attr("href");  // li 태그 아래의 a 태그에서 href 속성 값을 가져온다.
		fn_getReplyList("/replies/"+brdno+"/"+replyPage);
	});
	
	// 댓글 등록
	$("#replyAddBtn").on("click", function() {
	
		//var replyerObj = $("#newReplyWriter"); // 작성자
		var replyer = "1" //replyerObj.val();
	 	var replyContentObj = $("#replyContent");
		var rcontent = replyContentObj.val();

		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				brdno : brdno,
				userno : replyer,
				rcontent : rcontent,
				rgroup : 0,
				rparent : 0
			}),
			success : function(result) {
			
				if (result == 'SUCCESS') {
					
					replyPage = 1;
					
					console.log("댓글 등록 결과 : " + result);
					console.log("/replies/" + brdno + "/" + replyPage);

					fn_getReplyList("/replies/" + brdno + "/" + replyPage);
					//replyerObj.val("");
					replyContentObj.val("");
				}
			}
		});
	});


	// 댓글 수정div 부분
	var updateRepeno = updateRcontent = null;
	
	function fn_replyModify(repeno){
		
		hideDiv("#replyDialog");

		if (updateRepeno) {
			// 다른 댓글 수정버튼 클릭시, 전에 수정버튼 클릭했던 댓글 내용을 가져와 세팅한다.
			//$("#replyModDiv").appendTo(document.body);
			
			hideDiv("#replyModDiv");

			$("#reply"+updateRepeno).html(updateRcontent);
			updateRepeno = updateRcontent = null;

			//$(document.body).append($("#replyModDiv"));
			//$("#reply"+updateRepeno).text( updateRcontent );
		} 
		
		updateRepeno = repeno;
		updateRcontent = $("#reply"+repeno).html();  // 댓글 내용을 가져온다.

		
		$("#modRepeno").val(repeno);  // 수정할 댓글번호
		$("#modContent").val( html2Text(updateRcontent) ); // 수정할 댓글내용
		$("#reply"+repeno).text("");	// 수정내용이 들어가있는 div 에 "" 붙임
		//$("#replyModDiv").appendTo($("#reply"+repeno));
		$("#reply"+repeno).append($("#replyModDiv"));
		$("#replyModDiv").show();
		$("#modContent").focus(); // 수정할 텍스트에 포커스
	
	} 
	
	// 댓글 수정 취소 
	/* 
	$("#ModCancelBtn").on("click", function() {
		hideDiv("#replyModDiv");
		
		$("#reply"+updateRepeno).html(updateRcontent);
		updateRepeno = updateRcontent = null;
	});
	*/
	
	// 댓글 수정 취소
	function replyModCancel(){
		hideDiv("#replyModDiv");
		
		$("#reply"+updateRepeno).html(updateRcontent);
		updateRepeno = updateRcontent = null;
	}
	
	
	// 댓글 수정
	$("#replyModBtn").on("click", function() {

		var repeno = $("#modRepeno").val();
		var rcontent = $("#modContent").val();

		$.ajax({
			type : 'put',
			url : '/replies/' + repeno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				rcontent : rcontent
			}),
			dataType : 'text',
			success : function(result) {
				console.log("댓글 수정 결과 : " + result);
				if (result == 'SUCCESS') {
					//$("#replyModDiv").appendTo(document.body);
					$(document.body).append($("#replyModDiv"));
					$("#replyModDiv").hide();
					$("#reply"+updateRepeno).html( text2Html($("#modContent").val()) );

					updateRepeno = updateRcontent = null;
					
					fn_getReplyList("/replies/" + brdno + "/" + replyPage);
				}
			}
		});	
	});
	
	
	// 댓글 삭제
	function replyRemove(repno){

		var parent = $("#hiddenParent"+repno).val();
		
		$.ajax({
			type : 'delete',
			url : '/replies/' + repno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("댓글 삭제 결과 : " + result);
				if (result == 'SUCCESS') {
			
					
					fn_getReplyList("/replies/" + brdno + "/" + replyPage);
				}
			}
		});	
		
	}

	
	// 댓글 답글창
	function fn_replyReply(repno, userno){
		
		$("#replyDialog").show();
		
		if (updateRepeno) {
			replyModCancel();
		} 

		// 부모글의 repno
		//alert("부모글 번호 : "+repno + " / 부모글 작성자 : "+userno);
		
		var groupNumber = $("#hiddenGroup"+repno).val();
		
		$("#replyParent").val(repno);
		$("#repReplyContent").val("");
		//$("#replyDialog").appendTo($("#reply"+repno));
		$("#replyGroup").val(groupNumber);
		$("#reply"+repno).append($("#replyDialog"));
		$("#repReplyContent").focus();
	} 
	
	// 댓글 답변 취소
	function fn_replyReplyCancel(){
		hideDiv("#replyDialog");
	} 
	
	
	// 댓글 답변 등록
	$("#replyReplyBtn").on("click", function() {
	
		//var replyerObj = $("#newReplyWriter"); // 작성자
		var replyer = "2" //replyerObj.val();  현재 답글
		var replyContent = $("#repReplyContent").val();
		var rparent = $("#replyParent").val(); 
		var rgroup = $("#replyGroup").val();
	
		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				brdno : brdno,
				userno : replyer,
				rcontent : replyContent,
				rgroup : rgroup,
				rparent : rparent
			}),
			success : function(result) {
			
				if (result == 'SUCCESS') {
					
					replyPage = 1;
					
					console.log("댓글 답변 등록 결과 : " + result);
					console.log("/replies/" + brdno + "/" + replyPage);

					fn_getReplyList("/replies/" + brdno + "/" + replyPage);
					//$("#repReplyContent").val("");
					fn_replyReplyCancel();
				}
			}
		});
	});
	
	
	function hideDiv(id){
		$(id).hide();
		$(id).appendTo(document.body);
	}
	
	function html2Text(text) {

	   	text = text.replace(/&lt;/gi,"<");
		text = text.replace(/&gt;/gi,">");
		text = text.replace(/&quot;/gi,"\"");
		text = text.replace(/&#39;/gi,"\'");
		text = text.replace(/&nbsp;/gi, " ");
		text = text.replace(/<br>/gi,"\n");
		
		return text;	
	}

	function text2Html(text) {

		text = text.replace(/</gi,"&lt;");
		text = text.replace(/>/gi,"&gt;");
		text = text.replace(/\"/gi,"&quot;");
		text = text.replace(/\'/gi,"&#39;");
		text = text.replace(/ /gi, "&nbsp;");
		text = text.replace(/\n/gi,"<br />");	
	    
	    return text; 
	} 

 	</script>
    
</body>

</html>
