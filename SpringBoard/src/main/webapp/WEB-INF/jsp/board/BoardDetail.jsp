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
                    <c:if test="${listview.size()>0}">
		                <div class="panel-footer">
		                	<c:forEach var="listview" items="${listview}" varStatus="status">	
		           				<a href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>"> 							 
								<c:out value="${listview.filename}"/></a> <c:out value="${listview.size2String()}"/><br/>
							</c:forEach>
	                     </div>
                    </c:if>
                </div>
                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('board/list?bgno=<c:out value="${bgno}"/>')" >목록</button>
				<c:if test='${boardInfo.userno==sessionScope.userno}' >
	                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('boardDelete?bgno=<c:out value="${boardInfo.bgno}"/>&brdno=<c:out value="${boardInfo.brdno}"/>', '삭제')" >삭제</button>
	                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('boardForm?brdno=<c:out value="${boardInfo.brdno}"/>')" >수정</button>
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
				<!-- 	
					<div id="replyList"> 
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
					</div>
				

				-->	
				
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
