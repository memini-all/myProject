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
						<i class="fa fa-user fa-fw"></i> 회원정보 수정
					</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- 사용자 추가부분 -->
			<div class="panel panel-default">

				<div class="panel-body">
				
					<form id="form1" name="form1" role="form" enctype="multipart/form-data">
					
						<input type="hidden" name="userno" id="userno" value="${userVO.userno}"> 

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label id="idLb" class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>아이디</label>
							<div class="col-lg-3 form-check disabled">
								<input type="text" class="form-control" id="userid" name="userid" maxlength="20"
									 readonly disabled="disabled" value="${userVO.userid}">
							</div>
						</div>
						
						<div class="row form-group" id="pwDiv">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>비밀번호</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="userpw" name="userpw" maxlength="20" value="${userVO.userpw}">
							</div>
							<div class="col-lg-5" id="pwMsg" ></div> 
						</div>

						<div class="row form-group" id="pwDiv">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2">비밀번호 확인</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="userpw2" name="userpw2" maxlength="20" value="${userVO.userpw}">
							</div>
							<div class="col-lg-5" id="repwMsg" ></div> 
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>이름</label>
							<div class="col-lg-3">
								<input type="text" class="form-control" id="username" name="username" maxlength="20" value="${userVO.username}">
							</div>
							<div class="col-lg-5" id="nameMsg" ></div> 
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2"><font color="#ED4C00" >* </font>이메일</label>
							<div class="col-lg-3" style="float:left;">
								<input type="text"class="form-control" id="email" name="email" maxlength="35" value="${userVO.email}">
							</div>	
							<div class="col-lg-5" id="mailMsg" ></div> 
						</div>

						<div class="row form-group">
							<div class="col-lg-1"></div>
							<label class="control-label col-xs-4 col-sm-2">프로필 이미지</label>
							<div class="col-lg-2">
								<img id="thumbnailImg" style="width: 100%; height: 120px; max-width: 100px;" >	
								<input type="hidden" name="profile_Img" id="hiddenImg" value="${userVO.profile_Img}"> 
							</div>
							<div class="col-lg-6 col-sm-6 col-12">
								<label class="btn btn-default">
								찾아보기&hellip;<input type="file" style="display:none;" name="profileImg" id="profileImg" 
													onchange="fn_fileTypeCheck(this)" accept="image/gif, image/jpeg, image/png" />
								</label>
								<button type="button" class="btn btn-default" id="delBtn">삭제</button>
							</div>
							<div class="col-lg-4">
								<br><font color="#B7B7B7" size="2px">(최대용량 3MB 이내, 파일형식 jpg, gif, png, jpeg)</font>
							</div>  
						</div>
					</form>
				</div>
				
				<!-- /.panel-body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="cancelBtn">취소</button>
					<button type="button"  class="btn btn-default" id="modBtn" disabled="disabled" >회원정보 수정</button>
				</div>
			</div>
			<!-- /#panel -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->



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
					$("#thumbnailImg").attr({ 
						src: "/profile/image/"+userno
					});
				}
			}, 
	        error:function(request,status,error){
        		fn_errorPage(request.status);
        	}	
		});
	});


	var formObj = $("form[role='form']");
	
	
	/* 
	 *	회원정보 수정
	 */
	$("#modBtn").on("click", function(){

		formObj.attr("action", "/user/update");
		formObj.attr("method", "post");	
		formObj.submit();
	});
	

	//  가입버튼 비활성화를 위한 변수설정
    var pwdCheck = 0;
    var repwCheck = 0;
	var nameCheck = 0;
	var emailCheck = 0;

	
	/* 
	 *	비밀번호 체크
	 */
	$("#userpw").focusout(function(){

		var regPw = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,20}$/;
		var userpw = $("#userpw").val();
		
		
		if( fn_isEmpty(userpw, "#pwMsg") ){
			pwdCheck = 0;
		}	
		else if( regPw.test(userpw) == false ){
			pwdCheck = 0;
			fn_activeButton("N");
			$("#pwMsg").css("color", "#FF2424");   
			$("#pwMsg").html("5~20자 영문 대소문자, 숫자, 특수문자를 사용하세요.");	
		}
		else{
			pwdCheck = 1;
			$("#pwMsg").css("color", "#47C83E");   
			$("#pwMsg").html("<i class='fa fa-check fa-fw' style='font-size:1.45em'></i>");
			
			fn_validCheck(pwdCheck, repwCheck, nameCheck, emailCheck);
		}

	});


	/* 
	 *	비밀번호 확인
	 */
	$("#userpw2").focusout(function(){
		
		var regPw = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,20}$/;
		var pw = $("#userpw").val();
		var repw = $("#userpw2").val();	
		
		
		if( fn_isEmpty(repw, "#repwMsg") ){
			repwCheck = 0;
			$("#userpw2").val("");
		}
		else if( regPw.test(repw) == false ){
			repwCheck = 0;
			fn_activeButton("N");
			$("#repwMsg").css("color", "#FF2424");   
			$("#repwMsg").html("5~20자 영문 대소문자, 숫자, 특수문자를 사용하세요.");	
			$("#userpw2").val("");
		}
		else if( pw != repw ){
			repwCheck = 0;
			fn_activeButton("N");
			$("#repwMsg").css("color", "#FF2424");   
			$("#repwMsg").html("비밀번호가 일치하지 않습니다.");	
			$("#userpw2").val("");
		}
		else{
			repwCheck = 1;
			$("#repwMsg").css("color", "#47C83E");   
			$("#repwMsg").html("<i class='fa fa-check fa-fw' style='font-size:1.45em'></i>");	
			
			fn_validCheck(pwdCheck, repwCheck, nameCheck, emailCheck);
		}

	});


	/* 
	 *	이름 체크
	 */
	$("#username").focusout(function(){
		
		var regName = /^[a-zA-Z가-힣]{2,12}$/;
		var name = $("#username").val();
		

		if( fn_isEmpty(name, "#nameMsg") ){
			nameCheck = 0;
		}
		else if( regName.test(name) == false ){
			nameCheck = 0;
			fn_activeButton("N");
			$("#nameMsg").css("color", "#FF2424");   
			$("#nameMsg").html("2~12자 한글과 영문 대소문자를 사용하세요.");	
		}
		else{ 
			nameCheck = 1;
			$("#nameMsg").css("color", "#47C83E");   
			$("#nameMsg").html("<i class='fa fa-check fa-fw' style='font-size:1.45em'></i>");
			
			fn_validCheck(pwdCheck, repwCheck, nameCheck, emailCheck);
		}

	});

	
	/* 
	 *	이메일 체크
	 */
	$("#email").focusout(function(){
		
		var email = $("#email").val();
		var arrSplit = email.split("@");
		
		
		if( fn_isEmpty(email, "#mailMsg") ){
			emailCheck = 0;
			return false;
		}
		
		if( arrSplit.length == 2 )
		{
			var emailAccount = arrSplit[0];
			var emailDomain = arrSplit[1];

			if( emailAccount.length < 3 ){
				emailCheck = 0;
				fn_activeButton("N");
				$("#mailMsg").css("color", "#FF2424");   
				$("#mailMsg").html("@앞의 아이디는 최소 3자 이상 입력하셔야 합니다.");	
				return false;
			}
			
			var regxAccount = /^[A-Za-z0-9_]+(\.[A-Za-z0-9_]+)?$/;
            var regxDomain = /^([A-Za-z0-9-]+\.)+[A-Za-z]{2,6}$/;
	          
            if( regxAccount.test(emailDomain) == false ){
            	emailCheck = 0;
            	fn_activeButton("N");
            	$("#mailMsg").css("color", "#FF2424");   
    			$("#mailMsg").html("올바른 이메일 주소를 입력하세요.");	
    			return false;
            }
               
           if( regxDomain.test(emailDomain) == false ){
            	emailCheck = 0;
            	fn_activeButton("N");
            	$("#mailMsg").css("color", "#FF2424");   
    			$("#mailMsg").html("올바른 이메일 주소를 입력하세요.");	
    			return false;
            }

			emailCheck = 1;
			$("#mailMsg").css("color", "#47C83E");   
			$("#mailMsg").html("<i class='fa fa-check fa-fw' style='font-size:1.45em'></i>");	
     			
			fn_validCheck(pwdCheck, repwCheck, nameCheck, emailCheck);    
	    }
		else{
			emailCheck = 0;
			fn_activeButton("N");
        	$("#mailMsg").css("color", "#FF2424");   
			$("#mailMsg").html("올바른 이메일 주소를 입력하세요.");	
		}
	});

	
	/* 
	 *	회원정보 수정버튼 활성, 비활성
	 */
	function fn_activeButton(flag){
		
		// 등록버튼 활성
		if(flag == "Y"){
			$("#modBtn").prop("disabled", false);
			$("#modBtn").removeClass("btn btn-default").addClass("btn btn-primary");
		}
		else{	// 비활성
			$("#modBtn").prop("disabled", true);
			$("#modBtn").removeClass("btn btn-primary").addClass("btn btn-default");	
		}
	}
	
	
	/* 
	 *	공백 체크
	 */
	function fn_isEmpty(value, divElmt){

		var regxBlank = /[\s]/g;
		
		if( regxBlank.test(value) == true ){
			fn_activeButton("N");
			$(divElmt).css("color", "#FF2424");   
			$(divElmt).html("공백은 사용할 수 없습니다.");
			return true;
		}
		else if( value == "" | value == null){			
			fn_activeButton("N");
			$(divElmt).css("color", "#FF2424");   
			$(divElmt).html("필수 정보입니다.");		
			return true;
		}
		else{
			return false;
		}
	}

    
	/* 
	 *	유효성 체크
	 */
	function fn_validCheck(pwVal, repwVal, nameVal, emailVal){
		
		if( pwVal == 1 && repwVal == 1 && nameVal == 1 && emailVal == 1 ){
			fn_activeButton("Y");
		}else{
			fn_activeButton("N");
		}	
	}


	/* 
	 *	프로필 이미지 파일 체크
	 */
	function fn_fileTypeCheck(fileElmt){
		
		var fileTypes = ['jpg','gif','png','jpeg'];
		
		var pathpoint = fileElmt.value.lastIndexOf('.');
		var filepoint = fileElmt.value.substring(pathpoint + 1, fileElmt.length);
		var fileType = filepoint.toLowerCase();

		if ( fileTypes.indexOf(fileType) == -1 ) {
			alert('이미지 파일만 선택할 수 있습니다.');
			$("#profileImg").val("");
			return false;
		} 
		
		// 이미지 파일 용량 체크
		if ( !fn_fileSizeChk(fileElmt) ) return false;
		// 썸네일 이미지 생성
		fn_makeThumbnail(fileElmt);
	}
	
	
	/* 
	 *	프로필 사진 삭제	
	 */
	$("#delBtn").on("click", function(){
		
		$("#profileImg").val(""); // 새로운 프로필 이미지 제거
		$("#thumbnailImg").removeAttr("src"); // 썸네일 이미지 제거
		
		$("#hiddenImg").val("");  // 기존에 프로필 이미지 파일명 제거
	});

	 
	/* 
	 *	프로필 이미지 파일 용량체크 
	 */
	function fn_fileSizeChk(fileElmt){
		
		var maxSize = 1024 * 1024 * 3; // 3mb까지
		var fileSize = fileElmt.files[0].size;	// 파일 사이즈
		
		if(fileSize > maxSize){
             alert("파일은 최대 3MB까지 첨부 가능합니다.");
             $("#profileImg").val("");
             return false;
         }
		return true;
	}
	
	
	/* 
	 *	썸네일 이미지 생성
	 */
	function fn_makeThumbnail(fileElmt){
		
		var imgfile = fileElmt.files[0];

		var reader = new FileReader();
	    reader.readAsDataURL(imgfile);
		
		reader.onload = function  () {
	        
	        //썸네일 이미지 생성
	        var tempImage = new Image(); 	//drawImage 메서드에 넣기 위해 이미지 객체화
	        tempImage.src = reader.result; 	//data-uri를 이미지 객체에 주입
	        tempImage.onload = function() {
	            //리사이즈를 위해 캔버스 객체 생성
	            var canvas = document.createElement('canvas');
	            var canvasContext = canvas.getContext("2d");
	            
	            //캔버스 크기 설정
	            canvas.width = 100; //가로 100px
	            canvas.height = 120; //세로 120px
	            
	            //이미지를 캔버스에 그리기
	            canvasContext.drawImage(this, 0, 0, 100, 120);
	            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
	            var dataURI = canvas.toDataURL("image/jpeg");
	            //썸네일 이미지 보여주기
	            document.querySelector('#thumbnailImg').src = dataURI;
	            $("#hiddenImg").val("");  // 기존에 프로필 이미지 파일명 삭제
	        };
		}    
	}
	

	/* 
	 *	회원정보 수정 취소
	 */
	$("#cancelBtn").on("click", function(){
	  	self.location = "/user/info";	  
	});


</script>


</body>

</html>