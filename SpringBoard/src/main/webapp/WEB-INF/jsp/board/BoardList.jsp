<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    <script src="/resources/sb-admin/vendor/jquery/jquery-ui.js"></script>
    <script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
    <script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
    
  <!--  project9 BoardList.jsp JS파일 링크
    <script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/dynatree/jquery.dynatree.js"></script>    
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
   -->  
   
</head>
<body>
	<h2>게시판 목록목록</h2>	

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
                <div class="col-lg-12">
	                <button id="boardlistBtn" type="button" class="btn btn-default" onclick="showBoardList()"><i class="fa  fa-files-o fa-fw"></i> <c:out value="${bgInfo.bgname}"/></button>      
	                <div id="boardlistDiv" style="width: 250px; height:300px; display: none;" class="popover fade bottom in" role="tooltip">
	                	<div style="left:15%;" class="arrow"></div>
	                	<div class="popover-content">
             				<div id="tree"></div>	
	                	</div>
	                </div>
                
					<c:if test="${bgInfo.bgreadonly=='N' || sessionScope.userrole=='A'}">
			            <button type="button" class="btn btn-default pull-right" onclick="fn_moveToURL('boardForm?bgno=<c:out value="${searchVO.bgno}"/>')">
			            <i class="fa fa-edit fa-fw"></i> </button>      
					</c:if>
				</div>
            </div>
            <!-- /.row -->
            <div class="panel panel-default">
            	<div class="panel-body">
					<div class="listHead">
						<div class="listHiddenField pull-left field60"></div>
						<div class="listHiddenField pull-right field60"></div>
						<div class="listHiddenField pull-right field60"></div>
						<div class="listHiddenField pull-right field100"></div>
						<div class="listHiddenField pull-right field100"></div>
						<div class="listTitle"></div>
					</div>
					
					<c:forEach var="listview" items="${noticelist}" varStatus="status">
						<c:set var="listitem" value="${listview}" scope="request" />	
						<c:set var="listitemNo" value="" />	
						<!-- 
						<jsp:include page="BoardListSub.jsp" >
							<jsp:param name="listitemNo" value="${listitemNo}" />
							<jsp:param name="listitem" value="${listitem}" />
						</jsp:include>
						 -->
					</c:forEach>					
					<c:if test="${listview.size()==0}">
						<div class="listBody height200">
						</div>
					</c:if>
					<c:forEach var="listview" items="${listview}" varStatus="status">
						<c:set var="listitem" value="${listview}" scope="request" />	
						<c:set var="listitemNo" value="${searchVO.totRow-((searchVO.page-1)*searchVO.displayRowCount + status.index)}" scope="request" />	
					
					<!-- 
						<jsp:include page="BoardListSub.jsp" >
							<jsp:param name="listitemNo" value="${listitemNo}" />
							<jsp:param name="listitem" value="${listitem}" />
						</jsp:include>
					 -->		
					</c:forEach>	
					
					<br/>
					<form role="form" id="form1" name="form1"  method="post">
					    
					    <!-- 
					    <jsp:include page="../common/pagingforSubmit.jsp" />
				     -->
				     
						<div class="form-group">
							<div class="checkbox col-lg-3 pull-left">
							 	<label class="pull-right">
		                        	<input type="checkbox" name="searchType" value="brdmemo" />
		                      
		                        </label>
							 	<label class="pull-right">
		                        	<input type="checkbox" name="searchType" value="brdtitle" />
		                        	
		                        </label>
							 	<label class="pull-right">
							 		<input type="checkbox" name="searchType" value="usernm" />
		                        	
		                        </label>
		                   </div>
		                   <div class="input-group custom-search-form col-lg-3">
	                                <input class="form-control" placeholder="Search..." type="text" name="searchKeyword" 
	                                	   value='<c:out value="${searchVO.searchKeyword}"/>' >
	                                <span class="input-group-btn">
	                                <button class="btn btn-default" onclick="fn_formSubmit()">
	                                    <i class="fa fa-search"></i>
	                                </button>
	                            </span>
	                       </div>
						</div>
					</form>	
            	</div>    
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>

</html>