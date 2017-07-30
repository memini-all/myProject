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
	<link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css"
		rel="stylesheet">
	
	<!-- Custom CSS -->
	<link href="/resources/sb-admin/dist/css/sb-admin-2.css"
		rel="stylesheet">
	
	<!-- Custom Fonts -->
	<link
		href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css"
		rel="stylesheet" type="text/css">
	
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

</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<i class="fa fa-user fa-fw"></i> 나의 정보
					</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- 사용자 추가부분 -->
			<div class="panel panel-default">

				<div class="panel-body">
				
					<form id="form1" name="form1" role="form" enctype="multipart/form-data">
					

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label id="idLb" class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>아이디</label>
							<div class="col-lg-3 form-check disabled">
								<input type="text" class="form-control" id="userid" name="userid" maxlength="20" readonly value="${userVO.userid}">
							</div>
						</div>
						
						<div class="row form-group" id="pwDiv">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>비밀번호</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="userpw" name="userpw" maxlength="20" readonly value="${userVO.userpw}">
							</div>
						</div>

						<div class="row form-group" id="pwDiv">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2">비밀번호 확인</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="userpw2" name="userpw2" maxlength="20" readonly value="${userVO.userpw}">
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>이름</label>
							<div class="col-lg-3">
								<input type="text" class="form-control" id="username" name="username" maxlength="20" readonly value="${userVO.username}">
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>이메일</label>
							<div class="col-lg-3" style="float:left;">
								<input type="text"class="form-control" id="email" name="email" maxlength="35" readonly value="${userVO.email}">
							</div>	
						</div>
						
						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2">가입일</label>
							<div class="col-lg-3" style="float:left;">
								<input type="text"class="form-control" id="regdate" name="regdate" maxlength="35" readonly value="${userVO.regdate}">
							</div>	
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2">프로필 이미지</label>
							<div class="col-lg-2">
								<img id="thumbnailImg" style="width: 100%; height: 120px; max-width: 100px;" />
							</div>
						</div>
						
					</form>
				</div>
				
				<!-- /.panel-body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-outline btn-primary" id="cancelBtn">취소</button>
					<button type="button" class="btn btn-outline btn-primary" id="modBtn" >회원정보 수정</button>
					<button type="button" class="btn btn-outline btn-primary" id="delDialogBtn" >회원탈퇴</button>
				</div>
			</div>
			<!-- /#panel -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->


	<!-- Modal -->  <!-- 회원탈퇴 부분 -->
    <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" id="closeX" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">회원탈퇴</h4>
                </div>
                
                <div class="modal-body">
                	<form id="form1" name="form1">
						<input type="hidden" name="userno" id="modUserno"> 
                    	
                    	<div class="row form-group">
                    		<div class="col-lg-3"></div>
                            <label class="col-lg-8"><font color="#ED4C00" >* </font>탈퇴를 원하시면 비밀번호를 입력해 주세요.</label>
                        </div>
                        
                        <div class="row form-group">
                            <div class="col-lg-3"></div>
                            <div class="col-lg-7">
                            	<input type="password" class="form-control" id="modPw" name="modPw" maxlength="20" placeholder="비밀번호">
                            </div>
                        </div>
                        
                        <div class="row form-group">
                            <div class="col-lg-3"></div>
                            <div class="col-lg-7">
                            	<input type="password" class="form-control" id="modPw2" name="modPw2" maxlength="20" placeholder="비밀번호 확인">
                            </div>
                        </div>
                        
						<div class="row form-group">
							<div class="col-lg-3"></div>
                            <div class="col-lg-8" id="pwMsg" ></div> 
                        </div>
  
                	</form>        
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="close">취소</button>
                    <button type="button" class="btn btn-default" disabled="disabled" id="delBtn">탈퇴</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->


<script type="text/javascript">


	$(document).ready(function(){
		
		var userno = ${userVO.userno};
		
		$.ajax({
			url : '/profile/image/'+userno,
			success : function(result) {
				
				if(result == null || result == ""){
					$("#thumbnailImg").removeAttr("src");
				}
				else{
					//document.querySelector('#thumbnailImg').src = "/profile/image/"+userno;
					$("#thumbnailImg").attr({ 
						src: "/profile/image/"+userno
					});
				}
			}	
		});
	});
 
	
	var formObj = $("form[role='form']");
	
	// 회원정보 보기 취소
	$("#cancelBtn").on("click", function(){
	  	self.location = "/board/list";	  
	});
	
	// 회원정보 수정
	$("#modBtn").on("click", function(){
		self.location = "/user/view/update";	  
	});
	
	
	/**************** 회원 탈퇴 *****************/
	
	// 회원탈퇴 버튼 클릭 - 탈퇴 창 보여줌
	$("#delDialogBtn").on("click", function(){
		
		// 탈퇴창 열릴때 초기화
		$("#modPw").val("");
		$("#modPw2").val("");
		$("#pwMsg").html("");	
		
		$("#delModal").modal("show");	
	});
	
	
	// 회원 탈퇴
	$("#delBtn").on("click", function(){

		var userno = ${userVO.userno};
		var pw = $("#modPw").val();
		
		$.ajax({
			type : 'post',
			url : '/user/delete',
			data : {userno : userno, userpw : pw},
			success : function(result) {
				
				if(result == 'NOUSER'){
					// DB에 저장된 비밀번호와 일치하지 않을경우 
					$("#modPw").val("");
					$("#modPw2").val("");
					$("#pwMsg").css("color", "#FF2424");   
					$("#pwMsg").html("올바른 비밀번호를 입력하세요.");	
				}
				else if (result == 'SUCCESS') {
					
					alert("정상적으로 탈퇴되었습니다.");
					
					$("#myModal").modal("hide");
					self.location = "/logout";	  
				}
			}, 
	        error:function(request,status,error){
        		fn_errorPage(request.status);
        	}	
		});

		
	});
	
	// 탈퇴버튼 활성,비활성화를 위한 변수설정
    var pwdCheck = 0;
    var repwCheck = 0;
	
	// 비밀번호 체크
	$("#modPw").focusout(function(){

		var regPw = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,20}$/;
		var userpw = $("#modPw").val();
		
		
		if( fn_isEmpty(userpw, "#pwMsg") ){
			pwdCheck = 0;
		}	
		else if( regPw.test(userpw) == false ){
			pwdCheck = 0;
			$("#pwMsg").css("color", "#FF2424");   
			$("#pwMsg").html("5~20자 영문 대소문자, 숫자, 특수문자를 사용하세요.");	
		}
		else{
			pwdCheck = 1;
			$("#pwMsg").html("");	
			
			fn_validCheck(pwdCheck, repwCheck);
		}

	});
	
	// 비밀번호 확인
	$("#modPw2").focusout(function(){
		
		var userno = ${userVO.userno};
		
		var regPw = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,20}$/;
		var pw = $("#modPw").val();
		var repw = $("#modPw2").val();	
		
		
		if( fn_isEmpty(repw, "#pwMsg") ){
			repwCheck = 0;
			$("#modPw2").val("");
		}
		else if( regPw.test(repw) == false ){
			repwCheck = 0;
			fn_activeButton("N");
			$("#pwMsg").css("color", "#FF2424");   
			$("#pwMsg").html("5~20자 영문 대소문자, 숫자, 특수문자를 사용하세요.");	
			$("#modPw2").val("");
		}
		else if( pw != repw ){
			repwCheck = 0;
			fn_activeButton("N");
			$("#pwMsg").css("color", "#FF2424");   
			$("#pwMsg").html("비밀번호가 일치하지 않습니다.");	
			$("#modPw2").val("");
		}
		else{
			repwCheck = 1;		
			$("#pwMsg").html("");	
			fn_validCheck(pwdCheck, repwCheck);
		}

	});
	
	
	// 공백 체크
	function fn_isEmpty(value, divElmt){

		var regxBlank = /[\s]/g;
		
		if( regxBlank.test(value) == true ){

			$(divElmt).css("color", "#FF2424");   
			$(divElmt).html("공백은 사용할 수 없습니다.");
			return true;
		}
		else if( value == "" | value == null){			

			$(divElmt).css("color", "#FF2424");   
			$(divElmt).html("비밀번호를 입력하세요.");		
			return true;
		}
		else{
			return false;
		}
	}
	
	
	// 유효성 체크
	function fn_validCheck(pwVal, repwVal){
		
		if( pwVal == 1 && repwVal == 1 ){
			fn_activeButton("Y");
		}else{
			fn_activeButton("N");
		}	
	}
	
	// 탈퇴버튼 활성, 비활성
	function fn_activeButton(flag){
		
		// 탈퇴버튼 활성
		if(flag == "Y"){
			$("#delBtn").prop("disabled", false);
			$("#delBtn").removeClass("btn btn-default").addClass("btn btn-primary");
		}
		else{	// 비활성
			$("#delBtn").prop("disabled", true);
			$("#delBtn").removeClass("btn btn-primary").addClass("btn btn-default");	
		}
	}
	

</script>


</body>

</html>