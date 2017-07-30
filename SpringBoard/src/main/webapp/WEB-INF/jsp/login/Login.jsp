<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="css/sb-admin/font-awesome.min.css" rel="stylesheet">
    

    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/project9.js"></script>  
	 -->
	  
<script>




function fn_formSubmit(){
	
	if ( fn_chkInputValue("#userid", "아이디") ) return false;
	if ( fn_chkInputValue("#userpw", "비밀번호") ) return false;
	
	var formObj = $("form[role='form']");
	
	formObj.attr("action", "/login");
	formObj.attr("method", "post");	
	formObj.submit();
}

// 입력값 체크
function fn_chkInputValue(value, msg){

	var inputVal = $(value).val();
	
	var regxBlank = /[\s]/g;
	
	if( regxBlank.test(inputVal) == true ){
 
		alert("공백은 사용할 수 없습니다.");
		$(value).focus();
		return true;
	}
	else if( inputVal == "" | inputVal == null){			
  
		alert(msg+"을(를) 입력해주세요.");
		$(value).focus();
		return true;
	}
	else{
		return false;
	}
}


var result = '${msg}';

if(result == 'FAIL'){
	alert("아이디 또는 비밀번호를 다시 확인하세요.");
}

</script>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="form1" name="form1">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="ID" name="userid" id="userid" autofocus value="<c:out value="${userid}"/>">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="userpw" id="userpw" type="password" value="" onkeydown="if(event.keyCode == 13) { fn_formSubmit();}">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="useCookie" >Remember Me
                                    </label>
                                </div>
                                <div>
                                    <label>
                                        <a href="#" id="findBtn">Forgot Id/Password?</a>
                                    </label>
                                </div><br>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#" class="btn btn-lg btn-success btn-block" onclick="fn_formSubmit()">Login</a>
                            </fieldset>
                        </form>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.login-panel -->
            </div>
        </div>
        	<!-- /.row -->
    </div>
    	<!-- /.container -->
    	
    <!-- Modal -->  <!-- 아이디, 비밀번호 찾기 -->
    <div class="modal fade" id="findModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" id="closeX" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">아이디 / 비밀번호 찾기</h4>
                </div>
                
                <div class="modal-body">
                
                	<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#myIdFind">아이디 찾기</a>
						</li>
					 	<li>
					 		<a data-toggle="tab" href="#myPwFind" >비밀번호 찾기</a>
					 	</li>
					</ul>
					
					<div class="tab-content">
						<!-- tab1 아이디 찾기 -->
						<div id="myIdFind" class="tab-pane fade in active">
							<br>
							
							<div class="row form-group">
                    			<div class="col-lg-2"></div>
                            	<label class="col-lg-8"><font color="#ED4C00" >* </font>가입시 작성한 이름과 이메일을 입력하세요.</label>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7">
	                            	<input type="text" class="form-control" id="modId1" name="modId1" maxlength="30" placeholder="Your Name..">
	                            </div>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7">
	                            	<input type="text" class="form-control" id="modId2" name="modId2" maxlength="30" placeholder="Your Email..">
	                            </div>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7">
	                            	<button class="btn btn-outline btn-primary btn-block" id="idFindBtn">찾기</button>
	                            </div>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7" id="idFindMsg">
	                            	
	                            </div>
							</div>
							
						</div>
						<!-- /tab1 아이디 찾기 -->	
						
						<!-- tab2 비밀번호 찾기 -->	
						<div id="myPwFind" class="tab-pane fade">
					    	<br>
		
							<div class="row form-group">
                    			<div class="col-lg-2"></div>
                            	<label class="col-lg-8"><font color="#ED4C00" >* </font>비밀번호를 찾고자 하는 아이디를 입력해 주세요.</label>
							</div>

							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7">
	                            	<input type="text" class="form-control" id="modPw" name="modPw" maxlength="20" placeholder="Your ID...">
	                            </div>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7">
	                            	<button class="btn btn-outline btn-primary btn-block" id="pwFindBtn">찾기</button>
	                            </div>
							</div>
							
							<div class="row form-group">
	                            <div class="col-lg-3"></div>
	                            <div class="col-lg-7" id="pwFindMsg">
	                            	
	                            </div>
							</div>

						</div>
						<!-- /tab2 비밀번호 찾기 -->	
							
					</div>		
					<!-- /.tab-content -->

                </div>
                <!-- /.modal-body -->
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="close">닫기</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->	
    
    
    <script type="text/javascript">
    
    
	// 아이디/비밀번호 찾기
	$("#findBtn").on("click", function(){
		
		// dialog 열릴때 값 초기화
		$("#modId1").val("");
		$("#modId2").val("");
		$("#idFindMsg").html("");
		
		$("#modPw").val("");
		$("#pwFindMsg").html("");
		
		$("#findModal").modal("show");	
	});
	
	// 아이디 찾기
	$("#idFindBtn").on("click", function(){
		
		if ( fn_chkInputValue("#modId1", "이름") ) return false;
		if ( fn_chkInputValue("#modId2", "이메일") ) return false;
		
		var username = $("#modId1").val();
		var email = $("#modId2").val();
		
		// 데이터 전송
		$.ajax({
			url : "/user/find/id",
			data : { username : username,  email : email },
			success : function(result){
				
				if (result == '' || result == null){
					
					fn_conMessage("#idFindMsg", "N", "아이디가 없습니다.");
				}
				else{
					
					fn_conMessage("#idFindMsg", "Y", "아이디는 "+result+" 입니다.");
				}
			},
			error : function(request,status,error){
				alert("code : "+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}    
		});
		
	});
	
	
	// 비밀번호 찾기
	$("#pwFindBtn").on("click", function(){
		
		if ( fn_chkInputValue("#modPw", "아이디") ) return false;
		
		var userid = $("#modPw").val();
		
		$.ajax({
			url : "/user/find/pw",
			data : { userid : userid },
			success : function(result){
				
				if (result == '' || result == null){
					
					fn_conMessage("#pwFindMsg", "N", "아이디가 없습니다.");
				}
				else if(result == 'NOUSER'){
					
					fn_conMessage("#pwFindMsg", "N", "아이디가 없습니다.");
				}
				else if(result == 'LOCK'){
					
					fn_conMessage("#pwFindMsg", "N", "계정이 잠겨있습니다. 관리자에게 문의하세요.");
				}
				else{
					
					fn_conMessage("#pwFindMsg", "Y", "임시 비밀번호는 "+result+" 입니다. <br>로그인 후 비밀번호를 변경하세요.");
				}
			},
			error : function(request,status,error){
				alert("code : "+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}    
		});
		
	});
	
	// 상태메시지 
	function fn_conMessage(id, flag, msg){
		
		var elmt = $(id);
		
		if(flag == "N"){	
			elmt.css("color", "#FF2424");   
		}else{
			elmt.css("color", "#47C83E");   
		}
		
		elmt.html(msg);	
	}
    
    </script>
    	
    	
</body>

</html>
