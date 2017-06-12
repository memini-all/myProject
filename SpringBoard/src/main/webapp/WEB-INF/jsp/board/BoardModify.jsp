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
    
    <script src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    
    <!-- Bootstrap Core CSS -->
    <link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">

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
    
    <script src="/resources/ckeditor/ckeditor.js"></script>
    
    <script>
 
	
	$(document).ready(function() {
		
		CKEDITOR.replace( 'content', { 'filebrowserUploadUrl': 'upload4ckeditor'});
		
		var formObj = $("form[role='form']");
		
		// 수정버튼
		$("#modifyBtn").on("click", function(){
			
			CKEDITOR.instances["content"].updateElement();
			
			if ( ! chkInputValue("#title", "제목")) return false;
			if ( ! chkInputValue("#content", "내용")) return false;
			
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "post");		
			formObj.submit();
		});
		
		// 취소 버튼
		$("#cancelBtn").on("click", function(){

			var url =  "/board/list"
				+ "?page=${cri.page}&perPageNum=${cri.perPageNum}"
				+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		
			self.location = url;
		});
			
	});
	
	
	function chkInputValue(id, msg){

		if ( $.trim($(id).val()) == "") {
			alert(msg+"을(를) 입력해주세요.");
			$(id).focus();
			return false;
		}
		return true;
	}
	
	</script>
    
</head>
<body>

	<div id="wrapper">

		<!-- 네비게이션 부분 -->
		<jsp:include page="../common/navigation.jsp" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            					
            <!-- /.row -->
            <div class="row">
            	<form id="modifyForm"  name="modifyForm" role="form" enctype="multipart/form-data">
            	
            		<!-- 수정후 원래 페이지로 돌아가기 위한 페이지 번호와 페이지당 보여지는 글 목록수 -->
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					
					<!-- 검색조건 -->
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
            	
					<div class="panel panel-default">
	                    <div class="panel-body">
	                    	<div class="row form-group">
	                            <label class="col-lg-1">제목</label>
	                            <div class="col-lg-9">
	                            	<input type="text" class="form-control" id="title" name="title" size="70" maxlength="250" value="<c:out value="${boardVO.title}"/>">
	                            </div>
	                        </div>
	                    	<div class="row form-group">
	                            <label class="col-lg-1">내용</label>
	                            <div class="col-lg-9">
	                            	<textarea id="content" class="form-control" name="content" rows="10" cols="60">
	                            		<c:out value="${boardVO.content}"/>
	                            	</textarea>
	                            </div>
	                        </div>                        
	                    	<div class="row form-group">
	                            <label class="col-lg-1">파일</label>
	                            <div class="col-lg-9">
	                            	<!-- 
	                            	<c:forEach var="listview" items="${listview}" varStatus="status">
										<input type="checkbox" name="fileno" value="<c:out value="${listview.fileno}"/>">	
				            			<a href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>"> 							 
										<c:out value="${listview.filename}"/></a> <c:out value="${listview.size2String()}"/><br/>
									</c:forEach>					
									
									<input type="file" name="uploadfile" multiple="multiple" />
									<input type="file" name="uploadfile" multiple="multiple" />
									
									 -->
									 <input type="file" name="uploadfile" multiple="multiple" />
	                            </div>
	                        </div>  
	                    </div>
	                </div>
			       
					
					<input type="hidden" name="brdno" value="${boardVO.brdno}" />
					<!-- 작성자 : 현재는 hidden으로 임의의 값을 넣음 -->
					<!-- 추후 세션에 저장된 로그인한 아이디를 넘겨주고 controller에서 그걸 토대로 회원번호를 검색하도록 설정 -->
					<input type="hidden" name="userno" value="01" />
				</form>
				
				<button type="submit" id="modifyBtn" class="btn btn-outline btn-primary">수정</button>
				<button id="cancelBtn" class="btn btn-outline btn-primary">취소</button>
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>


</html>
