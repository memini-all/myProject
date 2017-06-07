<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

<!-- 
	<link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    <script src="/resources/sb-admin/vendor/jquery/jquery-ui.js"></script>
    <script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
    <script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
     -->
     
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
     
     
<!--  project9 BoardList.jsp JS파일 링크
    <script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/dynatree/jquery.dynatree.js"></script>    
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
   -->

<script>
	function fn_move(formid, url, msg){
		
		
		
		if (msg) {
			if (!confirm( msg + " 하시겠습니까??")) return;
		}
		var form = document.getElementById(formid);
		form.action=url;
		form.submit();
	}
	
	$(document).ready(function() {
	
		$('#newBtn').on("click", function(event) {
			//alert("글쓰기 클릭");
			self.location = "/board/post";
		});
	});
	
	
</script>

</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<!-- 페이지 가운데 흰부분 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Notifications</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			
			<div class="row">
	        	<div class="col-lg-12">
	                	<!-- 게시판 아이콘 -->
		                <button id="boardlistBtn" type="button" class="btn btn-default" onclick="showBoardList()"><i class="fa  fa-files-o fa-fw"></i> <c:out value="${bgInfo.bgname}"/></button>      
		                <div id="boardlistDiv" style="width: 250px; height:300px; display: none;" class="popover fade bottom in" role="tooltip">
						<div style="left:15%;" class="arrow"></div>
						<div class="popover-content">
							<div id="tree"></div>	
						</div>
					</div>
	                
						<!-- 글쓰기 버튼 -->
					<button type="button" class="btn btn-default pull-right" id="newBtn">
					<i class="fa fa-edit fa-fw"></i> 글쓰기 </button>      
						
	        	</div>
			</div>
				
			<div class="panel panel-default">
	            <div class="panel-body">
					<div class="boardHead">
						<div class="listHiddenField pull-left field60">No.</div>
						<div class="listHiddenField pull-right field60">첨부</div>
						<div class="listHiddenField pull-right field60">조회수</div>
						<div class="listHiddenField pull-right field100">작성일</div>
						<div class="listHiddenField pull-right field100">작성자</div>
						<div class="listTitle">제목</div>
					</div>
						
						
					<c:forEach items="${list}" var="boardVO">	
						
						<div class="listBody">
	
							<!-- 파일첨부 아이콘부분 -->
							<div class="listHiddenField pull-right field60">
								<c:if test="${listitem.filecnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${listitem.filecnt}"/>"></i>
								</c:if>	
							</div>
							<div class="listHiddenField pull-left field60"><c:out value="${boardVO.brdno}"/></div> <!-- 글번호 -->
							<div class="listHiddenField pull-right field60 textCenter"><c:out value="${boardVO.viewcnt}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${boardVO.regdate}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="글쓴이"/></div> <!-- 작성자 -->
							<div class="listTitle">
								<a href="/board/detail?brdno=${boardVO.brdno}" >${boardVO.title}</a>			<!-- 제목 -->					
							</div>
							
							<!-- 
							<div class="listHiddenField pull-right field60 textCenter"><c:out value="${listitem.brdhit}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listitem.brddate}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listitem.brdwriter}"/></div> 
							<div class="listTitle" title="<c:out value="${listitem.brdtitle}"/>">
								<a href="${link}" <c:if test="${listitem.brdnotice=='Y'}">class="notice"</c:if>><c:out value="${listitem.brdtitle}"/></a>						
							</div>
							 -->
							 
							<!-- 화면 축소시 보여질 부분 -->
							<div class="showField text-muted small">
							
								<c:out value="글쓴이"/> 
								<c:out value="${boardVO.regdate}"/>
								<i class="fa fa-eye fa-fw"></i> <c:out value="${boardVO.viewcnt}"/>
								<c:if test="${listitem.filecnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${listitem.filecnt}"/>"></i>
								</c:if>									
							</div>
							
							<!-- 
							<div class="showField text-muted small">
								<c:out value="${listitem.brdwriter}"/> 
								<c:out value="${listitem.brddate}"/>
								<i class="fa fa-eye fa-fw"></i> <c:out value="${listitem.brdhit}"/>
								<c:if test="${listitem.filecnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${listitem.filecnt}"/>"></i>
								</c:if>									
							</div>
							 -->
							 
						</div>
					<!-- listBody -->	
						
					</c:forEach>
	
						 
				
						
	            </div>
	            <!-- panel-body -->
			</div>
			<!-- panel panel-default -->
				
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</body>

</html>