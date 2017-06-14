<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
  
  	<!-- 댓글 템플릿 : 화면에서 하나의 댓글을 구성 -->
	<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<div class="panel panel-default" id="replyItem<c:out value="${replylist.reno}"/>" style="margin-left: <c:out value="${20*replylist.redepth}"/>px;">
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
					<c:if test='${replylist.userno==sessionScope.userno}' >
						<a href="javascript:fn_replyDelete('<c:out value="${replylist.reno}"/>')" title="삭제" ><span class="text-muted"><i class="fa fa-times fa-fw"></i></span></span></a>
						<a href="javascript:fn_replyUpdate('<c:out value="${replylist.reno}"/>')" title="수정" ><span class="text-muted"><i class="fa fa-edit fa-fw"></i></span></a>
					</c:if>
						<a href="javascript:fn_replyReply('<c:out value="${replylist.reno}"/>')" title="댓글" ><span class="text-muted"><i class="fa fa-comments fa-fw"></i></span></a>
				</div>
					<div id="reply<c:out value="${replylist.reno}"/>">{{rcontent}}</div>
			</div>	
		</div>
	</div>			
	{{/each}}
	</script>

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
 	 
 	 
 	// 글번호와 초기 보여줄 페이지
	var brdno = ${boardVO.brdno};
	var replyPage = 1;
	
	getReplyList("/replies/"+brdno+"/1");
	
	// 댓글목록과 댓글페이지를 만든다.
	function getReplyList(pageURI) {

		$.getJSON(pageURI, function(data) {
			
			printTemplate(data.replyList, $("#repliesDiv"), $('#template'));
			printPage(data.pageCalculate, $(".pagination"));
		});
	}
	// end getReplyList()
	
	
	// 배열형식 댓글데이터, 타겟(div), 템플릿 스크립트를 인자로 받아 템플릿 생성
 	var printTemplate = function(replyArr, target, templateObject){
 		
 		var template = Handlebars.compile(templateObject.html());
 		var html = template(replyArr);
 		target.after(html);
 	}
	
	// 댓글 페이지 생성
	var printPage = function(pageCalculate, target) {

		var str = "";

		if (pageCalculate.prev) {
			str += "<li><a href='" 
				+ (pageCalculate.startPage - 1)
				+ "'> << </a></li>";
		}

		for (var i = pageCalculate.startPage, len = pageCalculate.endPage; i <= len; i++) {
			
			var strClass = pageCalculate.cri.page == i ? 'class=active' : '';
			
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
                        	[ <a href="#">작성자</a> ]  [<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" />]
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

							                               
				<p>&nbsp;</p>
				<input type="hidden" id="brdno" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
				
				<!-- 댓글등록여부 확인 
				<c:if test="${bgInfo.bgreply=='Y'}"> </c:if>
				-->
				<!-- 댓글 입력창 -->
                <div class="panel panel-default">
					<div class="panel-body">
						<div class="col-lg-6">
							<textarea class="form-control" id="rememo1" name="rememo" maxlength="500" placeholder="댓글을 달아주세요."></textarea>
						</div>
						<div class="col-lg-6">
							<button class="btn btn-outline btn-primary" onclick="fn_formSubmit()">저장</button>
						</div>
					</div>
				</div>
					
					<!-- 댓글목록 -->	
				
					<div id="repliesDiv"> 
					
					<!-- 테스트
							<ul class="timeline">
								<li class="time-label" id="repliesDiv">
									<span class="bg-green">Replies List</span>
								</li>
							</ul>
					 -->
					 
						<!--		
						<c:forEach var="replylist" items="${replylist}" varStatus="status">
							<div class="panel panel-default" id="replyItem<c:out value="${replylist.reno}"/>" style="margin-left: <c:out value="${20*replylist.redepth}"/>px;">
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
											<c:out value="${replylist.rewriter}"/> <c:out value="${replylist.redate}"/>
											<c:if test='${replylist.userno==sessionScope.userno}' >
												<a href="javascript:fn_replyDelete('<c:out value="${replylist.reno}"/>')" title="삭제" ><span class="text-muted"><i class="fa fa-times fa-fw"></i></span></span></a>
												<a href="javascript:fn_replyUpdate('<c:out value="${replylist.reno}"/>')" title="수정" ><span class="text-muted"><i class="fa fa-edit fa-fw"></i></span></a>
											</c:if>
											<a href="javascript:fn_replyReply('<c:out value="${replylist.reno}"/>')" title="댓글" ><span class="text-muted"><i class="fa fa-comments fa-fw"></i></span></a>
										</div>
										<div id="reply<c:out value="${replylist.reno}"/>"><c:out value="${replylist.getRememoByHTML()}" escapeXml="false"/></div>
									</div>
									
									
								</div>
				            </div>						
						</c:forEach>
						 -->	 
					</div>
					
				 	<!-- 페이지 번호 부분 -->
					<div class='text-center'>
						<ul id="pagination" class="pagination pagination-sm no-margin ">

						</ul>
					</div>


				<!-- 댓글 답변 -->	
				<div id="replyDiv" style="width: 99%; display:none">
						<input type="hidden" id="brdno2" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
						<input type="hidden" id="reno2" name="reno">
						<div class="col-lg-6">
							<textarea class="form-control" id="rememo2" name="rememo2" rows="3" maxlength="500"></textarea>
						</div>
						<div class="col-lg-2 pull-left">
               				<button class="btn btn-outline btn-primary" onclick="fn_replyUpdateSave()">저장</button>
               				<button class="btn btn-outline btn-primary" onclick="fn_replyUpdateCancel()">취소</button>
						</div>
				</div>
				
				<!-- 댓글 수정 -->
				<div id="replyDialog" style="width: 99%; display:none">
						<input type="hidden" id="brdno3" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
						<input type="hidden" id="reno3" name="reno"> 
						<input type="hidden" id="reparent3" name="reparent">
						<div class="col-lg-6">
							<textarea class="form-control" id="rememo3" name="rememo3" rows="3" maxlength="500"></textarea>
						</div>
						<div class="col-lg-2 pull-left">
               				<button class="btn btn-outline btn-primary" onclick="fn_replyReplySave()">저장</button>
               				<button class="btn btn-outline btn-primary" onclick="fn_replyReplyCancel()">취소</button>
						</div>
				</div>								
           
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
 
    
</body>

</html>
