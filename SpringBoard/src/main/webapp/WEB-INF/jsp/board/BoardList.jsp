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


<script>
	
	$(document).ready(function() {
	
		var result = '${msg}';

		if(result == 'LOCK'){
			alert("계정이 잠겨있어 로그인 할 수 없습니다. \n운영자에게 문의하세요.");
		}
		
		// 글쓰기
		$('#newBtn').on("click", function(event) {
			self.location = "/board/post";
		});
		
		// 검색버튼
		$('#searchBtn').on('click', function(event){
			fn_search($('#keywordInput').val());
		});

	});
	
	// 검색
	function fn_search(value){

		var url =  "/board/list"
			+ '${pageCalculate.makeURI(1)}'
			+ "&searchType="
			+ $("select option:selected").val()
			+ "&keyword="
			+ encodeURIComponent(value);
	
			self.location = url;
	}
	
	
	// 작성자 클릭시 작성글 표시
	function fn_writerSearch(value){

		var url =  "/board/list"
			+ '${pageCalculate.makeURI(1)}'
			+ "&searchType=w"
			+ "&keyword="
			+ encodeURIComponent(value);
	
			self.location = url;
	}
	
</script>

</head>
<body>

	<div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />

		<!-- 페이지 가운데 흰부분 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">게시판</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			
			<div class="row">
	        	<div class="col-lg-12">
	                	<!-- 게시판 아이콘 -->
		                <button id="boardlistBtn" type="button" class="btn btn-default" onclick="self.location.href='/board/list'">
		                	<i class="fa  fa-files-o fa-fw"></i>
		                </button>      
	                
					<!-- 글쓰기 버튼 : 로그인 사용자만 보이도록 -->
					<c:if test="${sessionScope.login != null}">
						<button type="button" class="btn btn-default pull-right" id="newBtn">
							<i class="fa fa-edit fa-fw"></i> 글쓰기
						</button> 
					</c:if>

	        	</div>
			</div>
			<font style="font-size:1px">&nbsp;</font>
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
								<c:if test="${boardVO.fileCnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${boardVO.fileCnt}개 파일"/>"></i>
								</c:if>	
							</div>
							<div class="listHiddenField pull-left field60"><c:out value="${boardVO.brdno}"/></div> 					
							<div class="listHiddenField pull-right field60 textCenter"><c:out value="${boardVO.viewcnt}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${boardVO.regdate}"/></div>
							<div class="listHiddenField pull-right field100 textCenter">
								<a href="javascript:fn_writerSearch('<c:out value="${boardVO.username}"/>');"><c:out value="${boardVO.username}"/></a>
							</div> 		
							<div class="listTitle">
														<!-- 목록에서 글제목 클릭 시 페이지 정보 넘긴다. -->	
								<a href="/board/detail${pageCalculate.makeSearchURI(pageCalculate.cri.page) }&brdno=${boardVO.brdno}" >${boardVO.title}</a>	
								<c:if test="${boardVO.replycnt > 0}">
									(<c:out value="${boardVO.replycnt}"/>)	<!-- 댓글 개수 -->
								</c:if>						
							</div>

							 
							<!-- 화면 축소시 보여질 부분 -->
							<div class="showField text-muted small">
								<c:out value="글쓴이"/> 
								<c:out value="${boardVO.regdate}"/>
								<i class="fa fa-eye fa-fw"></i> <c:out value="${boardVO.viewcnt}"/>
								<c:if test="${boardVO.fileCnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${boardVO.fileCnt}"/>"></i>
								</c:if>									
							</div>

							 
						</div>
					<!-- listBody -->	
						
					</c:forEach>
	
					<!-- 페이지 및 검색 -->	 
					<form role="form" id="form1" name="form1" method="post"></form>	
					    
					    <!-- 페이지 -->
						<div id="dataTables-example_paginate" class="text-center">
							<ul class="pagination">
							
							<!-- 
							<c:if test="${pageCalculate.cri.page > 1}">
								<li title="처음으로" class="paginate_button" ><a href="/board/list${pageCalculate.makeSearchURI(1) }"><i class="fa fa-angle-double-left fa-fw"></i> </a></li>
							</c:if>	
							 -->	
									<c:if test="${pageCalculate.prev}">
										<!--  
										<li title="이전" class="paginate_button">
											<a href="/board/list${pageCalculate.makeSearchURI(pageCalculate.startPage - 1) }">
												<i class="fa fa-angle-left  fa-fw"></i>
											</a>
										</li>
										-->
										<li title="이전" class="page-item">
									       <a class="page-link" href="/board/list${pageCalculate.makeSearchURI(pageCalculate.startPage - 1) }" aria-label="Previous">
									        <span aria-hidden="true">&laquo;</span>
									        <span class="sr-only">Previous</span>   
									      </a>
									    </li>
									</c:if>
		
									<c:forEach begin="${pageCalculate.startPage }" end="${pageCalculate.endPage }" var="idx">
										<!-- 현재 선택된 페이지는 링크 비활성화, 버튼 파랑색 처리 -->
										<c:choose>
							                <c:when test="${idx eq pageCalculate.cri.page}">
												<li class="page-item active"><a href="#"><c:out value="${idx}"/></a></li>  <!-- active : 선택된 페이지번호 파란색으로 표시 -->
							                </c:when>
							                <c:otherwise>							           
												<li class="page-item""><a href="/board/list${pageCalculate.makeSearchURI(idx) }">${idx}</a></li>
							                </c:otherwise>
						           		</c:choose>
									</c:forEach>
		
									<c:if test="${pageCalculate.next && pageCalculate.endPage > 0}">
										<!--
										<li title="다음" class="page-item"">
											<a href="/board/list${pageCalculate.makeSearchURI(pageCalculate.endPage +1) }">
												<i class="fa fa-angle-right fa-fw"></i>
											</a>
										</li>
									  -->	
										<li title="다음" class="page-item">
									       <a class="page-link" href="/board/list${pageCalculate.makeSearchURI(pageCalculate.endPage +1) }" aria-label="Next">
									        <span aria-hidden="true">&raquo;</span>
									        <span class="sr-only">Next</span>   
									      </a>
									    </li>
										
									</c:if>

							<!-- 
								<c:if test="${pageCalculate.cri.page < pageCalculate.totalPage }">
									<li title="끝으로" class="paginate_button"><a href="/board/list${pageCalculate.makeSearchURI(pageCalculate.totalPage) }"><i class="fa fa-angle-double-right  fa-fw"></i></a></li>
								</c:if>	
							 -->
							 
							</ul>
							
						</div>					    
					    
				    	<!-- 기존 검색 부분
						<div class="form-group">
						
							<div class="col-lg-2" style="width:auto;">
							
								<select class="selectpicker form-control"  name="searchType" style="width:auto;">
									<option value="n" <c:out value="${cri.searchType == null ? 'selected' : '' }"/> >---</option>
                                	<option value="t" <c:out value="${cri.searchType eq 't' ? 'selected' : '' }"/> >제목</option>
                                	<option value="c" <c:out value="${cri.searchType eq 'c' ? 'selected' : '' }"/> >내용</option>
                                	<option value="w" <c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/> >작성자</option>
                                	<option value="tc" <c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/> >제목+내용</option>
                                </select>
							</div>
		                   
			                <div class="input-group custom-search-form col-lg-3">
								<input class="form-control" placeholder="Search..." type="text" name='keyword' 
		                        		id="keywordInput" value='${cri.keyword }' >
								<span class="input-group-btn">
									<button class="btn btn-default" id='searchBtn'>
										<i class="fa fa-search" style="font-size:1.45em"></i>
									</button>
								</span>
							</div>

						</div>
	 -->
						<!-- 검색 -->
						<div class="form-group col-lg-5" >
							<div class="input-group">
								
						      <div class="input-group-btn" style="width:auto;">
						        <select class="selectpicker form-control"  name="searchType" style="width:auto;">
	                                	<option value="t" <c:out value="${cri.searchType eq 't' ? 'selected' : '' }"/> >제목</option>
	                                	<option value="c" <c:out value="${cri.searchType eq 'c' ? 'selected' : '' }"/> >내용</option>
	                                	<option value="w" <c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/> >작성자</option>
	                                	<option value="tc" <c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/> >제목+내용</option>
	                            </select>
						      </div>
						      		<!-- 검색입력창 및 버튼 -->
						      <input type="text" class="form-control" placeholder="Search..." id="keywordInput" value='${cri.keyword }'
						      		onkeydown="if(event.keyCode == 13){ fn_search(this.value); }">
						      <span class="input-group-btn">
						        <button type="button" class="btn btn-outline btn-primary" id='searchBtn'>검색</button>
						      </span>
						    </div>
						</div>
					
						<!-- /검색 -->

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