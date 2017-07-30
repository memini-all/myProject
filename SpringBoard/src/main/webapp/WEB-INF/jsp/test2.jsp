<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- jQuery  -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- bootstrap JS -->
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	
	<!-- bootstrap CSS -->
	<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
	


</head>

<body>

	<br><br>
	
	<h4> ▶ 첫번째 테이블</h4>
	
	<br><br>

	<div class="row">
		<table id="example-table-1" width="100%" class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>No. </th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>				
				<tr>
					<td>1</td>
					<td>user01</td>
					<td>홍길동</td>
					<td>hong@gmail.com</td>
				</tr>
				<tr>
					<td>2</td>
					<td>user02</td>
					<td>김사부</td>
					<td>kim@naver.com</td>
				</tr>
				<tr>
					<td>3</td>
					<td>user03</td>
					<td>존</td>
					<td>John@gmail.com</td>
				</tr>
			</tbody>
		</table>
		<div class="col-lg-12" id="ex1_Result1" ></div> 
		<div class="col-lg-12" id="ex1_Result2" ></div> 
	</div>
	
	
		<br><br>
	
	<h4> ▶ 두번째 테이블</h4>
	
	<br><br>

	<div class="row">
		<table id="example-table-2" width="100%" class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>No. </th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>버튼</th>
				</tr>
			</thead>
			<tbody>				
				<tr>
					<td>1</td>
					<td>user04</td>
					<td>맥크리</td>
					<td>sunset@gmail.com</td>
					<td><input type="button" class="checkBtn" value="클릭" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>user05</td>
					<td>메르시</td>
					<td>Mercy@naver.com</td>
					<td><input type="button" class="checkBtn" value="클릭" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>user06</td>
					<td>한조</td>
					<td>trolling@gmail.com</td>
					<td><input type="button" class="checkBtn" value="클릭" /></td>
				</tr>
			</tbody>
		</table>
		<div class="col-lg-12" id="ex2_Result1" ></div> 
		<div class="col-lg-12" id="ex2_Result2" ></div> 
	</div>


		<br><br>
	
	<h4> ▶ 세번째 테이블</h4>
	
	<br><br>

	<div class="row">
	
		<button type="button" class="btn btn-outline btn-primary pull-right" id="selectBtn">선택</button>
		<table id="example-table-3" width="100%" class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>선택</th>
					<th>No. </th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>				
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>1</td>
					<td>user07</td>
					<td>NC소프트</td>
					<td>nc@gmail.com</td>
				
				</tr>
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>2</td>
					<td>user08</td>
					<td>넥슨</td>
					<td>donson@naver.com</td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="user_CheckBox" ></td>
					<td>3</td>
					<td>user09</td>
					<td>넷마블</td>
					<td>net@gmail.com</td>
				</tr>
			</tbody>
		</table>
		<div class="col-lg-12" id="ex3_Result1" ></div> 
		<div class="col-lg-12" id="ex3_Result2" ></div> 
	</div>
	
	<br><br>

	<script>
	
	
		// 상단 선택버튼 클릭시 체크된 Row의 값을 가져온다.
		$("#selectBtn").click(function(){ 
			
			var rowData = new Array();
			var tdArr = new Array();

			var checkbox = $("input[name=user_CheckBox]:checked");
			
			// 체크된 체크박스 값을 가져온다
			checkbox.each(function(i) {
	
				// checkbox.parent() : checkbox의 부모는 <td>이다.
				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				
				// 체크된 row의 모든 값을 배열에 담는다.
				rowData.push(tr.text());
				
				// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
				var no = td.eq(1).text()+", "
				var userid = td.eq(2).text()+", ";
				var name = td.eq(3).text()+", ";
				var email = td.eq(4).text()+", ";
				
				// 가져온 값을 배열에 담는다.
				tdArr.push(no);
				tdArr.push(userid);
				tdArr.push(name);
				tdArr.push(email);
				
				//console.log("no : " + no);
				//console.log("userid : " + userid);
				//console.log("name : " + name);
				//console.log("email : " + email);
			});
			
			$("#ex3_Result1").html(" * 체크된 Row의 모든 데이터 = "+rowData);	
			$("#ex3_Result2").html(tdArr);	
		});
	
	
		/******************************************************/
	

		// 버튼 클릭시 Row 값 가져오기
		$(".checkBtn").click(function(){ 
			
			var str = ""
			var tdArr = new Array();	// 배열 선언
			var checkBtn = $(this);
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			var no = td.eq(0).text();
			var userid = td.eq(1).text();
			var name = td.eq(2).text();
			var email = td.eq(3).text();
			
			
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){	
				tdArr.push(td.eq(i).text());
			});
			
			console.log("배열에 담긴 값 : "+tdArr);
			

			str +=	" * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
					", 아이디 : <font color='red'>" + userid + "</font>" +
					", 이름 : <font color='red'>" + name + "</font>" +
					", 이메일 : <font color='red'>" + email + "</font>";		
			
			$("#ex2_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());		
			$("#ex2_Result2").html(str);	
		});
	
	
	
	/******************************/
	
	
		// 테이블의 Row 클릭시 값 가져오기
		$("#example-table-1 tr").click(function(){ 	

			var str = ""
			var tdArr = new Array();	// 배열 선언
			
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
			var td = tr.children();
			
			// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){
				tdArr.push(td.eq(i).text());
			});
			
			console.log("배열에 담긴 값 : "+tdArr);
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			var no = td.eq(0).text();
			var userid = td.eq(1).text();
			var name = td.eq(2).text();
			var email = td.eq(3).text();
			
			
			str +=	" * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
					", 아이디 : <font color='red'>" + userid + "</font>" +
					", 이름 : <font color='red'>" + name + "</font>" +
					", 이메일 : <font color='red'>" + email + "</font>";		
			
			$("#ex1_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());		
			$("#ex1_Result2").html(str);
		});
	
	
	</script>


</body>
</html>

