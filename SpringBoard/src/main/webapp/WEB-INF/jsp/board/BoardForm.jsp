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
 
	
	$(document).ready(
		function() {
			CKEDITOR.replace( 'content', { 'filebrowserUploadUrl': 'upload4ckeditor'});
	});
	
	// 전송 부분 - 제이쿼리 이용 x
	/**/
	function fn_formSubmit(){
		CKEDITOR.instances["content"].updateElement();
		
		// 내용 입력 체크
		if ( ! chkInputValue("#title", "제목")) return false;
		if ( ! chkInputValue("#content", "내용")) return false;
	
		$("#registerForm").submit();
	} 
	
	
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
		<jsp:include page="../common/navigation2.jsp" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            						<!-- enctype="multipart/form-data"을 form 태그에 적용시 자동적으로 VO와 바인딩이 안됨, 
            							pom.xml과 설정파일(main-servlet.xml)에 파일업로드 관련 부분을 모두 추가해야함 -->
            <!-- /.row -->
            <div class="row">
            	<form id="registerForm"  name="registerForm" role="form" action="regist" method="post" enctype="multipart/form-data" onsubmit="return fn_formSubmit();">
					<div class="panel panel-default">
	                    <div class="panel-body">
	                    	<div class="row form-group">
	                            <label class="col-lg-1">제목</label>
	                            <div class="col-lg-9">
	                            	<input type="text" class="form-control" id="title" name="title" size="70" maxlength="250" placeholder="Enter Title">
	                            	
	                            	<!-- 공지사항 
	                            	<c:if test="${bgInfo.bgnotice=='Y'}">
									 	<label>
				                        	<input type="checkbox" name="brdnotice" value="Y" <c:if test="${boardInfo.brdnotice=='Y'}">checked="checked"</c:if>/>
				                        	<s:message code="common.notice"/>
				                        </label>
	                            	</c:if>
	                            	-->
	                            </div>
	                        </div>
	                    	<div class="row form-group">
	                            <label class="col-lg-1">내용</label>
	                            <div class="col-lg-9">
	                            	<textarea id="content" class="form-control" name="content" rows="10" cols="60"></textarea>
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
			        <button type="submit" class="btn btn-outline btn-primary">작성</button>
					
					<!-- 작성자 : 현재는 hidden으로 임의의 값을 넣음 -->
					<!-- 추후 세션에 저장된 로그인한 아이디를 넘겨주고 controller에서 그걸 토대로 회원번호를 검색하도록 설정 -->
					<input type="hidden" name="userno" value="01" />
				</form>
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>


<script>
/*
//글 등록시 파일의 이름을 같이 전송해 준다.
$("#registerForm").submit(function(event) {
	
	//event.preventDefault(); // submit의 동작을 일시적으로 막는다.
	// 이게 있으면 에디터 기능x

	var that = $(this);

	CKEDITOR.instances["content"].updateElement();
	
	//that.action = 
	
	// 내용 입력 체크
	if ( ! chkInputValue("#title", "제목")) return false;
	if ( ! chkInputValue("#content", "내용")) return false;
	
	$("#registerForm").submit();
});
*/

</script>

</html>
