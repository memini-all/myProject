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
		    <script src="/resources/sb-admin/vendor//metisMenu.min.js"></script>
		    <script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
		     -->
	
	<!--  project9 BoardList.jsp JS파일 링크
		    <script src="js/jquery-2.2.3.min.js"></script>
			<script src="js/jquery-ui.js"></script>
			<script src="js/dynatree/jquery.dynatree.js"></script>    
		    <script src="css/sb-admin/bootstrap.min.js"></script>
		    <script src="css/sb-admin/metisMenu.min.js"></script>
		    <script src="css/sb-admin/sb-admin-2.js"></script>
		   -->
	
	<!-- Bootstrap Core CSS -->
	<link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css"
		rel="stylesheet">
	
	<!-- MetisMenu CSS -->
	<link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css"
		rel="stylesheet">
	
	<!-- Custom CSS -->
	<link href="/resources/sb-admin/dist/css/sb-admin-2.css"
		rel="stylesheet">
	<link href="/resources/css/sb-admin-add.css" rel="stylesheet">
	
	<!-- Custom Fonts -->
	<link
		href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css"
		rel="stylesheet" type="text/css">
	
	<!-- jQuery -->
	<script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/sb-admin/vendor/jquery/jquery-ui.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Metis Menu Plugin JavaScript -->
	<script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
	
	<!-- Custom Theme JavaScript -->
	<script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
	
	<!-- Conmmon JavaScript -->
    <script src="/resources/js/common.js"></script>
	
	<script>

	</script>

</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<!-- 페이지 가운데 흰부분 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">로그인 기록(총 ${loginCount}건)</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="panel panel-default">
				<div class="panel-body">
					<div class="panel panel-default">
						<div class="table-responsive">
						
							<table id="listTable" width="100%" class="table table-bordered table-hover text-center" id="dataTables-example">
								<thead>
									<tr>
										<th>No. </th>
										<th>일시</th>
										<th>접속 IP</th>
										<th>유형</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${historyList}" var="loginVO" varStatus="status">	
									<tr>
										<td>${status.index + 1}</td>
										<td>${loginVO.ldate}</td>
										<td>${loginVO.conip}</td>
										<td>${loginVO.ltype}</td>
									</tr>	
								</c:forEach>
									<tr id='addbtn'>
										<td colspan="4">
											<div class="btns"><a href="javascript:fn_moreLoginList();" class="btn btn-outline btn-primary">더보기</a></div>
										</td>
									</tr>
								</tbody>
							</table>
							
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- panel-body -->
			</div>
			<!-- panel panel-default -->

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	
	
	<script>
		
		// start : 일종의 시작 페이지값, 최초 로그인기록이 보여지는게 1페이지 이므로 2부터 시작
		// end : 화면에 보여질 데이터 수
		var params = { start : 2, end :20 };  
		
		// 로그인기록 화면 No.에 표시될 인덱스
		var idx = 21;	

		
		function fn_moreLoginList(){
			
			var userno = '${sessionScope.login.userno}';
			var loginCnt = '${loginCount}';

		    $.ajax({
		        url : "/user/history/"+userno,
		        data : {start: params.start, end:params.end},
		        success : function(data){

						var historyCnt = data.historyList.length;	// 가져온 데이터 개수	
			            var content="";
	
			            for( var i=0 ; i<historyCnt ; i++) {
			                content +=
			                "<tr>"+
			                	"<td>"+ (idx + i) +"</td>"+
			                    "<td>"+ data.historyList[i].ldate +"</td>"+
			                    "<td>"+ data.historyList[i].conip +"</td>"+
			                    "<td>"+ data.historyList[i].ltype +"</td>"+
			                "</tr>";
			            }
	
			            content += "<tr id='addbtn'>"+
			            				"<td colspan='4'>"+
			            					"<div class='btns'>"+
			            						"<a href='javascript:fn_moreLoginList();' class='btn btn-outline btn-primary'>더보기</a>"+
			            					"</div>"+
			            				"</td>"+
			            			"</tr>";
			           				
			            $('#addbtn').remove();	//remove btn
	
			            console.log("loginCnt : "+loginCnt+", idx : "+idx);
			            
			            // 현재 화면에 보여진 데이터 수보다 총 데이터 수가 많을경우 [더보기] 버튼 추가
			            if( loginCnt > idx )
			            	$(content).appendTo("#listTable");
			              
			            idx = idx + 20;
			            params.start = params.start +1;	// 더보기 클릭 시 페이지 + 1
		            
		          	}, 
			        error:function(request,status,error){
		        		fn_errorPage(request.status);
		        	}
		    });
			
		};
	</script>
	
</body>

</html>