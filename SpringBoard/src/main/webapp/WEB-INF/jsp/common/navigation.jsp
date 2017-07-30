<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/board/list">Spring Board</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
            
                <!-- /.dropdown 사용자정보/로그아웃-->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                    
	                    <c:choose>
						    <c:when test="${sessionScope.login != null}">
								<li><a href="/user/info"><i class="fa fa-user fa-fw"></i> 회원정보</a></li>
								<li><a href="/user/activity"><i class="fa fa-commenting-o fa-fw"></i> 나의 활동</a></li>	
		                        <li><a href="/user/history"><i class="fa fa-history fa-fw"></i> 로그인 기록</a></li>
		                        <li class="divider"></li>
		                        <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i> 로그아웃</a></li>
						    </c:when>
						    <c:otherwise>
						    	<a href="/view/login"><i class="fa fa-sign-in fa-fw"></i> 로그인</a>
						    </c:otherwise>
						</c:choose>
                        
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                        
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search..." 
                                			id="navKeywordInput" onkeydown="if(event.keyCode == 13){ fn_naviSearch(); }">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id='navSearchBtn'>
                                    <i class="fa fa-search" style="font-size:1.45em"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="/board/list"><i class="fa fa-files-o fa-fw"></i> 게시판</a>
                        </li>
                      
                        <c:choose>
							<c:when test="${sessionScope.login == null}">
								<li>
									<a href="/view/login"><i class="fa fa-sign-in fa-fw"></i> 로그인</a>
								</li>
                       			<li>
                            		<a href="/user/join"><i class="fa fa-user fa-fw"></i> 회원가입</a>
                        		</li>
							</c:when>
							<c:otherwise>
								<li>
                            		<a href="/logout"><i class="fa fa-sign-out fa-fw"></i> 로그아웃</a>
                        		</li>		    	
							</c:otherwise>
						</c:choose>
   
   						<c:if test="${sessionScope.login.authority == 'AD'}">
                        <li>
                            <a href="#"><i class="fa fa-cogs fa-fw"></i> 관리자<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/user/mgt"><i class="fa fa-users fa-fw"></i> 사용자 관리</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                       </c:if>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>


	<script>

	// 상단 검색버튼
	$('#navSearchBtn').on('click', function(event){
		
		fn_naviSearch();
	});
	
	
	function fn_naviSearch(){
		
		var url =  "/board/list"
			+ '${pageCalculate.makeURI(1)}'
			+ "&searchType="
			+ $("select option:selected").val()
			+ "&keyword="
			+ encodeURIComponent($('#navKeywordInput').val());
	
			self.location = url;
	}

	</script>

