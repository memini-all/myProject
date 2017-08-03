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
	<script src="/resources/sb-admin/vendor/jquery/jquery-ui.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Metis Menu Plugin JavaScript -->
	<script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
	
	<!-- Custom Theme JavaScript -->
	<script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
	
	<!-- handlebars JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

	<!-- Conmmon JavaScript -->
    <script src="/resources/js/common.js"></script>


	<style type="text/css">

		.label:hover {
		  text-decoration: none;
		  cursor: pointer;
		}

		.label-info:hover {
		  background-color: #2d6987;
		}
		
		.label-danger:hover {
		  background-color: #953b39;
		}
		
		.actionBtn{
			margin-right : 7px;
		}

	</style>

</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<!-- 페이지 가운데 흰부분 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"><i class="fa fa-users fa-fw"></i>사용자 관리</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-lg-12">
	                	<form role="form" id="form1" name="form1"  method="post">
							<div class="form-group col-lg-4">
			                   	
			                   	<!-- 사용자 검색 -->
			                   	<div class="input-group custom-search-form col-lg-9">
		                        	<input class="form-control" placeholder="User ID or Name..." type="text" id="searchKeyword" name="searchKeyword" >
								      <span class="input-group-btn">
								        <button type="button" class="btn btn-outline btn-default" id='searchBtn'>찾기</button>
								      </span>
		                       	</div>
							</div>
						</form>	     
	                
						<div>
                       		<button type="button" class="btn btn-outline btn-primary pull-right actionBtn" title="탈퇴한 회원 보기" id="withdrawBtn">탈퇴회원</button>
                       		<button type="button" class="btn btn-outline btn-primary pull-right actionBtn" title="잠금된 회원 보기" id="lockBtn">잠금회원</button>
                       		<button type="button" class="btn btn-outline btn-primary pull-right actionBtn" title="선택한 회원정보 수정" id="modBtn">수정</button>
                       	</div>
	        	</div>
            </div>

			<div class="row">
					<div class="panel panel-default">
						<div class="table-responsive">
							
							<div id="userListDiv"></div>
	
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel -->
				
				<!-- panel-body -->
		</div>

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
		

	
	
	<!-- 템플릿 -->
	<script id="userListTemplate" type="text/x-handlebars-template">
	<table id="listTable" width="100%" class="table table-bordered table-hover text-center">
		<thead>
			<tr>
				<th><input type="checkbox" id="allUserCheck" onclick="fn_allChecked()"/></th>
				<th>No. </th>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>탈퇴여부</th>
				<th>계정잠금</th>
			</tr>
		</thead>
		<tbody>				
		{{#each .}}
			<tr class="userItm">
				<td><input type="checkbox" name="user_CheckBox" value="{{userno}}" ></td>
				<td>{{inc @key}}</td>
				<td>{{userid}}</td>
				<td>{{username}}</td>
				<td>{{email}}</td>
				<td>{{regdate}}</td>
				<td>
					{{#isYN is_withdraw}}
						<span class="label label-danger" onclick="fn_accountToggle(this)">{{is_withdraw}}</span>
					{{else}} 	
						<span class="label label-info" onclick="fn_accountToggle(this)">{{is_withdraw}}</span>
					{{/isYN}}	
				</td>
				<td>
					{{#isYN islock}}
						<span class="label label-danger" onclick="fn_accountToggle(this)">{{islock}}</span>
					{{else}} 	
						<span class="label label-info" onclick="fn_accountToggle(this)">{{islock}}</span>
					{{/isYN}}	
				</td>
			</tr>
		{{/each}}
			<tr id='addbtn'>
				<td colspan="8">
					<div class="btns"><a href="javascript:fn_moreUserList(0);" id="loadBtn" class="btn btn-outline btn-primary">추가로드</a></div>
				</td>
			</tr>
		</tbody>
	</table>
	</script> 
	
	
	<script>
	
	
		
		/******************** registerHelper *****************/
		
		/*
		 *	테이블의 No. 컬럼 값을 1부터 시작하도록 한다.
		 */
		Handlebars.registerHelper("inc", function(value, options){
			
		    return parseInt(value) + 1;
		});
		
		/*
		 *	탈퇴여부, 잠금여부 라벨 표시
		 */
		Handlebars.registerHelper('isYN', function(value, options) {	 
	
			return value == 'Y' ? options.fn(this) : options.inverse(this);	
		});
		
		
		/******************** registerHelper *****************/
	

		
		// 최초 fn_getUserList() 호출하여 사용자 목록을 가져온다.
		fn_getUserList("/user/mgt/userlist", 0);
		
		
		/*
		 *	작성한 댓글 목록을 가져온다.
		 */
		function fn_getUserList(pageURI, flag) {
	
			$.getJSON(pageURI, function(data) {		
				
				console.log(data);
				
				fn_printTemplate(data, $("#userListDiv"), $('#userListTemplate'), $("#listTable"));
				
				// 추가로드 부분의 <a> 태그
				var loadBtn = document.querySelector('#loadBtn');
				
				// flag 값을 fn_moreUserList()에 전달한다.
				// => 서버에서 flag 값에 따라 다르게 처리
				if(flag == 0){
					loadBtn.href = "javascript:fn_moreUserList("+flag+");";
				}
				else if(flag == 1){
					loadBtn.href = "javascript:fn_moreUserList("+flag+");";
				}
				else if(flag == 2){
					loadBtn.href = "javascript:fn_moreUserList("+flag+");";
				}	
				
			});	
		}
		
	
		/*
		 *	배열형식 데이터, 타겟(div), 템플릿 스크립트, 
		 *	삭제할 Element를 인자로 받아 템플릿 생성 
		 */
	 	var fn_printTemplate = function(dataArr, target, templateObject, delElmt){
	 		
	 		var template = Handlebars.compile(templateObject.html());
	 		var html = template(dataArr);
	 		delElmt.remove();
	 		target.after(html);
	 	}
		
		
		/*
		 * 잠금회원 보기
		 */
		$("#lockBtn").click(function(){
	
			fn_reset();
			fn_getUserList("/user/mgt/lock", 1);
		});
		
		
		/*
		 * 탈퇴회원 보기
		 */
		$("#withdrawBtn").click(function(){
	
			fn_reset();
			fn_getUserList("/user/mgt/withdraw", 2);
		});
		
		
		/*
		 * 사용자 검색
		 */
		$("#searchBtn").click(function(){
			
			params.start = 2;
			idx = 4;
			$("input[name=user_CheckBox]").prop("checked",false); // 체크해제
			
			var keyward = $("#searchKeyword").val();
			
			if(keyward.length <= 1){
				alert("두글자 이상 입력하세요.")
				return false;
			}
			
			fn_getUserList("/user/mgt/userlist?keyword="+keyward, 0);
		});
	
		
		/*
		 * 사용자 정보 수정 - 탈퇴여부, 계정잠금 수정
		 */
		$("#modBtn").click(function(){
			
			var checkbox = $("input[name=user_CheckBox]:checked");
			//var trVal;
			
			var userInfoArray = new Array();	
			
			// 체크된 체크박스 값을 가져온다
			checkbox.each(function(i) {
	
				// 체크된 row 중 i번째 row부터 가져온다. 
				// 즉, 체크된 row가 2개라면 0번째, 1번째 row를 각각 가져오게 된다. (i=0 부터 시작하므로)
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				
				// 선택된 행의 값을 모두 가져온다.
				//trVal = tr.text();

				// 객체를 생성하여 값을 담는다.
				var user = new Object();
				user.userno = td.eq(0).find("input").val();
				user.is_withdraw = td.eq(6).text().trim();
				user.islock = td.eq(7).text().trim();
				
				// 배열에 객체를 담는다.
				userInfoArray.push(user);

				//console.log("체크된 Row : " + txt);
			});
	
			
			// JSON으로 변환한다.
			var userJsonData = JSON.stringify(userInfoArray);
	
			console.log("JSON 데이터 : " + userJsonData);
			
			// 데이터 전송 
			$.ajax({
				url : "/user/mgt/update",
				type : 'post',
				headers : {
					"Content-Type" : "application/json"
				},
				dataType : 'text',
				data : userJsonData,
				success : function(result){

					if (result == 'SUCCESS') {
						fn_reset();
					}	
				}, 
		        error:function(request,status,error){
	        		fn_errorPage(request.status);
	        	}    
			});
			
		});
		
		
		/*
		 * 각종 값들을 초기화 시킨다.
		 */
		function fn_reset(){
			
			// 시작 페이지값, No.에 표시될 index를 초기화 시킨다
			params.start = 2;
			idx = 4;
			// 검색값 초기화
			$("#searchKeyword").val("");
			// 체크박스 체크해제
			$("input[name=user_CheckBox]").prop("checked",false); 
		}

		
		/*
		 * 탈퇴여부, 잠금여부의 값을 변경한다.
		 */
		function fn_accountToggle(spanElmt){
			
			var value= $(spanElmt).text();
			
			if( value == "N" ){
				// span 태그의 class값을 label label-danger 로 변경한다.
				$(spanElmt).removeClass("label label-info").addClass("label label-danger");
				
				// <span></span> 사이의 값을 Y 로 변경한다.
				$(spanElmt).html("Y");
			}
			else if( value == "Y" )	{
				$(spanElmt).removeClass("label label-danger").addClass("label label-info");
				$(spanElmt).html("N");
			}	
		}

	
		/*
		 *	전체선책 - 체크 시 모든 사용자 체크박스가 체크된다.
		 */
		function fn_allChecked(){

			//만약 전체 선택 체크박스가 체크된상태일경우
			if($("#allUserCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다 
				$("input[name=user_CheckBox]").prop("checked",true); 
			
			} else { 
				$("input[name=user_CheckBox]").prop("checked",false); 
			}
		}

	
		// start : 일종의 시작 페이지값, 최초 사용자 목록이 보여지는게 1페이지이므로 2부터 시작
		// end : 화면에 보여질 데이터 수
		var params = { start : 2, end :3 };  
		
		// No.에 표시될 인덱스
		var idx = 4;	

		
		/*
		 *	사용자 목록을 추가로 가져온다.
		 */
		function fn_moreUserList(flag){
			
			var userCnt = '${userCount}';
			var keyward = $("#searchKeyword").val();
			
		    $.ajax({
		        url : "/user/mgt/addition",
		        data : { 
		        	start : params.start
		        	, end : params.end
		        	, flag : flag
		        	, keyward : keyward
		        },
		        success : function(userList){

						var listCnt = userList.length;	// 가져온 데이터 개수	
			            var content="";
	
						// 추가로 가져온 사용자 목록 데이터를 태그로 만든다.
			            for( var i=0 ; i<listCnt ; i++) {
			                content +=
			                "<tr>"+
			               	 	"<td><input type='checkbox' name='user_CheckBox' value=" + userList[i].userno + " ></td>"+
			                	"<td>" + (idx + i) +"</td>"+
			                    "<td>" + userList[i].userid + "</td>"+
			                    "<td>" + userList[i].username + "</td>"+
			                    "<td>" + userList[i].email + "</td>"+
			                    "<td>" + userList[i].regdate + "</td>"
			                    
		                    if(userList[i].is_withdraw == "Y"){
		                    	content += "<td><span class='label label-danger' onclick='fn_accountToggle(this)'>" + 
		                    					userList[i].is_withdraw +
		                    				"</span></td>";
		                    }
		                    else{
		                    	content += "<td><span class='label label-info' onclick='fn_accountToggle(this)'>" + 
		                    					userList[i].is_withdraw + 
		                    				"</span></td>";
		                    }
		                    
		                    if(userList[i].islock == "Y"){
		                    	content +=  "<td><span class='label label-danger' onclick='fn_accountToggle(this)'>" + 
		                    					userList[i].islock + 
		                    				"</span></td>";
		                    }else{
		                    	content +=  "<td><span class='label label-info' onclick='fn_accountToggle(this)'>" + 
		                    					userList[i].islock + 
		                    				"</span></td>";
		                    }  
	 
		                    content += "</tr>";
			            }
	
			            content += "<tr id='addbtn'>"+
			            				"<td colspan='8'>"+
			            					"<div class='btns'>"+
			            						"<a href='javascript:fn_moreUserList("+ flag +");' id='loadBtn' class='btn btn-outline btn-primary'>추가로드</a>"+
			            					"</div>"+
			            				"</td>"+
			            			"</tr>";
			           				
			            $('#addbtn').remove();	//remove btn
	
			            
			        	console.log("userCnt : "+userCnt +" / " + "idx : "+idx);
			            
			            
			            // 현재 화면에 보여진 데이터 수보다 총 데이터 수가 많을경우 [추가로드] 버튼 추가
			            if( userCnt > idx )
			            	$(content).appendTo("#listTable");	// listTable에 사용자 목록을 추가한다.
			              
			            idx = idx + 3;
			            params.start = params.start +1;	// 추가로드 클릭 시 페이지 + 1
			            
			        }, 
			        error : function(request,status,error){
						fn_errorPage(request.status);
		        	}
		    });
			
		};
	</script>
	
</body>

</html>